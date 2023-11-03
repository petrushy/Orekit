/* Copyright SSC 2023
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

// This file was created by SSC and updated by SSC in 2023 and is largely a derived work from the
// original java class/interface that it inherits/implements

package org.orekit.frames.encounter;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.Field;
import org.hipparchus.geometry.euclidean.threed.FieldRotation;
import org.hipparchus.geometry.euclidean.threed.FieldVector3D;
import org.hipparchus.geometry.euclidean.threed.Rotation;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
 ;
import org.orekit.utils.FieldPVCoordinates;
import org.orekit.utils.PVCoordinates;

public class PythonEncounterLOF implements EncounterLOF {

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

    @Override
    public native String getName();

    // TODO Add field adaptors
    @Override
    public native <T extends CalculusFieldElement<T>> FieldRotation<T> rotationFromInertial(Field<T> field, FieldPVCoordinates<T> origin, FieldPVCoordinates<T> other);

    @Override
    public native Rotation rotationFromInertial(PVCoordinates origin, PVCoordinates other);

    @Override
    public native <T extends CalculusFieldElement<T>> FieldVector3D<T> getAxisNormalToCollisionPlane(Field<T> field);

    @Override
    public native <T extends CalculusFieldElement<T>> FieldPVCoordinates<T> getFieldOther(Field<T> field);

    @Override
    public native Vector3D getAxisNormalToCollisionPlane();

    @Override
    public native PVCoordinates getOther();
}
