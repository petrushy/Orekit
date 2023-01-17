/* Contributed in the public domain.
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

// this file was created by SSC 2020 and is largely a derived work from the
// original java class/interface


package org.orekit.files.general;

import org.hipparchus.geometry.euclidean.threed.RotationOrder;
import org.orekit.attitudes.BoundedAttitudeProvider;
import org.orekit.frames.Frame;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.TimeScale;
import org.orekit.utils.AngularDerivativesFilter;
import org.orekit.utils.TimeStampedAngularCoordinates;

import java.util.List;

public class PythonAttitudeEphemerisSegment implements AttitudeEphemerisFile.AttitudeEphemerisSegment {
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
     * Get an unmodifiable list of attitude data lines.
     *
     * @return a list of attitude data
     */
    @Override
    public native List<? extends TimeStampedAngularCoordinates> getAngularCoordinates();



    /**
     * Get the reference frame from which attitude is defined.
     *
     * @return the reference frame from which attitude is defined
     */
    @Override
    public native Frame getReferenceFrame();

    /**
     * Get the start date of this ephemeris segment.
     *
     * @return ephemeris segment start date.
     */
    @Override
    public native AbsoluteDate getStart();

    /**
     * Get the end date of this ephemeris segment.
     *
     * @return ephemeris segment end date.
     */
    @Override
    public native AbsoluteDate getStop();

    /**
     * Get the interpolation method to be used.
     *
     * @return the interpolation method
     */
    @Override
    public native String getInterpolationMethod();

    /**
     * Get the number of samples to use in interpolation.
     *
     * @return the number of points to use for interpolation.
     */
    @Override
    public native int getInterpolationSamples();

    /**
     * Get which derivatives of angular data are available in this attitude ephemeris segment.
     *
     * @return a value indicating if the file contains rotation and/or rotation rate
     * and/or acceleration data.
     */
    @Override
    public native AngularDerivativesFilter getAvailableDerivatives();

    /**
     * Get the attitude provider for this attitude ephemeris segment.
     *
     * @return the attitude provider for this attitude ephemeris segment.
     */
    @Override
    public native BoundedAttitudeProvider getAttitudeProvider();
}
