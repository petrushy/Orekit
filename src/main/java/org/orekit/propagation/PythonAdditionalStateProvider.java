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
import org.orekit.time.AbsoluteDate;

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
     * Initialize the additional state provider at the start of propagation.
     *
     * @param initialState initial state information at the start of propagation
     * @param target       date of propagation
     * @since 11.2
     */
    @Override
    public native void init(SpacecraftState initialState, AbsoluteDate target);

    /** Check if this provider should yield so another provider has an opportunity to add missing parts.
     * <p>
     * Decision to yield is often based on an additional state being {@link SpacecraftState#hasAdditionalState(String)
     * already available} in the provided {@code state} (but it could theoretically also depend on
     * an additional state derivative being {@link SpacecraftState#hasAdditionalStateDerivative(String)
     * already available}, or any other criterion). If for example a provider needs the state transition
     * matrix, it could implement this method as:
     * </p>
     * <pre>{@code
     * public boolean yield(final SpacecraftState state) {
     *     return !state.getAdditionalStates().containsKey("STM");
     * }
     * }</pre>
     * <p>
     * The default implementation returns {@code false}, meaning that state data can be
     * {@link #getAdditionalState(SpacecraftState) generated} immediately.
     * </p>
     * @param state state to handle
     * @return true if this provider should yield so another provider has an opportunity to add missing parts
     * as the state is incrementally built up
     * @since 11.1
     */
    public native boolean yield_(SpacecraftState state);

    public boolean yield(SpacecraftState state)
    {
        return this.yield_(state);
    }


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
