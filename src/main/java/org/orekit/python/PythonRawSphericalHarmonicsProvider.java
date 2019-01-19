/* Copyright 2002-2019 CS Systèmes d'Information
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

package org.orekit.python;

import org.orekit.forces.gravity.potential.RawSphericalHarmonicsProvider;
import org.orekit.forces.gravity.potential.TideSystem;
import org.orekit.time.AbsoluteDate;

public class PythonRawSphericalHarmonicsProvider implements RawSphericalHarmonicsProvider {
    /**
     * Get the raw spherical harmonic coefficients on a specific date.
     *
     * @param date to evaluate the spherical harmonics
     * @return the raw spherical harmonics on {@code date}.
     */
    @Override
    public native RawSphericalHarmonics onDate(AbsoluteDate date);

    /**
     * Get the maximal supported degree.
     *
     * @return maximal supported degree
     */
    @Override
    public native int getMaxDegree();

    /**
     * Get the maximal supported order.
     *
     * @return maximal supported order
     */
    @Override
    public native int getMaxOrder();

    /**
     * Get the central body attraction coefficient.
     *
     * @return mu (m³/s²)
     */
    @Override
    public native double getMu();

    /**
     * Get the value of the central body reference radius.
     *
     * @return ae (m)
     */
    @Override
    public native double getAe();

    /**
     * Get the reference date for the harmonics.
     *
     * @return reference date for the harmonics
     */
    @Override
    public native AbsoluteDate getReferenceDate();

    /**
     * Get the offset from {@link #getReferenceDate reference date} for the harmonics.
     *
     * @param date current date
     * @return offset between current date and reference date if there is a reference
     * date, or 0.0 if there are no reference dates (i.e. if {@link #getReferenceDate}
     * returns null)
     */
    @Override
    public native double getOffset(AbsoluteDate date);

    /**
     * Get the {@link TideSystem} used in the gravity field.
     *
     * @return tide system used in the gravity field
     */
    @Override
    public native TideSystem getTideSystem();
}
