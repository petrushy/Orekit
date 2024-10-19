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

package org.orekit.propagation;

import org.hipparchus.linear.RealMatrix;
import org.orekit.attitudes.AttitudeProvider;
import org.orekit.frames.Frame;
import org.orekit.propagation.events.EventDetector;
import org.orekit.propagation.sampling.OrekitStepHandler;
import org.orekit.propagation.sampling.StepHandlerMultiplexer;
import org.orekit.time.AbsoluteDate;
import org.orekit.utils.DoubleArrayDictionary;
import org.orekit.utils.TimeStampedPVCoordinates;

import java.util.Collection;
import java.util.List;

public class PythonPropagator implements Propagator {

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


    public native void setEphemerisModeHandler(OrekitStepHandler handler);

    /** {@inheritDoc} */
    @Override
    public native StepHandlerMultiplexer getMultiplexer();

    /** {@inheritDoc} */
    @Override
    public native EphemerisGenerator getEphemerisGenerator();

    /** {@inheritDoc} */
    @Override
    public native SpacecraftState getInitialState();

    /** {@inheritDoc} */
    @Override
    public native void resetInitialState(SpacecraftState state);

    /** {@inheritDoc} */
    @Override
    public native void addAdditionalStateProvider(AdditionalStateProvider additionalStateProvider);

    /** {@inheritDoc} */
    @Override
    public native List<AdditionalStateProvider> getAdditionalStateProviders();

    /** {@inheritDoc} */
    @Override
    public native boolean isAdditionalStateManaged(String name);

    /** {@inheritDoc} */
    @Override
    public native String[] getManagedAdditionalStates();

    /** {@inheritDoc} */
    @Override
    public native <T extends EventDetector> void addEventDetector(T detector);

    /** {@inheritDoc} */
    @Override
    public native Collection<EventDetector> getEventsDetectors();

    /** {@inheritDoc} */
    @Override
    public native void clearEventsDetectors();

    /** {@inheritDoc} */
    @Override
    public native AttitudeProvider getAttitudeProvider();

    /** {@inheritDoc} */
    @Override
    public native void setAttitudeProvider(AttitudeProvider attitudeProvider);

    /** {@inheritDoc} */
    @Override
    public native Frame getFrame();

    /** {@inheritDoc} */
    @Override
    public native MatricesHarvester setupMatricesComputation(String stmName, RealMatrix initialStm, DoubleArrayDictionary initialJacobianColumns);

    /** {@inheritDoc} */
    @Override
    public native SpacecraftState propagate(AbsoluteDate target);

    /** {@inheritDoc} */
    @Override
    public native SpacecraftState propagate(AbsoluteDate start, AbsoluteDate target);

    /** {@inheritDoc} */
    @Override
    public native TimeStampedPVCoordinates getPVCoordinates(AbsoluteDate date, Frame frame);
}
