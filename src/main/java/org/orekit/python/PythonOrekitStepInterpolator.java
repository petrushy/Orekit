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

package org.orekit.python;

import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.sampling.OrekitStepInterpolator;
import org.orekit.time.AbsoluteDate;

public class PythonOrekitStepInterpolator implements OrekitStepInterpolator {

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
     * Get the state at previous grid point date.
     *
     * @return state at previous grid point date
     */
    @Override
    public SpacecraftState getPreviousState() {
        return null;
    }

    /**
     * Determines if the {@link #getPreviousState() previous state} is computed directly
     * by the integrator, or if it is calculated using {@link #getInterpolatedState(AbsoluteDate)
     * interpolation}.
     *
     * <p> Typically the previous state is directly computed by the integrator, but when
     * events are detected the steps are shortened so that events occur on step boundaries
     * which means the previous state may be computed by the interpolator.
     *
     * @return {@code true} if the previous state was calculated by the interpolator and
     * false if it was computed directly by the integrator.
     */
    @Override
    public boolean isPreviousStateInterpolated() {
        return false;
    }

    /**
     * Get the state at current grid point date.
     *
     * @return state at current grid point date
     */
    @Override
    public SpacecraftState getCurrentState() {
        return null;
    }

    /**
     * Determines if the {@link #getCurrentState() current state} is computed directly by
     * the integrator, or if it is calculated using {@link #getInterpolatedState(AbsoluteDate)
     * interpolation}.
     *
     * <p> Typically the current state is directly computed by the integrator, but when
     * events are detected the steps are shortened so that events occur on step boundaries
     * which means the current state may be computed by the interpolator.
     *
     * @return {@code true} if the current state was calculated by the interpolator and
     * false if it was computed directly by the integrator.
     */
    @Override
    public boolean isCurrentStateInterpolated() {
        return false;
    }

    /**
     * Get the state at interpolated date.
     *
     * @param date date of the interpolated state
     * @return state at interpolated date
     */
    @Override
    public SpacecraftState getInterpolatedState(AbsoluteDate date) {
        return null;
    }

    /**
     * Check is integration direction is forward in date.
     *
     * @return true if integration is forward in date
     */
    @Override
    public boolean isForward() {
        return false;
    }

    /**
     * Create a new restricted version of the instance.
     * <p>
     * The instance is not changed at all.
     * </p>
     *
     * @param newPreviousState start of the restricted step
     * @param newCurrentState  end of the restricted step
     * @return restricted version of the instance
     * @see #getPreviousState()
     * @see #getCurrentState()
     * @since 9.0
     */
    @Override
    public OrekitStepInterpolator restrictStep(SpacecraftState newPreviousState, SpacecraftState newCurrentState) {
        return null;
    }
}
