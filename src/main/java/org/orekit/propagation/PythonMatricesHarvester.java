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

package org.orekit.propagation;

import org.hipparchus.linear.RealMatrix;
import org.orekit.orbits.OrbitType;
import org.orekit.orbits.PositionAngleType;

import java.util.List;

public class PythonMatricesHarvester implements MatricesHarvester {
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
     * Set up reference state.
     * <p>
     * This method is called whenever the global propagation reference state changes.
     * This corresponds to the start of propagation in batch least squares orbit determination
     * or at prediction step for each measurement in Kalman filtering. Its goal is to allow
     * the harvester to compute some internal data. Analytical models like TLE use it to
     * compute analytical derivatives, semi-analytical models like DSST use it to compute
     * short periodic terms, numerical models do not use it at all.
     * </p>
     *
     * @param reference reference state to set
     */
    @Override
    public native void setReferenceState(SpacecraftState reference);

    /**
     * Extract state transition matrix from state.
     *
     * @param state spacecraft state
     * @return state transition matrix, with semantics consistent with propagation,
     * or null if no state transition matrix is available
     * {@link OrbitType orbit type}.
     */
    @Override
    public native RealMatrix getStateTransitionMatrix(SpacecraftState state);

    /**
     * Get the Jacobian with respect to propagation parameters.
     *
     * @param state spacecraft state
     * @return Jacobian with respect to propagation parameters, or null
     * if there are no parameters
     */
    @Override
    public native RealMatrix getParametersJacobian(SpacecraftState state);

    /**
     * Get the names of the parameters in the matrix returned by {@link #getParametersJacobian}.
     * <p>
     * Beware that the names of the parameters are fully known only once all force models have
     * been set up and their parameters properly selected. Applications that retrieve the matrices
     * harvester first and select the force model parameters to retrieve afterwards (but obviously
     * before starting propagation) must take care to wait until the parameters have been set up
     * before they call this method. Calling the method too early would return wrong results.
     * </p>
     * <p>
     * The names are returned in the Jacobians matrix columns order
     * </p>
     *
     * @return names of the parameters (i.e. columns) of the Jacobian matrix
     */
    @Override
    public native List<String> getJacobiansColumnsNames();

    @Override
    public native OrbitType getOrbitType();

    @Override
    public native PositionAngleType getPositionAngleType();
}
