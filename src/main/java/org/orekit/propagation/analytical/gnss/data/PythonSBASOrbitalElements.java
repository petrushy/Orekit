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

package org.orekit.propagation.analytical.gnss.data;

import org.orekit.time.AbsoluteDate;


public class PythonSBASOrbitalElements implements SBASOrbitalElements {

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
     * Gets the PRN number of the SBAS satellite.
     *
     * @return the PRN number of the SBAS satellite
     */
    @Override
    public native int getPRN();

    /**
     * Gets the Reference Week of the SBAS orbit.
     *
     * @return the Reference Week of the SBAS orbit
     */
    @Override
    public native int getWeek();

    /**
     * Gets the Reference Time of the SBAS orbit in GPS seconds of the week.
     *
     * @return the Reference Time of the SBAS orbit (s)
     */
    @Override
    public native double getTime();

    /**
     * Get the ECEF-X component of satellite coordinates.
     *
     * @return the ECEF-X component of satellite coordinates (m)
     */
    @Override
    public native double getX();

    /**
     * Get the ECEF-X component of satellite velocity vector.
     *
     * @return the the ECEF-X component of satellite velocity vector (m/s)
     */
    @Override
    public native double getXDot();

    /**
     * Get the ECEF-X component of satellite acceleration vector.
     *
     * @return the GLONASS ECEF-X component of satellite acceleration vector (m/s²)
     */
    @Override
    public native double getXDotDot();

    /**
     * Get the ECEF-Y component of satellite coordinates.
     *
     * @return the ECEF-Y component of satellite coordinates (m)
     */
    @Override
    public native double getY();

    /**
     * Get the ECEF-Y component of satellite velocity vector.
     *
     * @return the ECEF-Y component of satellite velocity vector (m/s)
     */
    @Override
    public native double getYDot();

    /**
     * Get the ECEF-Y component of satellite acceleration vector.
     *
     * @return the ECEF-Y component of satellite acceleration vector (m/s²)
     */
    @Override
    public native double getYDotDot();

    /**
     * Get the ECEF-Z component of satellite coordinates.
     *
     * @return the ECEF-Z component of satellite coordinates (m)
     */
    @Override
    public native double getZ();

    /**
     * Get the ECEF-Z component of satellite velocity vector.
     *
     * @return the the ECEF-Z component of satellite velocity vector (m/s)
     */
    @Override
    public native double getZDot();

    /**
     * Get the ECEF-Z component of satellite acceleration vector.
     *
     * @return the ECEF-Z component of satellite acceleration vector (m/s²)
     */
    @Override
    public native double getZDotDot();

    /**
     * Gets the Issue Of Data Navigation (IODN).
     *
     * @return the IODN
     */
    @Override
    public native int getIODN();

    /**
     * Gets the Zeroth Order Clock Correction.
     *
     * @return the Zeroth Order Clock Correction (s)
     */
    @Override
    public native double getAGf0();

    /**
     * Gets the First Order Clock Correction.
     *
     * @return the First Order Clock Correction (s/s)
     */
    @Override
    public native double getAGf1();

    /**
     * Gets the clock correction reference time toc.
     *
     * @return the clock correction reference time (s)
     */
    @Override
    public native double getToc();

    /**
     * Get the date.
     *
     * @return date attached to the object
     */
    @Override
    public native AbsoluteDate getDate();
}
