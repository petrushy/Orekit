/* Copyright SSC 2019-2023
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

package org.orekit.propagation.events;

import org.hipparchus.CalculusFieldElement;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.events.handlers.FieldEventHandler;
import org.orekit.time.FieldAbsoluteDate;

public class PythonFieldEventDetector<T extends CalculusFieldElement<T>> implements FieldEventDetector<T> {

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
     * Initialize event handler at the start of a propagation.
     * <p>
     * This method is called once at the start of the propagation. It
     * may be used by the event handler to initialize some internal data
     * if needed.
     * </p>
     * <p>
     * The default implementation does nothing
     * </p>
     *
     * @param s0 initial state
     * @param t  target time for the integration
     */
    @Override
    public native void init(FieldSpacecraftState<T> s0, FieldAbsoluteDate<T> t);

    /**
     * Compute the value of the switching function.
     * This function must be continuous (at least in its roots neighborhood),
     * as the integrator will need to find its roots to locate the events.
     *
     * @param s the current state information: date, kinematics, attitude
     * @return value of the switching function
     */
    @Override
    public native T g(FieldSpacecraftState<T> s);

    /**
     * Get the convergence threshold in the event time search.
     *
     * @return convergence threshold (s)
     */
    @Override
    public native T getThreshold();

    /**
     * Get maximal time interval between switching function checks.
     *
     * @return maximal time interval (s) between switching function checks
     */
    @Override
    public native FieldAdaptableInterval<T> getMaxCheckInterval();

    /**
     * Get maximal number of iterations in the event time search.
     *
     * @return maximal number of iterations in the event time search
     */
    @Override
    public native int getMaxIterationCount();

    @Override
    public native FieldEventHandler<T> getHandler();


}
