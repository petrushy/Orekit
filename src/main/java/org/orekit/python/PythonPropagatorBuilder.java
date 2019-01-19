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

import org.orekit.frames.Frame;
import org.orekit.orbits.OrbitType;
import org.orekit.orbits.PositionAngle;
import org.orekit.propagation.Propagator;
import org.orekit.propagation.conversion.PropagatorBuilder;
import org.orekit.time.AbsoluteDate;
import org.orekit.utils.ParameterDriversList;

public class PythonPropagatorBuilder implements PropagatorBuilder {
    /**
     * Build a propagator.
     *
     * @param normalizedParameters normalized values for the selected parameters
     * @return an initialized propagator
     */
    @Override
    public native Propagator buildPropagator(double[] normalizedParameters);

    /**
     * Get the current value of selected normalized parameters.
     *
     * @return current value of selected normalized parameters
     */
    @Override
    public native double[] getSelectedNormalizedParameters();

    /**
     * Get the orbit type expected for the 6 first parameters in
     * {@link #buildPropagator(double[])}.
     *
     * @return orbit type to use in {@link #buildPropagator(double[])}
     * @see #buildPropagator(double[])
     * @see #getPositionAngle()
     * @since 7.1
     */
    @Override
    public native OrbitType getOrbitType();

    /**
     * Get the position angle type expected for the 6 first parameters in
     * {@link #buildPropagator(double[])}.
     *
     * @return position angle type to use in {@link #buildPropagator(double[])}
     * @see #buildPropagator(double[])
     * @see #getOrbitType()
     * @since 7.1
     */
    @Override
    public native PositionAngle getPositionAngle();

    /**
     * Get the date of the initial orbit.
     *
     * @return date of the initial orbit
     */
    @Override
    public native AbsoluteDate getInitialOrbitDate();

    /**
     * Get the frame in which the orbit is propagated.
     *
     * @return frame in which the orbit is propagated
     */
    @Override
    public native Frame getFrame();

    /**
     * Get the drivers for the configurable orbital parameters.
     *
     * @return drivers for the configurable orbital parameters
     * @since 8.0
     */
    @Override
    public native ParameterDriversList getOrbitalParametersDrivers();

    /**
     * Get the drivers for the configurable propagation parameters.
     * <p>
     * The parameters typically correspond to force models.
     * </p>
     *
     * @return drivers for the configurable propagation parameters
     * @since 8.0
     */
    @Override
    public native ParameterDriversList getPropagationParametersDrivers();
}
