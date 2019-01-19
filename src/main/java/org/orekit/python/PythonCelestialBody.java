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

import org.hipparchus.Field;
import org.hipparchus.RealFieldElement;
import org.orekit.bodies.CelestialBody;
import org.orekit.frames.Frame;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.FieldAbsoluteDate;
import org.orekit.utils.FieldPVCoordinatesProvider;
import org.orekit.utils.TimeStampedFieldPVCoordinates;
import org.orekit.utils.TimeStampedPVCoordinates;

public class PythonCelestialBody implements CelestialBody {

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
     * Get an inertially oriented, body centered frame.
     * <p>The frame is always bound to the body center, and its axes have a
     * fixed orientation with respect to other inertial frames.</p>
     *
     * @return an inertially oriented, body centered frame
     * @see #getBodyOrientedFrame()
     */
    @Override
    public native Frame getInertiallyOrientedFrame();

    /**
     * Get a body oriented, body centered frame.
     * <p>The frame is always bound to the body center, and its axes have a
     * fixed orientation with respect to the celestial body.</p>
     *
     * @return a body oriented, body centered frame
     * @see #getInertiallyOrientedFrame()
     */
    @Override
    public native Frame getBodyOrientedFrame();

    /**
     * Get the name of the body.
     *
     * @return name of the body
     */
    @Override
    public native String getName();

    /**
     * Get the attraction coefficient of the body.
     *
     * @return attraction coefficient of the body (m³/s²)
     */
    @Override
    public native double getGM();

    /**
     * Convert to a {@link FieldPVCoordinatesProvider} with a specific type.
     *
     * @param field field for the argument and value
     * @return converted function
     */
    @Override
    public native <T extends RealFieldElement<T>> FieldPVCoordinatesProvider<T> toFieldPVCoordinatesProvider(Field<T> field);

    /**
     * Get the {@link FieldPVCoordinates} of the body in the selected frame.
     *
     * @param date  current date
     * @param frame the frame where to define the position
     * @return time-stamped position/velocity of the body (m and m/s)
     */
    @Override
    public native <T extends RealFieldElement<T>> TimeStampedFieldPVCoordinates<T> getPVCoordinates(FieldAbsoluteDate<T> date, Frame frame);

    /**
     * Get the {@link PVCoordinates} of the body in the selected frame.
     *
     * @param date  current date
     * @param frame the frame where to define the position
     * @return time-stamped position/velocity of the body (m and m/s)
     */
    @Override
    public native TimeStampedPVCoordinates getPVCoordinates(AbsoluteDate date, Frame frame);
}
