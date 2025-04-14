/* Copyright 2022-2025 Petrus Hyvönen, SSC
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

// this file was created by SSC 2025 and is largely a derived work from the
// original java class by Romain Serra

package org.orekit.models.earth.atmosphere;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.geometry.euclidean.threed.FieldVector3D;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.frames.Frame;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.FieldAbsoluteDate;
import org.orekit.utils.ExtendedPositionProvider;

public class PythonAbstractSunInfluencedAtmosphere extends AbstractSunInfluencedAtmosphere {

    /** Part of JCC Python interface to object */
    private long pythonObject;

    /** Constructor. */
    public PythonAbstractSunInfluencedAtmosphere(ExtendedPositionProvider sun) {
        super(sun);
    }

    /** Part of JCC Python interface to object */
    public void pythonExtension(long pythonObject) {
        this.pythonObject = pythonObject;
    }

    /** Part of JCC Python interface to object */
    public long pythonExtension() {
        return this.pythonObject;
    }

    /** Part of JCC Python interface to object */
    public void finalize()
            throws Throwable {
        pythonDecRef();
    }

    /** Part of JCC Python interface to object */
    public native void pythonDecRef();

    /** {@inheritDoc} */
    @Override
    public native ExtendedPositionProvider getSun();

    /** {@inheritDoc} */
    @Override
    public native Vector3D getSunPosition(AbsoluteDate date, Frame frame);

    /** {@inheritDoc} */
    @Override
    public native <T extends CalculusFieldElement<T>> FieldVector3D<T> getSunPosition(FieldAbsoluteDate<T> date, Frame frame);

    /** {@inheritDoc} */
    @Override
    public native Frame getFrame();

    /** {@inheritDoc} */
    @Override
    public native double getDensity(AbsoluteDate date, Vector3D position, Frame frame);

    /** {@inheritDoc} */
    @Override
    public native <T extends CalculusFieldElement<T>> T getDensity(FieldAbsoluteDate<T> date, FieldVector3D<T> position, Frame frame);
}
