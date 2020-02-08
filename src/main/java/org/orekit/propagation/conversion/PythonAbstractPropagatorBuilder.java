/* Copyright 2002-2020 CS Group
 * Licensed to CS Group (CS) under one or more
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

// this file was created by SCC 2020 and is largely a derived work from the
// original java class/interface

package org.orekit.propagation.conversion;

import org.orekit.orbits.Orbit;
import org.orekit.orbits.PositionAngle;
import org.orekit.propagation.Propagator;
import org.orekit.propagation.conversion.AbstractPropagatorBuilder;

// TODO: Not sure about which methods shall be exposed to Python

public class PythonAbstractPropagatorBuilder extends AbstractPropagatorBuilder {
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
     * Build a new instance.
     * <p>
     * The template orbit is used as a model to {@link
     * #createInitialOrbit() create initial orbit}. It defines the
     * inertial frame, the central attraction coefficient, the orbit type, and is also
     * used together with the {@code positionScale} to convert from the {@link
     * ParameterDriver#setNormalizedValue(double) normalized} parameters used by the
     * callers of this builder to the real orbital parameters.
     * </p>
     * <p>
     * By default, all the {@link #getOrbitalParametersDrivers() orbital parameters drivers}
     * are selected, which means that if the builder is used for orbit determination or
     * propagator conversion, all orbital parameters will be estimated. If only a subset
     * of the orbital parameters must be estimated, caller must retrieve the orbital
     * parameters by calling {@link #getOrbitalParametersDrivers()} and then call
     * {@link ParameterDriver#setSelected(boolean) setSelected(false)}.
     * </p>
     *
     * @param templateOrbit                 reference orbit from which real orbits will be built
     * @param positionAngle                 position angle type to use
     * @param positionScale                 scaling factor used for orbital parameters normalization
     *                                      (typically set to the expected standard deviation of the position)
     * @param addDriverForCentralAttraction if true, a {@link ParameterDriver} should
     *                                      be set up for central attraction coefficient
     * @since 8.0
     */
    public PythonAbstractPropagatorBuilder(Orbit templateOrbit, PositionAngle positionAngle, double positionScale, boolean addDriverForCentralAttraction) {
        super(templateOrbit, positionAngle, positionScale, addDriverForCentralAttraction);
    }

    /**
     * Build a propagator.
     * Extension point for Python.
     *
     *
     * @param normalizedParameters normalized values for the selected parameters
     * @return an initialized propagator
     */
    @Override
    public native Propagator buildPropagator(double[] normalizedParameters);
}
