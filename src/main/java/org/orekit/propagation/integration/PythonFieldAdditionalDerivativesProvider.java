/* Copyright 2002-2022 CS GROUP
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

// this file was created by SSC 2022 and is largely a derived work from the
// original java class/interface


package org.orekit.propagation.integration;

import org.hipparchus.CalculusFieldElement;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.SpacecraftState;
 ;
import org.orekit.time.FieldAbsoluteDate;

public class PythonFieldAdditionalDerivativesProvider<T extends CalculusFieldElement<T>> implements FieldAdditionalDerivativesProvider<T> {

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

    /**
     * Get the name of the additional derivatives (which will become state once integrated).
     *
     * @return name of the additional state (names containing "orekit"
     * with any case are reserved for the library internal use)
     */
    @Override
    public native String getName();

    /**
     * Get the dimension of the generated derivative.
     *
     * @return dimension of the generated
     */
    @Override
    public native int getDimension();

     @Override
    public native void init(FieldSpacecraftState<T> initialState, FieldAbsoluteDate<T> target);

    @Override
    public native boolean yields(FieldSpacecraftState<T> state);

    @Override
    public native FieldCombinedDerivatives<T> combinedDerivatives(FieldSpacecraftState<T> s);
}
