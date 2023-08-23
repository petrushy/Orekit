/* Copyright 2002-2023 CS GROUP
 * Licensed to CS GROUP (CS) under one or more
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

package org.orekit.files.ccsds.ndm.odm.omm;

import java.util.List;

import org.orekit.data.DataContext;
import org.orekit.files.ccsds.ndm.NdmConstituent;
import org.orekit.files.ccsds.ndm.odm.OdmCommonMetadata;
import org.orekit.files.ccsds.ndm.odm.KeplerianElements;
import org.orekit.files.ccsds.ndm.odm.OdmHeader;
import org.orekit.files.ccsds.section.Segment;
import org.orekit.orbits.CartesianOrbit;
import org.orekit.orbits.KeplerianOrbit;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.analytical.tle.TLE;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.TimeStamped;
import org.orekit.utils.IERSConventions;

/**
 * This class gathers the informations present in the Orbital Mean-Elements Message (OMM).
 * @author sports
 * @since 6.1
 */
public class Omm extends NdmConstituent<OdmHeader, Segment<OmmMetadata, OmmData>> implements TimeStamped {

    /** Root element for XML files. */
    public static final String ROOT = "omm";

    /** Key for format version. */
    public static final String FORMAT_VERSION_KEY = "CCSDS_OMM_VERS";

    /** Simple constructor.
     * @param header file header
     * @param segments file segments
     * @param conventions IERS conventions
     * @param dataContext used for creating frames, time scales, etc.
     */
    public Omm(final OdmHeader header, final List<Segment<OmmMetadata, OmmData>> segments,
               final IERSConventions conventions, final DataContext dataContext) {
        super(header, segments, conventions, dataContext);
    }

    /** Get the file metadata.
     * @return file metadata
     */
    public OmmMetadata getMetadata() {
        return getSegments().get(0).getMetadata();
    }

    /** Get the file data.
     * @return file data
     */
    public OmmData getData() {
        return getSegments().get(0).getData();
    }

    /** {@inheritDoc} */
    @Override
    public AbsoluteDate getDate() {
        return getData().getKeplerianElementsBlock().getEpoch();
    }

    /** Generate a keplerian orbit.
     * @return generated orbit
     */
    public KeplerianOrbit generateKeplerianOrbit() {
        return getData().getKeplerianElementsBlock().generateKeplerianOrbit(getMetadata().getFrame());
    }

    /** Generate spacecraft state from the {@link CartesianOrbit} generated by generateCartesianOrbit.
     *  Raises an exception if OPM doesn't contain spacecraft mass information.
     * @return the spacecraft state of the OPM
     */
    public SpacecraftState generateSpacecraftState() {
        return new SpacecraftState(generateKeplerianOrbit(), getData().getMass());
    }

    /** Generate TLE from OMM file. Launch Year, Launch Day and Launch Piece are not present in the
     * OMM file, they have to be set manually by the user with the AdditionalData static class.
     * @return the tle
     */
    public TLE generateTLE() {
        final OdmCommonMetadata metadata = getMetadata();
        final KeplerianElements kep = getData().getKeplerianElementsBlock();
        final OmmTle               tle = getData().getTLEBlock();
        return new TLE(tle.getNoradID(), tle.getClassificationType(),
                       metadata.getLaunchYear(), metadata.getLaunchNumber(), metadata.getLaunchPiece(),
                       tle.getEphemerisType(), tle.getElementSetNumber(), kep.getEpoch(),
                       kep.getMeanMotion(), tle.getMeanMotionDot() / 2, tle.getMeanMotionDotDot() / 6,
                       kep.getE(), kep.getI(), kep.getPa(), kep.getRaan(),
                       kep.getAnomaly(), tle.getRevAtEpoch(),
                       tle.getBStar(), getDataContext().getTimeScales().getUTC());
    }

}
