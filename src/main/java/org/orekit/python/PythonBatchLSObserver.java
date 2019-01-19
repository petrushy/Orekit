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

import org.hipparchus.optim.nonlinear.vector.leastsquares.LeastSquaresProblem;
import org.orekit.estimation.leastsquares.BatchLSObserver;
import org.orekit.estimation.measurements.EstimationsProvider;
import org.orekit.orbits.Orbit;
import org.orekit.utils.ParameterDriversList;

public class PythonBatchLSObserver implements BatchLSObserver {

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
     * Notification callback for the end of each evaluation.
     *
     * @param iterationsCount                 iterations count
     * @param evaluationsCount                evaluations count
     * @param orbits                          current estimated orbits
     * @param estimatedOrbitalParameters      estimated orbital parameters
     * @param estimatedPropagatorParameters   estimated propagator parameters
     * @param estimatedMeasurementsParameters estimated measurements parameters
     * @param evaluationsProvider             provider for measurements evaluations resulting
     *                                        from the current estimated orbit (this is an unmodifiable view of the
     *                                        current evaluations, its content is changed at each iteration)
     * @param lspEvaluation                   current evaluation of the underlying {@link LeastSquaresProblem
     *                                        least squares problem}
     */
    @Override
    public native void evaluationPerformed(int iterationsCount, int evaluationsCount, Orbit[] orbits, ParameterDriversList estimatedOrbitalParameters, ParameterDriversList estimatedPropagatorParameters, ParameterDriversList estimatedMeasurementsParameters, EstimationsProvider evaluationsProvider, LeastSquaresProblem.Evaluation lspEvaluation);
}
