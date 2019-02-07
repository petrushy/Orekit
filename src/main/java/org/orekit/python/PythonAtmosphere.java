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

package org.orekit.python;

import org.hipparchus.RealFieldElement;
import org.hipparchus.geometry.euclidean.threed.FieldVector3D;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.forces.drag.atmosphere.Atmosphere;
import org.orekit.frames.Frame;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.FieldAbsoluteDate;

public class PythonAtmosphere implements Atmosphere {

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
     * Get the frame of the central body.
     *
     * @return frame of the central body.
     * @since 6.0
     */
    @Override
    public native Frame getFrame();

    /**
     * Get the local density.
     * Extension point for Python.
     *
     * @param date     current date
     * @param position current position in frame
     * @param frame    the frame in which is defined the position
     * @return local density (kg/m³)
     */
    @Override
    public native double getDensity(AbsoluteDate date, Vector3D position, Frame frame);

    /**
     * Get the local density.
     * Redirects to getFieldDensity
     *
     * @param date     current date
     * @param position current position in frame
     * @param frame    the frame in which is defined the position
     * @return local density (kg/m³)
     */
    @Override
    public <T extends RealFieldElement<T>> T getDensity(FieldAbsoluteDate<T> date, FieldVector3D<T> position, Frame frame) {
        return this.getFieldDensity(date, position, frame);
    }


    /**
     * Get the local density.
     * Extension point for Python.
     *
     * @param date     current date
     * @param position current position in frame
     * @param frame    the frame in which is defined the position
     * @return local density (kg/m³)
     */
    public native <T extends RealFieldElement<T>> T getFieldDensity(FieldAbsoluteDate<T> date, FieldVector3D<T> position, Frame frame);


    /**
     * Get the inertial velocity of atmosphere molecules.
     * Extension point for Python.
     *
     * <p>By default, atmosphere is supposed to have a null
     * velocity in the central body frame.</p>
     *
     * @param date     current date
     * @param position current position in frame
     * @param frame    the frame in which is defined the position
     * @return velocity (m/s) (defined in the same frame as the position)
     */
    @Override
    public native Vector3D getVelocity(AbsoluteDate date, Vector3D position, Frame frame);

    /**
     * Get the inertial velocity of atmosphere molecules.
     * Redirects to getFieldVelocity(...)
     *
     * @param date     current date
     * @param position current position in frame
     * @param frame    the frame in which is defined the position
     * @return velocity (m/s) (defined in the same frame as the position)
     */
    @Override
    public <T extends RealFieldElement<T>> FieldVector3D<T> getVelocity(FieldAbsoluteDate<T> date, FieldVector3D<T> position, Frame frame) {
        return this.getFieldVelocity(date, position, frame);
    }

    /**
     * Get the inertial velocity of atmosphere molecules.
     * Extension point for Python.
     *
     * @param date     current date
     * @param position current position in frame
     * @param frame    the frame in which is defined the position
     * @return velocity (m/s) (defined in the same frame as the position)
     */
    public native <T extends RealFieldElement<T>> FieldVector3D<T> getFieldVelocity(FieldAbsoluteDate<T> date, FieldVector3D<T> position, Frame frame);

}
