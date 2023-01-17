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


package org.orekit.utils;

import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.integration.AdditionalEquations;
import org.orekit.propagation.numerical.NumericalPropagator;

import java.util.List;

public class PythonAbstractMultipleShooting extends AbstractMultipleShooting {

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


    /** Simple Constructor.
     * <p> Standard constructor for multiple shooting </p>
     * @param initialGuessList initial patch points to be corrected.
     * @param propagatorList list of propagators associated to each patch point.
     * @param arcDuration initial guess of the duration of each arc.
     * @param tolerance convergence tolerance on the constraint vector.
     * @param maxIter maximum number of iterations
     * @param additionalName name of the additional equations
     * @since 11.1
     */
    public PythonAbstractMultipleShooting(final List<SpacecraftState> initialGuessList, final List<NumericalPropagator> propagatorList,
                                          final double arcDuration, final double tolerance, final int maxIter, final String additionalName)
    { super(initialGuessList, propagatorList, arcDuration, tolerance, maxIter, additionalName); }

    /**
     * Compute the additional constraints.
     *
     * @param propagatedSP propagated SpacecraftState
     * @return fxAdditionnal additional constraints
     */
    @Override
    public native double[] computeAdditionalConstraints(List<SpacecraftState> propagatedSP);

    /**
     * Compute a part of the Jacobian matrix from additional constraints.
     *
     * @param propagatedSP propagatedSP
     * @return jacobianMatrix Jacobian sub-matrix
     */
    @Override
    public native  double[][] computeAdditionalJacobianMatrix(List<SpacecraftState> propagatedSP);

    /**
     * Compute the additional state from the additionalEquations.
     *
     * @param initialState         SpacecraftState without the additional state
     * @param additionalEquations2 Additional Equations.
     * @return augmentedSP SpacecraftState with the additional state within.
     */
    @Override
    public native SpacecraftState getAugmentedInitialState(SpacecraftState initialState, AdditionalEquations additionalEquations2);
}
