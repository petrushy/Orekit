package org.orekit.forces.gravity.potential;

/* Copyright 2002-2021 CS GROUP
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

// this file was created by SSC 2021 and is largely a derived work from the
// original java class


import org.orekit.python.JCCBase;
import org.orekit.time.AbsoluteDate;

public class PythonUnnormalizedSphericalHarmonicsProvider implements UnnormalizedSphericalHarmonicsProvider {

    /** Part of JCC Python interface to object */
    protected long pythonObject;
    public void pythonExtension(long pythonObject) {
        this.pythonObject = pythonObject;
    }
    public long pythonExtension() {
        return this.pythonObject;
    }
    public void finalize() throws Throwable { pythonDecRef(); }
    public native void pythonDecRef();

    /** {@inheritDoc} */
    @Override
    public native int getMaxDegree();

    /** {@inheritDoc} */
    @Override
    public  native int getMaxOrder();

    /** {@inheritDoc} */
    @Override
    public  native double getMu();

    /**
     * Get the value of the central body reference radius.
     *
     * @return ae (m)
     */
    @Override
    public  native double getAe();

    /**
     * Get the reference date for the harmonics.
     *
     * @return reference date for the harmonics
     */
    @Override
    public  native AbsoluteDate getReferenceDate();


    /**
     * Get the {@link TideSystem} used in the gravity field.
     *
     * @return tide system used in the gravity field
     */
    @Override
    public  native TideSystem getTideSystem();

    /**
     * Get the un-normalized spherical harmonic coefficients at a specific instance in time.
     *
     * @param date of evaluation
     * @return un-normalized coefficients on {@code date}.
     * @since 6.1
     */
    @Override
    public  native UnnormalizedSphericalHarmonics onDate(AbsoluteDate date);
}
