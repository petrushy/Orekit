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

package org.orekit.propagation.conversion;

import org.orekit.estimation.leastsquares.AbstractBatchLSModel;
import org.orekit.estimation.leastsquares.ModelObserver;
import org.orekit.estimation.measurements.ObservedMeasurement;
import org.orekit.frames.Frame;
import org.orekit.orbits.Orbit;
import org.orekit.orbits.OrbitType;
import org.orekit.orbits.PositionAngleType;
import org.orekit.propagation.Propagator;
 ;
import org.orekit.time.AbsoluteDate;
import org.orekit.utils.ParameterDriversList;

import java.util.List;

public class PythonPropagatorBuilder implements PropagatorBuilder {

    /** Part of JCC Python interface to object */
    protected long pythonObject;
    public void pythonExtension(long pythonObject) {
        this.pythonObject = pythonObject;
    }
    public long pythonExtension() {
        return this.pythonObject;
    }
    public void finalize() throws Throwable { pythonDecRef(); }
    public native void pythonDecRef();

    /** {@inheritDoc} */
    @Override
    public native PropagatorBuilder copy();

    /** {@inheritDoc} */
    @Override
    public native Propagator buildPropagator(double[] normalizedParameters);

    /** {@inheritDoc} */
    @Override
    public native AbstractBatchLSModel buildLeastSquaresModel(PropagatorBuilder[] builders, List<ObservedMeasurement<?>> measurements, ParameterDriversList estimatedMeasurementsParameters, ModelObserver observer);

    /** {@inheritDoc} */
    @Override
    public native double[] getSelectedNormalizedParameters();

    /** {@inheritDoc} */
    @Override
    public native OrbitType getOrbitType();

    /** {@inheritDoc} */
    @Override
    public native PositionAngleType getPositionAngleType();

    /** {@inheritDoc} */
    @Override
    public native AbsoluteDate getInitialOrbitDate();

    /** {@inheritDoc} */
    @Override
    public native Frame getFrame();

    /** {@inheritDoc} */
    @Override
    public native double getMu();

    /** {@inheritDoc} */
    @Override
    public native ParameterDriversList getOrbitalParametersDrivers();

    /** {@inheritDoc} */
    @Override
    public native ParameterDriversList getPropagationParametersDrivers();

    /** {@inheritDoc} */
    @Override
    public native void resetOrbit(Orbit newOrbit);
}
