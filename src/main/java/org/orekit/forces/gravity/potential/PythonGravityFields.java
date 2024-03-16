/* Copyright SSC 2023
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

// This file was created by SSC and updated by SSC in 2023 and is largely a derived work from the
// original java class/interface that it inherits/implements

package org.orekit.forces.gravity.potential;

import org.orekit.time.AbsoluteDate;

import java.util.List;

public class PythonGravityFields implements GravityFields {

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

    @Override
    public native NormalizedSphericalHarmonicsProvider getConstantNormalizedProvider(int degree, int order, AbsoluteDate freezingDate);

    @Override
    public native NormalizedSphericalHarmonicsProvider getNormalizedProvider(int degree, int order);

    @Override
    public native UnnormalizedSphericalHarmonicsProvider getConstantUnnormalizedProvider(int degree, int order, AbsoluteDate freezingDate);

    @Override
    public native UnnormalizedSphericalHarmonicsProvider getUnnormalizedProvider(int degree, int order);

    /**
     * Get the ocean tides waves.
     *
     * <p><span style="color:red">
     * WARNING: as of 2013-11-17, there seem to be an inconsistency when loading
     * one or the other file, for wave Sa (Doodson number 56.554) and P1 (Doodson
     * number 163.555). The sign of the coefficients are different. We think the
     * problem lies in the input files from IERS and not in the conversion (which
     * works for all other waves), but cannot be sure. For this reason, ocean
     * tides are still considered experimental at this date.
     * </span></p>
     *
     * @param degree maximal degree
     * @param order  maximal order
     * @return list of tides waves containing already loaded data
     * @since 6.1
     */
    @Override
    public native List<OceanTidesWave> getOceanTidesWaves(int degree, int order);
}
