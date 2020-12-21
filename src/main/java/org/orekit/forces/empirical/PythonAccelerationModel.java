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

// this file was created by SSC 2020 and is largely a derived work from the
// original java class/interface

package org.orekit.forces.empirical;

import org.hipparchus.RealFieldElement;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.SpacecraftState;
import org.orekit.utils.ParameterDriver;

public class PythonAccelerationModel implements AccelerationModel {

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
     * Compute the signed amplitude of the acceleration.
     * <p>
     * The acceleration is the direction multiplied by the signed amplitude. So if
     * signed amplitude is negative, the acceleratin is towards the opposite of the
     * direction specified at construction.
     * </p>
     *
     * @param state      current state information: date, kinematics, attitude
     * @param parameters values of the force model parameters
     * @return norm of the acceleration
     */
    @Override
    public native double signedAmplitude(SpacecraftState state, double[] parameters);

    /**
     * Compute the signed amplitude of the acceleration.
     * <p>
     * The acceleration is the direction multiplied by the signed amplitude. So if
     * signed amplitude is negative, the acceleratin is towards the opposite of the
     * direction specified at construction.
     * </p>
     *
     * @param state      current state information: date, kinematics, attitude
     * @param parameters values of the force model parameters
     * @return norm of the acceleration
     */
    @Override
    public <T extends RealFieldElement<T>> T signedAmplitude(FieldSpacecraftState<T> state, T[] parameters) {
        return this.signedAmplitude_FT(state, parameters);
    }

    public native <T extends RealFieldElement<T>> T signedAmplitude_FT(FieldSpacecraftState<T> state, T[] parameters);

    /**
     * Get the drivers for acceleration model parameters.
     *
     * @return drivers for acceleration model parameters
     */
    @Override
    public native ParameterDriver[] getParametersDrivers();
}