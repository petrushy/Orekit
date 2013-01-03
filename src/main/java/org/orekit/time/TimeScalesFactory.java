/* Copyright 2002-2013 CS Systèmes d'Information
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
package org.orekit.time;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.orekit.data.DataProvidersManager;
import org.orekit.errors.OrekitException;
import org.orekit.errors.OrekitMessages;
import org.orekit.frames.FramesFactory;

/** Factory for predefined time scales.
 * <p>
 * This is a utility class, so its constructor is private.
 * </p>
 * @author Luc Maisonobe
 */
public class TimeScalesFactory implements Serializable {

    /** Serializable UID. */
    private static final long serialVersionUID = -2063625014942931917L;

    /** International Atomic Time scale. */
    private static TAIScale tai = null;

    /** Universal Time Coordinate scale. */
    private static UTCScale utc = null;

    /** Universal Time 1 scale. */
    private static UT1Scale ut1 = null;

    /** Terrestrial Time scale. */
    private static TTScale tt = null;

    /** Galileo System Time scale. */
    private static GalileoScale gst = null;

    /** Global Positioning System scale. */
    private static GPSScale gps = null;

    /** Geocentric Coordinate Time scale. */
    private static TCGScale tcg = null;

    /** Barycentric Dynamic Time scale. */
    private static TDBScale tdb = null;

    /** Barycentric Coordinate Time scale. */
    private static TCBScale tcb = null;

    /** Greenwich Mean Sidereal Time scale. */
    private static GMSTScale gmst = null;

    /** UTCTAI offsets loaders. */
    private static List<UTCTAILoader> loaders = new ArrayList<UTCTAILoader>();

    /** Private constructor.
     * <p>This class is a utility class, it should neither have a public
     * nor a default constructor. This private constructor prevents
     * the compiler from generating one automatically.</p>
     */
    private TimeScalesFactory() {
    }

    /** Add a loader for UTC-TAI offsets history files.
     * @param loader custom loader to add
     * @see #getUTC()
     * @see #clearUTCTAILoaders()
     */
    public static void addUTCTAILoader(final UTCTAILoader loader) {
        loaders.add(loader);
    }

    /** Add the default loader for UTC-TAI offsets history files.
     * <p>
     * The default loader looks for a file named {@code UTC-TAI.history}
     * that must be in the IERS format.
     * </p>
     * @see <a href="http://hpiers.obspm.fr/eoppc/bul/bulc/UTC-TAI.history">IERS UTC-TAI.history file</a>
     * @see #getUTC()
     * @see #clearUTCTAILoaders()
     */
    public static void addDefaultUTCTAILoader() {
        addUTCTAILoader(new UTCTAIHistoryFilesLoader());
    }

    /** Clear loaders for UTC-TAI offsets history files.
     * @see #getUTC()
     * @see #addUTCTAILoader(UTCTAILoader)
     * @see #addDefaultUTCTAILoader()
     */
    public static void clearUTCTAILoaders() {
        loaders.clear();
    }

    /** Get the International Atomic Time scale.
     * @return International Atomic Time scale
     */
    public static TAIScale getTAI() {
        synchronized (TimeScalesFactory.class) {

            if (tai == null) {
                tai = new TAIScale();
            }

            return tai;

        }
    }

    /** Get the Universal Time Coordinate scale.
     * <p>
     * If no {@link UTCTAILoader} has been added by calling {@link
     * #addUTCTAILoader(UTCTAILoader) addUTCTAILoader} or if {@link
     * #clearUTCTAILoaders() clearUTCTAILoaders} has been called afterwards,
     * the {@link #addDefaultUTCTAILoader() addDefaultUTCTAILoader} method
     * will be called automatically.
     * </p>
     * @return Universal Time Coordinate scale
     * @exception OrekitException if some data can't be read or some
     * file content is corrupted
     * @see #addUTCTAILoader(UTCTAILoader)
     * @see #clearUTCTAILoaders()
     * @see #addDefaultUTCTAILoader()
     */
    public static UTCScale getUTC() throws OrekitException {
        synchronized (TimeScalesFactory.class) {

            if (utc == null) {
                SortedMap<DateComponents, Integer> entries =
                    new TreeMap<DateComponents, Integer>();
                boolean loaded = false;
                if (loaders.isEmpty()) {
                    addDefaultUTCTAILoader();
                }
                for (UTCTAILoader loader : loaders) {
                    DataProvidersManager.getInstance().feed(loader.getSupportedNames(), loader);
                    if (!loader.stillAcceptsData()) {
                        entries = loader.loadTimeSteps();
                        loaded = true;
                    }
                }
                if (!loaded) {
                    throw new OrekitException(OrekitMessages.NO_IERS_UTC_TAI_HISTORY_DATA_LOADED);
                }
                utc = new UTCScale(entries);
            }

            return utc;
        }
    }

    /** Get the Universal Time 1 scale.
     * <p>
     * UT1 scale depends on both UTC scale and Earth Orientation Parameters,
     * so this method loads these data sets. See the {@link #getUTC()
     * TimeScalesFactory.getUTC()} and {@link FramesFactory#getEOP2000History()
     * FramesFactory.getEOP2000History()} methods for an explanation of how the
     * corresponding data loaders can be configured.
     * </p>
     * @return Universal Time 1 scale
     * @exception OrekitException if some data can't be read or some
     * file content is corrupted
     * @see #getUTC()
     * @see FramesFactory#getEOP2000History()
     */
    public static UT1Scale getUT1() throws OrekitException {
        synchronized (TimeScalesFactory.class) {

            if (ut1 == null) {
                ut1 = new UT1Scale(FramesFactory.getEOP2000History(), getUTC());
            }

            return ut1;

        }
    }

    /** Get the Terrestrial Time scale.
     * @return Terrestrial Time scale
     */
    public static TTScale getTT() {
        synchronized (TimeScalesFactory.class) {

            if (tt == null) {
                tt = new TTScale();
            }

            return tt;

        }
    }

    /** Get the Galileo System Time scale.
     * @return Galileo System Time scale
     */
    public static GalileoScale getGST() {
        synchronized (TimeScalesFactory.class) {

            if (gst == null) {
                gst = new GalileoScale();
            }

            return gst;

        }
    }

    /** Get the Global Positioning System scale.
     * @return Global Positioning System scale
     */
    public static GPSScale getGPS() {
        synchronized (TimeScalesFactory.class) {

            if (gps == null) {
                gps = new GPSScale();
            }

            return gps;

        }
    }

    /** Get the Geocentric Coordinate Time scale.
     * @return Geocentric Coordinate Time scale
     */
    public static TCGScale getTCG() {
        synchronized (TimeScalesFactory.class) {

            if (tcg == null) {
                tcg = new TCGScale();
            }

            return tcg;

        }
    }

    /** Get the Barycentric Dynamic Time scale.
     * @return Barycentric Dynamic Time scale
     */
    public static TDBScale getTDB() {
        synchronized (TimeScalesFactory.class) {

            if (tdb == null) {
                tdb = new TDBScale();
            }

            return tdb;

        }
    }

    /** Get the Barycentric Coordinate Time scale.
     * @return Barycentric Coordinate Time scale
     */
    public static TCBScale getTCB() {
        synchronized (TimeScalesFactory.class) {

            if (tcb == null) {
                tcb = new TCBScale(getTDB());
            }

            return tcb;

        }
    }

    /** Get the Greenwich Mean Sidereal Time scale.
     * @return Greenwich Mean Sidereal Time scale
     * @exception OrekitException if some data can't be read or some
     * file content is corrupted
     */
    public static GMSTScale getGMST() throws OrekitException {
        synchronized (TimeScalesFactory.class) {

            if (gmst == null) {
                gmst = new GMSTScale(getUT1());
            }

            return gmst;

        }
    }

}
