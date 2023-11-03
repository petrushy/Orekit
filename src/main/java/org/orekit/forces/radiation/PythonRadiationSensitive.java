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

package org.orekit.forces.radiation;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.geometry.euclidean.threed.FieldRotation;
import org.hipparchus.geometry.euclidean.threed.FieldVector3D;
import org.hipparchus.geometry.euclidean.threed.Rotation;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.frames.Frame;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.SpacecraftState;
 ;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.FieldAbsoluteDate;
import org.orekit.utils.ParameterDriver;

import java.util.List;

public class PythonRadiationSensitive implements RadiationSensitive {

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


    /**
     * Get the drivers for supported parameters.
     *
     * @return parameters drivers
     * @since 8.0
     */
    @Override
    public native List<ParameterDriver> getRadiationParametersDrivers();

    @Override
    public native Vector3D radiationPressureAcceleration(SpacecraftState state, Vector3D flux, double[] parameters);

    @Override
    public  <T extends CalculusFieldElement<T>> FieldVector3D<T> radiationPressureAcceleration(FieldSpacecraftState<T> state, FieldVector3D<T> flux, T[] parameters) {
        return this.radiationPressureAcceleration_FFT(state, flux, parameters);
    }

    public native <T extends CalculusFieldElement<T>> FieldVector3D<T> radiationPressureAcceleration_FFT(FieldSpacecraftState<T> state, FieldVector3D<T> flux, T[] parameters);


}
