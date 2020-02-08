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
    protected PythonSmoothFieldOfView(Vector3D center, Vector3D primaryMeridian, double margin) {
        super(center, primaryMeridian, margin);
    }

    /**
     * Get boundary direction at angle.
     *
     * @param angle phase angle of the boundary direction
     * @return boundary direction at phase angle in spacecraft frame
     */
    @Override
    protected native Vector3D directionAt(double angle);

    /**
     * Get the offset of target body with respect to the Field Of View Boundary.
     * <p>
     * The offset is the signed angular distance between target body and closest boundary
     * point, taking into account {@link VisibilityTrigger} and {@link #getMargin() margin}.
     * </p>
     * <p>
     * As Field Of View can have complex shapes that may require long computation,
     * when the target point can be proven to be outside of the Field Of View, a
     * faster but approximate computation can be used. This approximation is only
     * performed about 0.01 radians outside of the Field Of View augmented by the
     * deadband defined by target body radius and Field Of View margin and should be
     * designed to still return a positive value if the full accurate computation
     * would return a positive value. When target point is close to the zone (and
     * furthermore when it is inside the zone), the full accurate computation is
     * performed. This design allows this offset to be used as a reliable way to
     * detect Field Of View boundary crossings (taking {@link VisibilityTrigger}
     * and {@link #getMargin() margin} into account), which correspond to sign
     * changes of the offset.
     * </p>
     *
     * @param lineOfSight   line of sight from the center of the Field Of View support
     *                      unit sphere to the target in spacecraft frame
     * @param angularRadius target body angular radius
     * @param trigger       visibility trigger for spherical bodies
     * @return an offset negative if the target is visible within the Field Of
     * View and positive if it is outside of the Field Of View
     * (note that this cannot take into account interposing bodies)
     * @see #offsetFromBoundary(Vector3D, double, VisibilityTrigger)
     */
    @Override
    public native double offsetFromBoundary(Vector3D lineOfSight, double angularRadius, VisibilityTrigger trigger);

    /**
     * Find the direction on Field Of View Boundary closest to a line of sight.
     *
     * @param lineOfSight line of sight from the center of the Field Of View support
     *                    unit sphere to the target in spacecraft frame
     * @return direction on Field Of View Boundary closest to a line of sight
     */
    @Override
    public native Vector3D projectToBoundary(Vector3D lineOfSight);
}
