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

import org.hipparchus.RealFieldElement;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.events.FieldEventDetector;
import org.orekit.propagation.events.handlers.FieldEventHandler;
import org.orekit.time.FieldAbsoluteDate;

public class PythonFieldEventDetector<T extends RealFieldElement<T>> implements FieldEventDetector<T> {
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
    public native T getMaxCheckInterval();

    /**
     * Get maximal number of iterations in the event time search.
     *
     * @return maximal number of iterations in the event time search
     */
    @Override
    public native int getMaxIterationCount();

    /**
     * Handle the event.
     *
     * @param s          SpaceCraft state to be used in the evaluation
     * @param increasing with the event occurred in an "increasing" or "decreasing" slope direction
     * @return the Action that the calling detector should pass back to the evaluation system
     * @since 7.0
     */
    @Override
    public native FieldEventHandler.Action eventOccurred(FieldSpacecraftState<T> s, boolean increasing);

    /**
     * Reset the state prior to continue propagation.
     * <p>This method is called after the step handler has returned and
     * before the next step is started, but only when {@link
     * #eventOccurred} has itself returned the {@link Action#RESET_STATE}
     * indicator. It allows the user to reset the state for the next step,
     * without perturbing the step handler of the finishing step. If the
     * {@link #eventOccurred} never returns the {@link Action#RESET_STATE}
     * indicator, this function will never be called, and it is safe to simply return null.</p>
     * <p>
     * The default implementation simply returns its argument.
     * </p>
     *
     * @param oldState old state
     * @return new state
     * @since 7.0
     */
    @Override
    public native FieldSpacecraftState<T> resetState(FieldSpacecraftState<T> oldState);
}
