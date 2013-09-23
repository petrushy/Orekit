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
package org.orekit.frames;

import org.orekit.errors.TimeStampedCacheException;
import org.orekit.time.AbsoluteDate;

/** Interface for retrieving Earth Orientation Parameters history throughout a large time range.
 * @author Luc Maisonobe
 */
public interface EOPHistory {

    /** Get the date of the first available Earth Orientation Parameters.
     * @return the start date of the available data
     */
    AbsoluteDate getStartDate();

    /** Get the date of the last available Earth Orientation Parameters.
     * @return the end date of the available data
     */
    AbsoluteDate getEndDate();

    /** Get the UT1-UTC value.
     * <p>The data provided comes from the IERS files. It is smoothed data.</p>
     * @param date date at which the value is desired
     * @return UT1-UTC in seconds (0 if date is outside covered range)
     */
    double getUT1MinusUTC(final AbsoluteDate date);

    /** Get the LoD (Length of Day) value.
     * <p>The data provided comes from the IERS files. It is smoothed data.</p>
     * @param date date at which the value is desired
     * @return LoD in seconds (0 if date is outside covered range)
     * @exception TimeStampedCacheException if EOP data cannot be retrieved
     */
    double getLOD(final AbsoluteDate date) throws TimeStampedCacheException;

    /** Get the pole IERS Reference Pole correction.
     * <p>The data provided comes from the IERS files. It is smoothed data.</p>
     * @param date date at which the correction is desired
     * @return pole correction ({@link PoleCorrection#NULL_CORRECTION
     * PoleCorrection.NULL_CORRECTION} if date is outside covered range)
     */
    PoleCorrection getPoleCorrection(final AbsoluteDate date);

    /** Get the correction to the nutation parameters.
     * <p>The data provided comes from the IERS files. It is smoothed data.</p>
     * @param date date at which the correction is desired
     * @return nutation correction (zero if date is outside covered range)
     */
    double[] getNutationCorrection(final AbsoluteDate date);

}
