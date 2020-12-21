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

import org.orekit.attitudes.BoundedAttitudeProvider;
import org.orekit.time.AbsoluteDate;

import java.util.List;

public class PythonSatelliteAttitudeEphemeris implements AttitudeEphemerisFile.SatelliteAttitudeEphemeris {
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
     * Get the satellite ID. The satellite ID is unique only within the same ephemeris
     * file.
     *
     * @return the satellite's ID, never {@code null}.
     */
    @Override
    public native String getId();

    /**
     * Get the segments of the attitude ephemeris.
     *
     * <p> Attitude ephemeris segments are typically used to split an ephemeris around
     * discontinuous events.
     *
     * @return the segments contained in the attitude ephemeris file for this satellite.
     */
    @Override
    public native List<? extends AttitudeEphemerisFile.AttitudeEphemerisSegment> getSegments();

    /**
     * Get the start date of the ephemeris.
     *
     * @return ephemeris start date.
     */
    @Override
    public native AbsoluteDate getStart();

    /**
     * Get the end date of the ephemeris.
     *
     * @return ephemeris end date.
     */
    @Override
    public native AbsoluteDate getStop();

    /**
     * Get the attitude provider corresponding to this ephemeris, combining data from all {@link
     * #getSegments() segments}.
     *
     * @return an attitude provider for all the data in this attitude ephemeris file.
     */
    @Override
    public native BoundedAttitudeProvider getAttitudeProvider();
}
