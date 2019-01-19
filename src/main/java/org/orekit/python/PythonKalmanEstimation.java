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

import org.hipparchus.linear.RealMatrix;
import org.hipparchus.linear.RealVector;
import org.orekit.estimation.measurements.EstimatedMeasurement;
import org.orekit.estimation.sequential.KalmanEstimation;
import org.orekit.propagation.SpacecraftState;
import org.orekit.time.AbsoluteDate;
import org.orekit.utils.ParameterDriversList;

public class PythonKalmanEstimation implements KalmanEstimation {
    /**
     * Get the list of estimated orbital parameters.
     *
     * @return the list of estimated orbital parameters
     */
    @Override
    public native ParameterDriversList getEstimatedOrbitalParameters();

    /**
     * Get the list of estimated propagation parameters.
     *
     * @return the list of estimated propagation parameters
     */
    @Override
    public native ParameterDriversList getEstimatedPropagationParameters();

    /**
     * Get the list of estimated measurements parameters.
     *
     * @return the list of estimated measurements parameters
     */
    @Override
    public native ParameterDriversList getEstimatedMeasurementsParameters();

    /**
     * Get the predicted spacecraft states.
     *
     * @return predicted spacecraft states
     */
    @Override
    public native SpacecraftState[] getPredictedSpacecraftStates();

    /**
     * Get the corrected spacecraft states.
     *
     * @return corrected spacecraft states
     */
    @Override
    public native SpacecraftState[] getCorrectedSpacecraftStates();

    /**
     * Get the "physical" estimated state (i.e. not normalized)
     *
     * @return the "physical" estimated state
     */
    @Override
    public native RealVector getPhysicalEstimatedState();

    /**
     * Get the "physical" estimated covariance matrix (i.e. not normalized)
     *
     * @return the "physical" estimated covariance matrix
     */
    @Override
    public native RealMatrix getPhysicalEstimatedCovarianceMatrix();

    /**
     * Get physical state transition matrix between previous state and estimated (but not yet corrected) state.
     *
     * @return state transition matrix between previous state and estimated state (but not yet corrected)
     * (may be null for initial process estimate)
     * @since 9.3
     */
    @Override
    public native RealMatrix getPhysicalStateTransitionMatrix();

    /**
     * Get the physical Jacobian of the measurement with respect to the state (H matrix).
     *
     * @return physical Jacobian of the measurement with respect to the state (may be null for initial
     * process estimate or if the measurement has been ignored)
     * @since 9.3
     */
    @Override
    public native RealMatrix getPhysicalMeasurementJacobian();

    /**
     * Get the physical innovation covariance matrix.
     *
     * @return physical innovation covariance matrix (may be null for initial
     * process estimate or if the measurement has been ignored)
     * @since 9.3
     */
    @Override
    public native RealMatrix getPhysicalInnovationCovarianceMatrix();

    /**
     * Get the physical Kalman gain matrix.
     *
     * @return Kalman gain matrix (may be null for initial
     * process estimate or if the measurement has been ignored)
     * @since 9.3
     */
    @Override
    public native RealMatrix getPhysicalKalmanGain();

    /**
     * Get the current measurement number.
     *
     * @return current measurement number
     */
    @Override
    public native int getCurrentMeasurementNumber();

    /**
     * Get the current date.
     *
     * @return current date
     */
    @Override
    public native AbsoluteDate getCurrentDate();

    /**
     * Get the predicted measurement.
     * <p>
     * This estimation has been evaluated on the last predicted orbits
     * </p>
     *
     * @return predicted measurement
     */
    @Override
    public native EstimatedMeasurement<?> getPredictedMeasurement();

    /**
     * Get the estimated measurement.
     * <p>
     * This estimation has been evaluated on the last corrected orbits
     * </p>
     *
     * @return corrected measurement
     */
    @Override
    public native EstimatedMeasurement<?> getCorrectedMeasurement();
}
