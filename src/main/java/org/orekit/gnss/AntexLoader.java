/* Copyright 2002-2017 CS Systèmes d'Information
 * Licensed to CS Systèmes d'Information (CS) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * CS licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.orekit.gnss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hipparchus.analysis.interpolation.BicubicInterpolator;
import org.hipparchus.analysis.interpolation.BivariateGridInterpolator;
import org.hipparchus.analysis.interpolation.LinearInterpolator;
import org.hipparchus.analysis.interpolation.UnivariateInterpolator;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.hipparchus.util.FastMath;
import org.orekit.data.DataLoader;
import org.orekit.data.DataProvidersManager;
import org.orekit.errors.OrekitException;
import org.orekit.errors.OrekitIllegalArgumentException;
import org.orekit.errors.OrekitMessages;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.TimeScalesFactory;
import org.orekit.utils.TimeSpanMap;

/**
 * Factory for GNSS antennas (both receiver and satellite).
 * <p>
 * The factory creates antennas by parsing an
 * <a href="ftp://www.igs.org/pub/station/general/antex14.txt">ANTEX</a> file.
 * </p>
 *
 * @author Luc Maisonobe
 * @since 9.1
 */
public class AntexLoader {

    /** Default supported files name pattern for antex files. */
    public static final String DEFAULT_ANTEX_SUPPORTED_NAMES = "^\\w{5}(?:_\\d{4})?\\.atx$";

    /** Interpolator for azimut-independent phase. */
    private final UnivariateInterpolator interpolator1D;

    /** Interpolator for azimut-dependent phase. */
    private final BivariateGridInterpolator interpolator2D;

    /** Satellites antennas. */
    private final List<TimeSpanMap<SatelliteAntenna>> satellitesAntennas;

    /** Receivers antennas. */
    private final List<ReceiverAntenna> receiversAntennas;

    /** Simple constructor.
     * @param supportedNames regular expression for supported files names
     * @exception OrekitException if no antex file can be read
     */
    public AntexLoader(final String supportedNames)
        throws OrekitException {
        interpolator1D     = new LinearInterpolator();
        interpolator2D     = new BicubicInterpolator();
        satellitesAntennas = new ArrayList<>();
        receiversAntennas  = new ArrayList<>();
        DataProvidersManager.getInstance().feed(supportedNames, new Parser());
    }

    /** Add a satellite antenna.
     * @param antenna satellite antenna to add
     */
    private void addSatelliteAntenna(final SatelliteAntenna antenna) {
        final Optional<TimeSpanMap<SatelliteAntenna>> existing =
                        satellitesAntennas.
                        stream().
                        filter(m -> {
                            final SatelliteAntenna first = m.getTransitions().first().getBefore();
                            return first.getSatelliteSystem() == antenna.getSatelliteSystem() &&
                                   first.getPrnNumber() == antenna.getPrnNumber();
                        }).findFirst();
        if (existing.isPresent()) {
            // this is an update for a satellite antenna, with new time span
            existing.get().addValidAfter(antenna, antenna.getValidFrom());
        } else {
            // this is a new satellite antenna
            satellitesAntennas.add(new TimeSpanMap<>(antenna));
        }
    }

    /** Get parsed satellites antennas.
     * @return unmodifiable view of parsed satellites antennas
     */
    public List<TimeSpanMap<SatelliteAntenna>> getSatellitesAntennas() {
        return Collections.unmodifiableList(satellitesAntennas);
    }

    /** Add a receiver antenna.
     * @param antenna receiver antenna to add
     */
    private void addReceiverAntenna(final ReceiverAntenna antenna) {
        receiversAntennas.add(antenna);
    }

    /** Get parsed receivers antennas.
     * @return unmodifiable view of parsed receivers antennas
     */
    public List<ReceiverAntenna> getReceiversAntennas() {
        return Collections.unmodifiableList(receiversAntennas);
    }

    /** Parser for antex files.
     * @see <a href="ftp://www.igs.org/pub/station/general/antex14.txt">ANTEX: The Antenna Exchange Format, Version 1.4</a>
     */
    private class Parser implements DataLoader {

        /** Index of label in data lines. */
        private static final int LABEL_START = 60;

        /** Supported format version. */
        private static final double FORMAT_VERSION = 1.4;

        /** Phase center eccentricities conversion factor. */
        private static final double MM_TO_M = 0.001;

        /** {@inheritDoc} */
        @Override
        public boolean stillAcceptsData() {
            // we load all antex files we can find
            return true;
        }

        /** {@inheritDoc} */
        @Override
        public void loadData(final InputStream input, final String name)
            throws IOException, OrekitException {

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"))) {

                // placeholders for parsed data
                int                              lineNumber           = 0;
                SatelliteSystem                  satelliteSystem      = null;
                String                           antennaType          = null;
                SatelliteAntennaCode             satelliteAntennaCode = null;
                String                           serialNumber         = null;
                int                              prnNumber            = -1;
                int                              satelliteCode        = -1;
                String                           cosparID             = null;
                AbsoluteDate                     validFrom            = AbsoluteDate.PAST_INFINITY;
                AbsoluteDate                     validUntil           = AbsoluteDate.FUTURE_INFINITY;
                String                           sinexCode            = null;
                double[]                         azimuthGrid          = null;
                double[]                         polarGrid            = null;
                double[]                         grid1D               = null;
                double[][]                       grid2D               = null;
                Vector3D                         eccentricities       = Vector3D.ZERO;
                int                              nbFrequencies        = -1;
                Frequency                        frequency            = null;
                Map<Frequency, FrequencyPattern> patterns             = null;
                boolean                          inFrequency          = false;
                boolean                          inRMS                = false;

                for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                    ++lineNumber;
                    switch(line.substring(LABEL_START).trim()) {
                        case "COMMENT" :
                            // nothing to do
                            break;
                        case "ANTEX VERSION / SYST" :
                            if (FastMath.abs(parseDouble(line, 0, 8) - FORMAT_VERSION) > 0.001) {
                                throw new OrekitException(OrekitMessages.UNSUPPORTED_FILE_FORMAT, name);
                            }
                            // we parse the general setting for satellite system to check for format errors,
                            // but otherwise ignore it
                            SatelliteSystem.parseSatelliteSystem(parseString(line, 20, 1));
                            break;
                        case "PCV TYPE / REFANT" :
                            // TODO
                            break;
                        case "END OF HEADER" :
                            // nothing to do
                            break;
                        case "START OF ANTENNA" :
                            // reset antenna data
                            satelliteSystem      = null;
                            antennaType          = null;
                            satelliteAntennaCode = null;
                            serialNumber         = null;
                            prnNumber            = -1;
                            satelliteCode        = -1;
                            cosparID             = null;
                            validFrom            = AbsoluteDate.PAST_INFINITY;
                            validUntil           = AbsoluteDate.FUTURE_INFINITY;
                            sinexCode            = null;
                            azimuthGrid          = null;
                            polarGrid            = null;
                            grid1D               = null;
                            grid2D               = null;
                            eccentricities       = Vector3D.ZERO;
                            nbFrequencies        = -1;
                            frequency            = null;
                            patterns             = null;
                            inFrequency          = false;
                            inRMS                = false;
                            break;
                        case "TYPE / SERIAL NO" :
                            antennaType = parseString(line, 0, 20);
                            try {
                                satelliteAntennaCode = SatelliteAntennaCode.parseSatelliteAntennaCode(antennaType);
                                final String satField = parseString(line, 20, 20);
                                if (satField.length() > 0) {
                                    satelliteSystem = SatelliteSystem.parseSatelliteSystem(satField);
                                    final int n = parseInt(satField, 1, 19);
                                    switch (satelliteSystem) {
                                        case GPS:
                                        case GLONASS:
                                        case GALILEO:
                                        case COMPASS:
                                        case IRNSS:
                                            prnNumber = n;
                                            break;
                                        case QZSS:
                                            prnNumber = n + 192;
                                            break;
                                        case SBAS:
                                            prnNumber = n + 100;
                                            break;
                                        default:
                                            // MIXED satellite system is not allowed here
                                            throw new OrekitException(OrekitMessages.UNABLE_TO_PARSE_LINE_IN_FILE,
                                                                      lineNumber, name, line);
                                    }
                                    satelliteCode = parseInt(line, 41, 9); // we drop the system type
                                    cosparID      = parseString(line, 50, 10);
                                }
                            } catch (OrekitIllegalArgumentException oiae) {
                                // this is a receiver antenna, not a satellite antenna
                                serialNumber = parseString(line, 20, 20);
                            }
                            break;
                        case "METH / BY / # / DATE" :
                            // ignored
                            break;
                        case "DAZI" : {
                            final double stepDegrees = parseDouble(line,  2, 6);
                            if (stepDegrees > 0.001) {
                                // we wrap azimuth before 0° and after 360° to ensure smoothness
                                azimuthGrid = new double[3 + (int) FastMath.round(360.0 / stepDegrees)];
                                for (int i = 0; i < azimuthGrid.length; ++i) {
                                    azimuthGrid[i] = FastMath.toRadians((i - 1) * stepDegrees);
                                }
                            }
                            break;
                        }
                        case "ZEN1 / ZEN2 / DZEN" : {
                            final double startDegrees = parseDouble(line,  2, 6);
                            final double endDegrees   = parseDouble(line,  8, 6);
                            final double stepDegrees  = parseDouble(line, 14, 6);
                            polarGrid = new double[1 + (int) FastMath.round((endDegrees - startDegrees) / stepDegrees)];
                            for (int i = 0; i < polarGrid.length; ++i) {
                                polarGrid[i] = FastMath.toRadians(startDegrees + i * stepDegrees);
                            }
                            break;
                        }
                        case "# OF FREQUENCIES" :
                            nbFrequencies = parseInt(line, 0, 6);
                            patterns      = new HashMap<>(nbFrequencies);
                            break;
                        case "VALID FROM" :
                            validFrom = new AbsoluteDate(parseInt(line,     0,  6),
                                                         parseInt(line,     6,  6),
                                                         parseInt(line,    12,  6),
                                                         parseInt(line,    18,  6),
                                                         parseInt(line,    24,  6),
                                                         parseDouble(line, 30, 13),
                                                         TimeScalesFactory.getGPS());
                            break;
                        case "VALID UNTIL" :
                            validUntil = new AbsoluteDate(parseInt(line,     0,  6),
                                                          parseInt(line,     6,  6),
                                                          parseInt(line,    12,  6),
                                                          parseInt(line,    18,  6),
                                                          parseInt(line,    24,  6),
                                                          parseDouble(line, 30, 13),
                                                          TimeScalesFactory.getGPS());
                            break;
                        case "SINEX CODE" :
                            sinexCode = parseString(line, 0, 10);
                            break;
                        case "START OF FREQUENCY" :
                            try {
                                frequency = Frequency.valueOf(parseString(line, 3, 3));
                                grid1D    = new double[polarGrid.length];
                                if (azimuthGrid != null) {
                                    grid2D = new double[azimuthGrid.length][polarGrid.length];
                                }
                            } catch (IllegalArgumentException iae) {
                                throw new OrekitException(OrekitMessages.UNKNOWN_RINEX_FREQUENCY,
                                                          parseString(line, 3, 3), name, lineNumber);
                            }
                            inFrequency = true;
                            break;
                        case "NORTH / EAST / UP" :
                            if (!inRMS) {
                                eccentricities = new Vector3D(parseDouble(line,  0, 10) * MM_TO_M,
                                                              parseDouble(line, 10, 10) * MM_TO_M,
                                                              parseDouble(line, 20, 10) * MM_TO_M);
                            }
                            break;
                        case "END OF FREQUENCY" : {
                            final String endFrequency = parseString(line, 3, 3);
                            if (!frequency.toString().equals(endFrequency)) {
                                throw new OrekitException(OrekitMessages.MISMATCHED_FREQUENCIES,
                                                          name, lineNumber, frequency.toString(), endFrequency);

                            }

                            if (azimuthGrid == null) {
                                patterns.put(frequency,
                                             new FrequencyPattern(eccentricities,
                                                                  polarGrid[0], polarGrid[polarGrid.length - 1],
                                                                  interpolator1D.interpolate(polarGrid, grid1D),
                                                                  null));
                            } else {
                                patterns.put(frequency,
                                             new FrequencyPattern(eccentricities,
                                                                  polarGrid[0], polarGrid[polarGrid.length - 1],
                                                                  interpolator1D.interpolate(polarGrid, grid1D),
                                                                  interpolator2D.interpolate(azimuthGrid, polarGrid, grid2D)));
                            }
                            frequency   = null;
                            grid1D      = null;
                            grid2D      = null;
                            inFrequency = false;
                            break;
                        }
                        case "START OF FREQ RMS" :
                            inRMS = true;
                            break;
                        case "END OF FREQ RMS" :
                            inRMS = false;
                            break;
                        case "END OF ANTENNA" :
                            if (satelliteAntennaCode == null) {
                                addReceiverAntenna(new ReceiverAntenna(antennaType, sinexCode, patterns, serialNumber));
                            } else {
                                addSatelliteAntenna(new SatelliteAntenna(antennaType, sinexCode, patterns,
                                                                         satelliteSystem, prnNumber, satelliteCode,
                                                                         cosparID, validFrom, validUntil));
                            }
                            break;
                        default :
                            if (inFrequency) {
                                final String[] fields = line.trim().split("\\s+");
                                if (fields.length != polarGrid.length + 1) {
                                    throw new OrekitException(OrekitMessages.WRONG_COLUMNS_NUMBER,
                                                              name, lineNumber, polarGrid.length + 1, fields.length);
                                }
                                if ("NOAZI".equals(fields[0])) {
                                    // azimuth-independent phase
                                    for (int i = 0; i < grid1D.length; ++i) {
                                        grid1D[i] = Double.parseDouble(fields[i + 1]);
                                    }
                                } else {
                                    // azimuth-dependent phase
                                    final double stepDegrees = 360.0 / (azimuthGrid.length - 3);
                                    final int    k           = 1 + (int) FastMath.round(Double.parseDouble(fields[0]) / stepDegrees);
                                    for (int i = 0; i < grid2D[k].length; ++i) {
                                        grid2D[k][i] = Double.parseDouble(fields[i + 1]);
                                    }
                                    if (k == 2) {
                                        // copy the data from just above 0° to just above 360°
                                       System.arraycopy(grid2D[k], 0, grid2D[grid2D.length - 1], 0, grid2D[k].length); 
                                    }
                                    if (k == grid2D.length - 2) {
                                        // copy the data from just below 360° to just below 0°
                                       System.arraycopy(grid2D[k], 0, grid2D[0], 0, grid2D[k].length); 
                                    }
                                }
                            } else if (inRMS) {
                                // RMS section is ignored (furthermore there are no RMS sections in both igs08.atx and igs14.atx)
                            } else {
                                throw new OrekitException(OrekitMessages.UNABLE_TO_PARSE_LINE_IN_FILE,
                                                          lineNumber, name, line);
                            }
                    }
                }

            }
        }

        /** Extract a string from a line.
         * @param line to parse
         * @param start start index of the string
         * @param length length of the string
         * @return parsed string
         */
        private String parseString(final String line, final int start, final int length) {
            return line.substring(start, FastMath.min(line.length(), start + length)).trim();
        }

        /** Extract an integer from a line.
         * @param line to parse
         * @param start start index of the integer
         * @param length length of the integer
         * @return parsed integer
         */
        private int parseInt(final String line, final int start, final int length) {
            return Integer.parseInt(parseString(line, start, length));
        }

        /** Extract a double from a line.
         * @param line to parse
         * @param start start index of the real
         * @param length length of the real
         * @return parsed real
         */
        private double parseDouble(final String line, final int start, final int length) {
            return Double.parseDouble(parseString(line, start, length));
        }

    }

}
