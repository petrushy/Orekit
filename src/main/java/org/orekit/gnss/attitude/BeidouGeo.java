/* Copyright 2002-2018 CS Systèmes d'Information
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
package org.orekit.gnss.attitude;

import org.orekit.errors.OrekitException;
import org.orekit.time.AbsoluteDate;
import org.orekit.utils.PVCoordinatesProvider;
import org.orekit.utils.TimeStampedAngularCoordinates;

/**
 * Attitude providers for Beidou geostationary orbit navigation satellites.
 * @author Luc Maisonobe Java translation
 * @since 9.2
 */
public class BeidouGeo extends AbstractGNSSAttitudeProvider {

    /** Serializable UID. */
    private static final long serialVersionUID = 20171114L;

    /** Simple constructor.
     * @param validityStart start of validity for this provider
     * @param validityEnd end of validity for this provider
     * @param sun provider for Sun position
     */
    public BeidouGeo(final AbsoluteDate validityStart, final AbsoluteDate validityEnd,
                     final PVCoordinatesProvider sun) {
        super(validityStart, validityEnd, sun);
    }

    /** {@inheritDoc} */
    @Override
    protected TimeStampedAngularCoordinates correctedYaw(final GNSSAttitudeContext context)
        throws OrekitException {

        // geostationary Beidou spacecraft are always in Orbit Normal (ON) yaw
        return context.orbitNormalYaw();

    }

}
