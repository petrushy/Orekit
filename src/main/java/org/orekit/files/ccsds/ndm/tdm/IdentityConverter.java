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
package org.orekit.files.ccsds.ndm.tdm;

import org.orekit.time.AbsoluteDate;

/**
 * Identity converter for Range Units.
 * @author Luc Maisonobe
 * @since 11.0
 */
public class IdentityConverter implements RangeUnitsConverter {

    /** Empty constructor.
     * <p>
     * This constructor is not strictly necessary, but it prevents spurious
     * javadoc warnings with JDK 18 and later.
     * </p>
     * @since 12.0
     */
    public IdentityConverter() {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    public double ruToMeters(final TdmMetadata metadata, final AbsoluteDate date, final double range) {
        return range;
    }

    /** {@inheritDoc} */
    @Override
    public double metersToRu(final TdmMetadata metadata, final AbsoluteDate date, final double range) {
        return range;
    }

}
