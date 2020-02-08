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

// this file was created by SCC 2019 and is largely a derived work from the
// original java class/interface

package org.orekit.propagation;

import org.orekit.propagation.AdditionalStateProvider;
import org.orekit.propagation.SpacecraftState;

public class PythonAdditionalStateProvider implements AdditionalStateProvider {

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
     * Get the name of the additional state.
     * Extension point for Python.
     *
     * @return name of the additional state
     */
    @Override
    public native String getName();

    /**
     * Get the additional state.
     * Extension point for Python.
     *
     * @param state spacecraft state to which additional state should correspond
     * @return additional state corresponding to spacecraft state
     */
    @Override
    public native double[] getAdditionalState(SpacecraftState state);
}