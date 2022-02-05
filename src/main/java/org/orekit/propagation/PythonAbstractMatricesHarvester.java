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

package org.orekit.propagation;

import org.hipparchus.linear.RealMatrix;
import org.orekit.orbits.OrbitType;
import org.orekit.orbits.PositionAngle;
import org.orekit.utils.DoubleArrayDictionary;

import java.util.List;

public class PythonAbstractMatricesHarvester extends AbstractMatricesHarvester {
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
     * Simple constructor.
     * <p>
     * The arguments for initial matrices <em>must</em> be compatible with the {@link OrbitType orbit type}
     * and {@link PositionAngle position angle} that will be used by propagator
     * </p>
     *
     * @param stmName                State Transition Matrix state name
     * @param initialStm             initial State Transition Matrix ∂Y/∂Y₀,
     *                               if null (which is the most frequent case), assumed to be 6x6 identity
     * @param initialJacobianColumns initial columns of the Jacobians matrix with respect to parameters,
     *                               if null or if some selected parameters are missing from the dictionary, the corresponding
     */
    protected PythonAbstractMatricesHarvester(String stmName, RealMatrix initialStm, DoubleArrayDictionary initialJacobianColumns) {
        super(stmName, initialStm, initialJacobianColumns);
    }

    /**
     * Freeze the names of the Jacobian columns.
     * <p>
     * This method is called when propagation starts, i.e. when configuration is completed
     * </p>
     */
    @Override
    public native void freezeColumnsNames();

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
}
