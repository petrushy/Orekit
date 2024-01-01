/* Copyright 2002-2024 CS GROUP
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
package org.orekit.estimation.measurements.gnss;

/**
 * Enumerate for combination of measurements types.
 *
 * @author Bryan Cazabonne
 * @since 10.1
 */
public enum CombinationType {

    /** Phase minus code combination. */
    PHASE_MINUS_CODE("Phase minus code"),

    /** GRoup And Phase Ionospheric Calibration (GRAPHIC) combination. */
    GRAPHIC("GRAPHIC"),

    /** Geometry-free combination. */
    GEOMETRY_FREE("Geometry Free"),

    /** Ionosphere-free combination. */
    IONO_FREE("Ionosphere Free"),

    /** Narrow-lane combination. */
    NARROW_LANE("Narrow Lane"),

    /** Wide-lane combination. */
    WIDE_LANE("Wide Lane"),

    /** Melbourne-Wübbena combination. */
    MELBOURNE_WUBBENA("Melbourne Wubbena");

    /** Name of the combination of measurements. */
    private final String name;

    /**
     * Constructor.
     * @param name name of the combination of measurements
     */
    CombinationType(final String name) {
        this.name = name;
    }

    /**
     * Get the name of the combination of measurements.
     * @return the name
     */
    public String getName() {
        return name;
    }

}
