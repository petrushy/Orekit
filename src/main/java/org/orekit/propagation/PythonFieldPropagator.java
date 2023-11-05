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


import org.hipparchus.CalculusFieldElement;
import org.orekit.attitudes.AttitudeProvider;
import org.orekit.frames.Frame;
import org.orekit.propagation.events.FieldEventDetector;
import org.orekit.propagation.integration.FieldAbstractIntegratedPropagator;
import org.orekit.propagation.sampling.FieldOrekitFixedStepHandler;
import org.orekit.propagation.sampling.FieldOrekitStepHandler;
import org.orekit.propagation.sampling.FieldStepHandlerMultiplexer;
import org.orekit.time.FieldAbsoluteDate;
import org.orekit.utils.FieldPVCoordinates;
import org.orekit.utils.TimeStampedFieldPVCoordinates;

import java.util.Collection;
import java.util.List;


public class PythonFieldPropagator<T extends CalculusFieldElement<T>> implements FieldPropagator<T> {

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


    /** {@inheritDoc} */
    @Override
    public native FieldStepHandlerMultiplexer<T> getMultiplexer();

    /** {@inheritDoc} */
    @Override
    public native FieldEphemerisGenerator<T> getEphemerisGenerator();

    /** {@inheritDoc} */
    @Override
    public native FieldSpacecraftState<T> getInitialState();

    /** {@inheritDoc} */
    @Override
    public native void resetInitialState(FieldSpacecraftState<T> state);

    /** {@inheritDoc} */
    @Override
    public native void addAdditionalStateProvider(FieldAdditionalStateProvider<T> additionalStateProvider);

    /** {@inheritDoc} */
    @Override
    public native List<FieldAdditionalStateProvider<T>> getAdditionalStateProviders();

    /** {@inheritDoc} */
    @Override
    public native boolean isAdditionalStateManaged(String name);

    /** {@inheritDoc} */
    @Override
    public native String[] getManagedAdditionalStates();

    /** {@inheritDoc} */
    @Override
    public native <D extends FieldEventDetector<T>> void addEventDetector(D detector);

    /** {@inheritDoc} */
    @Override
    public native Collection<FieldEventDetector<T>> getEventsDetectors();

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
    public native FieldSpacecraftState<T> propagate(FieldAbsoluteDate<T> target);

    /** {@inheritDoc} */
    @Override
    public native FieldSpacecraftState<T> propagate(FieldAbsoluteDate<T> start, FieldAbsoluteDate<T> target);

    /** {@inheritDoc} */
    @Override
    public native TimeStampedFieldPVCoordinates<T> getPVCoordinates(FieldAbsoluteDate<T> date, Frame frame);
}
