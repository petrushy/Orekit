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

// this file was created by SCC 2020 and is largely a derived work from the
// original java class/interface

package org.orekit.geometry.fov;

import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.propagation.events.VisibilityTrigger;

public class PythonSmoothFieldOfView extends SmoothFieldOfView {

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
     * Build a new instance.
     *
     * @param center          direction of the FOV center (Z<sub>smooth</sub>), in spacecraft frame
     * @param primaryMeridian vector defining the (+X<sub>smooth</sub>, Z<sub>smooth</sub>)
     *                        half-plane (it is allowed to have {@code primaryMeridian} not orthogonal to
     *                        {@code center} as orthogonality will be fixed internally)
     * @param margin          angular margin to apply to the zone (if positive,
     *                        the Field Of View will consider points slightly outside of the
     */
    public PythonSmoothFieldOfView(Vector3D center, Vector3D primaryMeridian, double margin) {
        super(center, primaryMeridian, margin);
    }

    /** {@inheritDoc} */
    @Override
    public native Vector3D directionAt(double angle);

    /** {@inheritDoc} */
    @Override
    public native double offsetFromBoundary(Vector3D lineOfSight, double angularRadius, VisibilityTrigger trigger);

    /** {@inheritDoc} */
    @Override
    public native Vector3D projectToBoundary(Vector3D lineOfSight);
}
