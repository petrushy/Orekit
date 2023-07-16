/* Copyright 2002-2020 CS GROUP
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

// this file was created by SCC 2020 and is largely a derived work from the
// original java class/interface


package org.orekit.models.earth.ionosphere;

import org.hipparchus.CalculusFieldElement;

public class PythonIonosphericMappingFunction implements IonosphericMappingFunction {

    /** Part of JCC Python interface to object */
    private long pythonObject;

    /** Part of JCC Python interface to object */
    public void pythonExtension(long pythonObject)
    {
        this.pythonObject = pythonObject;
    }

    /** Part of JCC Python interface to object */
    public long pythonExtension()
    {
        return this.pythonObject;
    }

    /** Part of JCC Python interface to object */
    public void finalize()
            throws Throwable
    {
        pythonDecRef();
    }

    /** Part of JCC Python interface to object */
    public native void pythonDecRef();


    /**
     * This method allows the computation of the ionospheric mapping factor.
     *
     * @param elevation the elevation of the satellite, in radians.
     * @return the ionospheric mapping factor.
     */
    @Override
    public native double mappingFactor(double elevation);

    /**
     * This method allows the computation of the ionospheric mapping factor.
     *
     * @param elevation the elevation of the satellite, in radians.
     * @return the ionospheric mapping factor.
     */
    @Override
    public native <T extends CalculusFieldElement<T>> T mappingFactor(T elevation);

    /**
     * This method allows the computation of the ionospheric mapping factor.
     *
     * @param elevation the elevation of the satellite, in radians.
     * @return the ionospheric mapping factor.
     */
    public native <T extends CalculusFieldElement<T>> T mappingFactor_T(T elevation);
}
