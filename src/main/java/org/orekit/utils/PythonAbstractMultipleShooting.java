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



    public PythonAbstractMultipleShooting(final List<SpacecraftState> initialGuessList,
                                          final List<NumericalPropagator> propagatorList,
                                          final double tolerance, final int maxIter,
                                          final boolean isAutonomous, final String additionalName) {
        super(initialGuessList, propagatorList, tolerance, maxIter, isAutonomous, additionalName);
    }

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

    @Override
    public native SpacecraftState getAugmentedInitialState(int i);

   }
