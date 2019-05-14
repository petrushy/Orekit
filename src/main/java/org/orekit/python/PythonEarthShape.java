/* Contributed in the public domain.
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
import org.hipparchus.geometry.euclidean.threed.FieldLine;
import org.hipparchus.geometry.euclidean.threed.FieldVector3D;
import org.hipparchus.geometry.euclidean.threed.Line;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.bodies.FieldGeodeticPoint;
import org.orekit.bodies.GeodeticPoint;
import org.orekit.frames.Frame;
import org.orekit.models.earth.EarthShape;
import org.orekit.models.earth.ReferenceEllipsoid;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.FieldAbsoluteDate;
import org.orekit.utils.TimeStampedPVCoordinates;

public class PythonEarthShape implements EarthShape {

    private static final long serialVersionUID = -3177940928549930595L;
    
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
     * Get the underlying ellipsoid model that defines latitude and longitude. If the
     * height component of a {@link GeodeticPoint} is not needed,
     * then using the ellipsoid will provide the quickest transformation.
     *
     * @return the reference ellipsoid. May be {@code this}, but never {@code null}.
     */
    @Override
    public native ReferenceEllipsoid getEllipsoid();

    /**
     * Get body frame related to body shape.
     *
     * @return body frame related to body shape
     */
    @Override
    public native Frame getBodyFrame();

    /**
     * Get the intersection point of a line with the surface of the body.
     * <p>A line may have several intersection points with a closed
     * surface (we consider the one point case as a degenerated two
     * points case). The close parameter is used to select which of
     * these points should be returned. The selected point is the one
     * that is closest to the close point.</p>
     *
     * @param line  test line (may intersect the body or not)
     * @param close point used for intersections selection
     * @param frame frame in which line is expressed
     * @param date  date of the line in given frame
     * @return intersection point at altitude zero or null if the line does
     * not intersect the surface
     */
    @Override
    public native GeodeticPoint getIntersectionPoint(Line line, Vector3D close, Frame frame, AbsoluteDate date);

    /**
     * Get the intersection point of a line with the surface of the body.
     * <p>A line may have several intersection points with a closed
     * surface (we consider the one point case as a degenerated two
     * points case). The close parameter is used to select which of
     * these points should be returned. The selected point is the one
     * that is closest to the close point.</p>
     *
     * @param line  test line (may intersect the body or not)
     * @param close point used for intersections selection
     * @param frame frame in which line is expressed
     * @param date  date of the line in given frame
     * @return intersection point at altitude zero or null if the line does
     * not intersect the surface
     * @since 9.0
     */
    @Override
    public <T extends RealFieldElement<T>> FieldGeodeticPoint<T> getIntersectionPoint(FieldLine<T> line, FieldVector3D<T> close, Frame frame, FieldAbsoluteDate<T> date) {
        return this.getIntersectionPoint_FFFF(line, close, frame, date);
    }

    public native <T extends RealFieldElement<T>> FieldGeodeticPoint<T> getIntersectionPoint_FFFF(FieldLine<T> line, FieldVector3D<T> close, Frame frame, FieldAbsoluteDate<T> date);

    /**
     * Project a point to the ground.
     *
     * @param point point to project
     * @param date  current date
     * @param frame frame in which moving point is expressed
     * @return ground point exactly at the local vertical of specified point,
     * in the same frame as specified point
     * @see #projectToGround(TimeStampedPVCoordinates, Frame)
     * @since 7.0
     */
    @Override
    public native Vector3D projectToGround(Vector3D point, AbsoluteDate date, Frame frame);

    /**
     * Project a moving point to the ground.
     *
     * @param pv    moving point
     * @param frame frame in which moving point is expressed
     * @return ground point exactly at the local vertical of specified point,
     * in the same frame as specified point
     * @see #projectToGround(Vector3D, AbsoluteDate, Frame)
     * @since 7.0
     */
    @Override
    public TimeStampedPVCoordinates projectToGround(TimeStampedPVCoordinates pv, Frame frame) {
        return this.projectToGround_TF(pv,frame);
    }

    public native TimeStampedPVCoordinates projectToGround_TF(TimeStampedPVCoordinates pv, Frame frame);

    /**
     * Transform a Cartesian point to a surface-relative point.
     *
     * @param point Cartesian point
     * @param frame frame in which Cartesian point is expressed
     * @param date  date of the computation (used for frames conversions)
     * @return point at the same location but as a surface-relative point
     */
    @Override
    public native GeodeticPoint transform(Vector3D point, Frame frame, AbsoluteDate date);

    /**
     * Transform a Cartesian point to a surface-relative point.
     *
     * @param point Cartesian point
     * @param frame frame in which Cartesian point is expressed
     * @param date  date of the computation (used for frames conversions)
     * @return point at the same location but as a surface-relative point
     * @since 9.0
     */
    @Override
    public <T extends RealFieldElement<T>> FieldGeodeticPoint<T> transform(FieldVector3D<T> point, Frame frame, FieldAbsoluteDate<T> date) {
        return this.transform_FFF(point,frame, date);
    }

    /**
     * Transform a surface-relative point to a Cartesian point.
     *
     * @param point surface-relative point
     * @return point at the same location but as a Cartesian point
     */
    @Override
    public Vector3D transform(GeodeticPoint point) {
        return this.transform_G(point);
    }

    /**
     * Transform a Cartesian point to a surface-relative point.
     *
     * @param point Cartesian point
     * @param frame frame in which Cartesian point is expressed
     * @param date  date of the computation (used for frames conversions)
     * @return point at the same location but as a surface-relative point
     * @since 9.0
     */

    public native <T extends RealFieldElement<T>> FieldGeodeticPoint<T> transform_FFF(FieldVector3D<T> point, Frame frame, FieldAbsoluteDate<T> date);

    /**
     * Transform a surface-relative point to a Cartesian point.
     *
     * @param point surface-relative point
     * @return point at the same location but as a Cartesian point
     */

    public native Vector3D transform_G(GeodeticPoint point);

    /**
     * Transform a surface-relative point to a Cartesian point.
     *
     * @param point surface-relative point
     * @return point at the same location but as a Cartesian point
     * @since 9.0
     */
    @Override
    public <T extends RealFieldElement<T>> FieldVector3D<T> transform(FieldGeodeticPoint<T> point) {
        return this.transform_F(point);
    }

    public native <T extends RealFieldElement<T>> FieldVector3D<T> transform_F(FieldGeodeticPoint<T> point);

}
