/* Copyright 2023 Luc Maisonobe
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
package org.orekit.files.sp3;

import org.orekit.utils.units.Unit;

/** Constants for SP3 files.
 * @since 12.0
 * @author Luc Maisonobe
 */
public class SP3Constants {

    /** Bad or absent clock values are to be set to 999999.999999. */
    public static final double DEFAULT_CLOCK_VALUE = 999999.999999;

    /** Position unit. */
    public static final Unit POSITION_UNIT = Unit.parse("km");

    /** Position accuracy unit. */
    public static final Unit POSITION_ACCURACY_UNIT = Unit.parse("mm");

    /** Velocity unit. */
    public static final Unit VELOCITY_UNIT = Unit.parse("dm/s");

    /** Velocity accuracy unit. */
    public static final Unit VELOCITY_ACCURACY_UNIT = Unit.parse("mm/s").scale("10⁻⁴mm/s", 1.0e-4);

    /** Clock unit. */
    public static final Unit CLOCK_UNIT = Unit.parse("µs");

    /** Clock accuracy unit. */
    public static final Unit CLOCK_ACCURACY_UNIT = Unit.parse("ps");

    /** Clock rate unit. */
    public static final Unit CLOCK_RATE_UNIT = Unit.parse("µs/s").scale("10⁻⁴µs/s", 1.0e-4);

    /** Clock rate accuracy unit. */
    public static final Unit CLOCK_RATE_ACCURACY_UNIT = Unit.parse("ps/s").scale("10⁻⁴ps/s", 1.0e-4);

    /** Private constructor for utility class.
     */
    private SP3Constants() {
        // nothing to do
    }

}
