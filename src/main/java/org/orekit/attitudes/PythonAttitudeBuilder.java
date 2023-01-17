/* Copyright 2002-2021 CS GROUP
 * Licensed to CS GROUP (CS) under one or more
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

// this file was created by SSC 2021 and is largely a derived work from the
// original java class

package org.orekit.attitudes;

import org.hipparchus.CalculusFieldElement;
import org.orekit.frames.Frame;
import org.orekit.utils.FieldPVCoordinatesProvider;
import org.orekit.utils.PVCoordinatesProvider;
import org.orekit.utils.TimeStampedAngularCoordinates;
import org.orekit.utils.TimeStampedFieldAngularCoordinates;

public class PythonAttitudeBuilder implements AttitudeBuilder {

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
     * Build a filtered attitude.
     *
     * @param frame       reference frame with respect to which attitude must be defined
     * @param pvProv      provider for spacecraft position and velocity
     * @param rawAttitude raw rotation/rotation rate/rotation acceleration
     * @return filtered attitude
     */
    @Override
    public native Attitude build(Frame frame, PVCoordinatesProvider pvProv, TimeStampedAngularCoordinates rawAttitude);

    /**
     * Build a filtered attitude.
     *
     * @param frame       reference frame with respect to which attitude must be defined
     * @param pvProv      provider for spacecraft position and velocity
     * @param rawAttitude raw rotation/rotation rate/rotation acceleration
     * @return filtered attitude
     */
    public  native <T extends CalculusFieldElement<T>> FieldAttitude<T> build_FFT(Frame frame, FieldPVCoordinatesProvider<T> pvProv, TimeStampedFieldAngularCoordinates<T> rawAttitude);

    @Override
    public  <T extends CalculusFieldElement<T>> FieldAttitude<T> build(Frame frame, FieldPVCoordinatesProvider<T> pvProv, TimeStampedFieldAngularCoordinates<T> rawAttitude) {
        return this.build_FFT(frame, pvProv, rawAttitude);
    }


}
