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


package org.orekit.estimation.sequential;

import org.orekit.propagation.MatricesHarvester;
import org.orekit.propagation.PropagationType;
import org.orekit.propagation.Propagator;
import org.orekit.propagation.conversion.OrbitDeterminationPropagatorBuilder;
import org.orekit.utils.ParameterDriversList;

import java.util.List;

public class PythonAbstractKalmanModel extends AbstractKalmanModel {

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
     * Kalman process model constructor (package private).
     * This constructor is used whenever state type and propagation type do not matter.
     * It is used for {@link KalmanModel} and {@link TLEKalmanModel}.
     *
     * @param propagatorBuilders             propagators builders used to evaluate the orbits.
     * @param covarianceMatricesProviders    providers for covariance matrices
     * @param estimatedMeasurementParameters measurement parameters to estimate
     * @param measurementProcessNoiseMatrix  provider for measurement process noise matrix
     * @param harvesters                     harvesters for extracting Jacobians from integrated states
     */
    public PythonAbstractKalmanModel(List<OrbitDeterminationPropagatorBuilder> propagatorBuilders, List<CovarianceMatrixProvider> covarianceMatricesProviders, ParameterDriversList estimatedMeasurementParameters, CovarianceMatrixProvider measurementProcessNoiseMatrix, MatricesHarvester[] harvesters) {
        super(propagatorBuilders, covarianceMatricesProviders, estimatedMeasurementParameters, measurementProcessNoiseMatrix, harvesters);
    }

    /**
     * Kalman process model constructor (package private).
     * This constructor is used whenever propagation type and/or state type are to be specified.
     * It is used for {@link DSSTKalmanModel}.
     *
     * @param propagatorBuilders             propagators builders used to evaluate the orbits.
     * @param covarianceMatricesProviders    providers for covariance matrices
     * @param estimatedMeasurementParameters measurement parameters to estimate
     * @param measurementProcessNoiseMatrix  provider for measurement process noise matrix
     * @param harvesters                     harvesters for extracting Jacobians from integrated states
     * @param propagationType                type of the orbit used for the propagation (mean or osculating), applicable only for DSST
     * @param stateType                      type of the elements used to define the orbital state (mean or osculating), applicable only for DSST
     */
     public PythonAbstractKalmanModel(List<OrbitDeterminationPropagatorBuilder> propagatorBuilders, List<CovarianceMatrixProvider> covarianceMatricesProviders, ParameterDriversList estimatedMeasurementParameters, CovarianceMatrixProvider measurementProcessNoiseMatrix, MatricesHarvester[] harvesters, PropagationType propagationType, PropagationType stateType) {
        super(propagatorBuilders, covarianceMatricesProviders, estimatedMeasurementParameters, measurementProcessNoiseMatrix, harvesters, propagationType, stateType);
    }

    /**
     * Update the reference trajectories using the propagators as input.
     *
     * @param propagators The new propagators to use
     * @param pType       propagationType type of the orbit used for the propagation (mean or osculating)
     * @param sType       type of the elements used to define the orbital state (mean or osculating)
     */
    @Override
    public native void updateReferenceTrajectories(Propagator[] propagators, PropagationType pType, PropagationType sType);
}
