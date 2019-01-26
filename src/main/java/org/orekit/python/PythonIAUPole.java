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

import java.io.Serializable;

import org.hipparchus.RealFieldElement;
import org.hipparchus.geometry.euclidean.threed.FieldVector3D;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.bodies.IAUPole;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.FieldAbsoluteDate;


public class PythonIAUPole  implements IAUPole {

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
     * Get the body North pole direction in ICRF frame.
     *
     * @param date current date
     * @return body North pole direction in ICRF frame
     */
    @Override
    public native Vector3D getPole(AbsoluteDate date);

    /**
     * Get the body North pole direction in ICRF frame.
     *
     * @param date current date
     * @return body North pole direction in ICRF frame
     * @since 9.0
     */
    @Override
    public <T extends RealFieldElement<T>> FieldVector3D<T> getPole(FieldAbsoluteDate<T> date) {
        return this.getFieldPole(date);
    }

    /**
     * Get the body North pole direction in ICRF frame.
     *
     * @param date current date
     * @return body North pole direction in ICRF frame
     * @since 9.0
     */
    public native <T extends RealFieldElement<T>> FieldVector3D<T> getFieldPole(FieldAbsoluteDate<T> date);



    /**
     * Get the body Q Node direction in ICRF frame.
     *
     * @param date current date
     * @return body Q Node direction in ICRF frame
     * @since 9.1
     */
    @Override
    public native Vector3D getNode(AbsoluteDate date);

    /**
     * Get the body Q Node direction in ICRF frame.
     *
     * @param date current date
     * @return body Q Node direction in ICRF frame
     * @since 9.1
     */
    @Override
    public <T extends RealFieldElement<T>> FieldVector3D<T> getNode(FieldAbsoluteDate<T> date) {
        return this.getFieldNode(date);
    }

    /**
     * Get the body Q Node direction in ICRF frame.
     *
     * @param date current date
     * @return body Q Node direction in ICRF frame
     * @since 9.1
     */
    public native <T extends RealFieldElement<T>> FieldVector3D<T> getFieldNode(FieldAbsoluteDate<T> date);


    /**
     * Get the prime meridian angle.
     * <p>
     * The prime meridian angle is the angle between the Q node and the
     * prime meridian. represents the body rotation.
     * </p>
     *
     * @param date current date
     * @return prime meridian vector
     */
    @Override
    public native double getPrimeMeridianAngle(AbsoluteDate date);

    /**
     * Get the prime meridian angle.
     * <p>
     * The prime meridian angle is the angle between the Q node and the
     * prime meridian. represents the body rotation.
     * </p>
     *
     * @param date current date
     * @return prime meridian vector
     * @since 9.0
     */
    @Override
    public <T extends RealFieldElement<T>> T getPrimeMeridianAngle(FieldAbsoluteDate<T> date) {
        return this.getFieldPrimeMeridianAngle(date);
    }

    /**
     * Get the prime meridian angle.
     * <p>
     * The prime meridian angle is the angle between the Q node and the
     * prime meridian. represents the body rotation.
     * </p>
     *
     * @param date current date
     * @return prime meridian vector
     * @since 9.0
     */
    public native <T extends RealFieldElement<T>> T getFieldPrimeMeridianAngle(FieldAbsoluteDate<T> date);


}
