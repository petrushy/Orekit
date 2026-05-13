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
// this file was created by SCC 2018 and is largely a derived work from the
// original java class.
// Expanded in 2026 to expose every default method declared on
// AttitudeProvider as native, so Python subclasses can override
// getAttitudeRotation, getEventDetectors and parameter-driver methods.
// See PythonDetectorModifier for the broader rationale.


package org.orekit.attitudes;


import org.hipparchus.CalculusFieldElement;
import org.hipparchus.Field;
import org.hipparchus.geometry.euclidean.threed.FieldRotation;
import org.hipparchus.geometry.euclidean.threed.Rotation;
import org.orekit.frames.Frame;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.events.EventDetector;
import org.orekit.propagation.events.FieldEventDetector;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.FieldAbsoluteDate;
import org.orekit.utils.FieldPVCoordinatesProvider;
import org.orekit.utils.ParameterDriver;
import org.orekit.utils.PVCoordinatesProvider;

import java.util.List;
import java.util.stream.Stream;

public class PythonAttitudeProvider implements AttitudeProvider {

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
    public native Attitude getAttitude(PVCoordinatesProvider pvProv, AbsoluteDate date, Frame frame);

    /** {@inheritDoc} */
    @Override
    public native <T extends CalculusFieldElement<T>> FieldAttitude<T> getAttitude(FieldPVCoordinatesProvider<T> pvProv, FieldAbsoluteDate<T> date, Frame frame);

    /** {@inheritDoc} */
    @Override
    public native Rotation getAttitudeRotation(PVCoordinatesProvider pvProv, AbsoluteDate date, Frame frame);

    /** {@inheritDoc} */
    @Override
    public native <T extends CalculusFieldElement<T>> FieldRotation<T> getAttitudeRotation(FieldPVCoordinatesProvider<T> pvProv, FieldAbsoluteDate<T> date, Frame frame);

    /** {@inheritDoc} */
    @Override
    public native Rotation getAttitudeRotation(SpacecraftState state, double[] parameters);

    /** {@inheritDoc} */
    @Override
    public native <T extends CalculusFieldElement<T>> FieldRotation<T> getAttitudeRotation(FieldSpacecraftState<T> state, T[] parameters);

    /** {@inheritDoc} */
    @Override
    public native Stream<EventDetector> getEventDetectors();

    /** {@inheritDoc} */
    @Override
    public native <T extends CalculusFieldElement<T>> Stream<FieldEventDetector<T>> getFieldEventDetectors(Field<T> field);

    /** {@inheritDoc} */
    @Override
    public native List<ParameterDriver> getParametersDrivers();
}
