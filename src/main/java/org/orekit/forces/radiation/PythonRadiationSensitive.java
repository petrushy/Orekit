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
import org.orekit.time.AbsoluteDate;
import org.orekit.time.FieldAbsoluteDate;
import org.orekit.utils.ParameterDriver;

import java.util.List;

public class PythonRadiationSensitive implements RadiationSensitive {

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
     * Get the drivers for supported parameters.
     *
     * @return parameters drivers
     * @since 8.0
     */
    @Override
    public native List<ParameterDriver> getRadiationParametersDrivers();

    /**
     * Compute the acceleration due to radiation pressure.
     *
     * @param date       current date
     * @param frame      inertial reference frame for state (both orbit and attitude)
     * @param position   position of spacecraft in reference frame
     * @param rotation   orientation (attitude) of the spacecraft with respect to reference frame
     * @param mass       current mass
     * @param flux       radiation flux in the same inertial frame as spacecraft orbit
     * @param parameters values of the force model parameters
     * @return spacecraft acceleration in the same inertial frame as spacecraft orbit (m/s²)
     */
    @Override
    public native Vector3D radiationPressureAcceleration(AbsoluteDate date, Frame frame, Vector3D position, Rotation rotation, double mass, Vector3D flux, double[] parameters);

    /**
     * Compute the acceleration due to radiation pressure.
     *
     * @param date       current date
     * @param frame      inertial reference frame for state (both orbit and attitude)
     * @param position   position of spacecraft in reference frame
     * @param rotation   orientation (attitude) of the spacecraft with respect to reference frame
     * @param mass       current mass
     * @param flux       radiation flux in the same inertial frame as spacecraft orbit
     * @param parameters values of the force model parameters
     * @return spacecraft acceleration in the same inertial frame as spacecraft orbit (m/s²)
     */
    @Override
    public <T extends CalculusFieldElement<T>> FieldVector3D<T> radiationPressureAcceleration(FieldAbsoluteDate<T> date, Frame frame, FieldVector3D<T> position, FieldRotation<T> rotation, T mass, FieldVector3D<T> flux, T[] parameters) {
        return this.radiationPressureAcceleration_FFFFTFT(date, frame, position, rotation, mass, flux, parameters);
    }


    /**
     * Compute the acceleration due to radiation pressure.
     *
     * @param date       current date
     * @param frame      inertial reference frame for state (both orbit and attitude)
     * @param position   position of spacecraft in reference frame
     * @param rotation   orientation (attitude) of the spacecraft with respect to reference frame
     * @param mass       current mass
     * @param flux       radiation flux in the same inertial frame as spacecraft orbit
     * @param parameters values of the force model parameters
     * @return spacecraft acceleration in the same inertial frame as spacecraft orbit (m/s²)
     */
    public native <T extends CalculusFieldElement<T>> FieldVector3D<T> radiationPressureAcceleration_FFFFTFT(FieldAbsoluteDate<T> date, Frame frame, FieldVector3D<T> position, FieldRotation<T> rotation, T mass, FieldVector3D<T> flux, T[] parameters);


}
