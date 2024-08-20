/* Copyright 2022-2024 Romain Serra
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
package org.orekit.control.indirect.adjoint;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.util.MathArrays;
import org.orekit.frames.Frame;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.FieldAbsoluteDate;

/**
 * Abstract class for common computations regarding adjoint dynamics and gravity for Cartesian coordinates.
 *
 * @author Romain Serra
 * @see CartesianAdjointEquationTerm
 * @since 12.2
 */
public abstract class AbstractCartesianAdjointGravitationalTerm extends AbstractCartesianAdjointEquationTerm {

    /** Body gravitational parameter. */
    private final double mu;

    /**
     * Constructor.
     * @param mu body gravitational parameter
     */
    protected AbstractCartesianAdjointGravitationalTerm(final double mu) {
        this.mu = mu;
    }

    /**
     * Getter for the gravitational constant.
     * @return mu
     */
    public double getMu() {
        return mu;
    }

    /** {@inheritDoc} */
    @Override
    public double[] getRatesContribution(final AbsoluteDate date, final double[] stateVariables,
                                         final double[] adjointVariables, final Frame frame) {
        final double[] contribution = new double[adjointVariables.length];
        final double[] adjointVelocityDerivativesContribution = getVelocityAdjointContribution(date, stateVariables,
            adjointVariables, frame);
        System.arraycopy(adjointVelocityDerivativesContribution, 0, contribution, 3, adjointVelocityDerivativesContribution.length);
        return contribution;
    }

    /**
     * Computes the contribution to velocity adjoint derivatives.
     *
     * @param date             date
     * @param stateVariables   state variables
     * @param adjointVariables adjoint variables
     * @param frame            propagation frame
     * @return contribution to velocity adjoint derivatives
     */
    protected abstract double[] getVelocityAdjointContribution(AbsoluteDate date, double[] stateVariables,
                                                               double[] adjointVariables, Frame frame);

    /** {@inheritDoc} */
    @Override
    public <T extends CalculusFieldElement<T>> T[] getFieldRatesContribution(final FieldAbsoluteDate<T> date,
                                                                             final T[] stateVariables,
                                                                             final T[] adjointVariables, final Frame frame) {
        final T[] contribution = MathArrays.buildArray(date.getField(), adjointVariables.length);
        final T[] adjointVelocityDerivativesContribution = getVelocityAdjointFieldContribution(date, stateVariables,
            adjointVariables, frame);
        System.arraycopy(adjointVelocityDerivativesContribution, 0, contribution, 3, adjointVelocityDerivativesContribution.length);
        return contribution;
    }

    /**
     * Computes the contribution to velocity adjoint derivatives.
     *
     * @param <T>              field type
     * @param date             date
     * @param stateVariables   state variables
     * @param adjointVariables adjoint variables
     * @param frame            propagation frame
     * @return contribution to velocity adjoint derivatives
     */
    protected abstract <T extends CalculusFieldElement<T>> T[] getVelocityAdjointFieldContribution(FieldAbsoluteDate<T> date,
                                                                                                   T[] stateVariables,
                                                                                                   T[] adjointVariables,
                                                                                                   Frame frame);
}
