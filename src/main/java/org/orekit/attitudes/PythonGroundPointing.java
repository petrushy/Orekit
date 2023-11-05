/* Copyright 2002-2020 CS Group
 * Licensed to CS Group (CS) under one or more
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

// this file was created by SSC 2020 and is largely a derived work from the
// original java class/interface

package org.orekit.attitudes;


import org.hipparchus.CalculusFieldElement;
import org.orekit.frames.Frame;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.FieldAbsoluteDate;
import org.orekit.utils.FieldPVCoordinatesProvider;
import org.orekit.utils.PVCoordinatesProvider;
import org.orekit.utils.TimeStampedFieldPVCoordinates;
import org.orekit.utils.TimeStampedPVCoordinates;

public class PythonGroundPointing extends GroundPointing {
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
     * Default constructor.
     * Build a new instance with arbitrary default elements.
     *
     * @param inertialFrame frame in which orbital velocities are computed
     * @param bodyFrame     the frame that rotates with the body
     * @since 7.1
     */
    public PythonGroundPointing(Frame inertialFrame, Frame bodyFrame) {
        super(inertialFrame, bodyFrame);
    }

    /** {@inheritDoc} */
    @Override
    public native TimeStampedPVCoordinates getTargetPV(PVCoordinatesProvider pvProv, AbsoluteDate date, Frame frame);

    /** {@inheritDoc} */
    @Override
    public native  <T extends CalculusFieldElement<T>> TimeStampedFieldPVCoordinates<T> getTargetPV(FieldPVCoordinatesProvider<T> pvProv, FieldAbsoluteDate<T> date, Frame frame);



}
