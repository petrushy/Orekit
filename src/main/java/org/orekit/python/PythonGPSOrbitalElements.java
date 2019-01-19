/* Copyright 2002-2019 CS Systèmes d'Information
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

import org.orekit.propagation.analytical.gnss.GPSOrbitalElements;
import org.orekit.time.AbsoluteDate;

public class PythonGPSOrbitalElements implements GPSOrbitalElements {

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
     * Gets the PRN number of the GPS satellite.
     *
     * @return the PRN number of the GPS satellite
     */
    @Override
    public int getPRN() {
        return 0;
    }

    /**
     * Gets the Reference Week of the GPS orbit.
     *
     * @return the Reference Week of the GPS orbit within [0, 1024[
     */
    @Override
    public int getWeek() {
        return 0;
    }

    /**
     * Gets the Reference Time of the GPS orbit as a duration from week start.
     *
     * @return the Reference Time of the GPS orbit (s)
     */
    @Override
    public double getTime() {
        return 0;
    }

    /**
     * Gets the Semi-Major Axis.
     *
     * @return the Semi-Major Axis (m)
     */
    @Override
    public double getSma() {
        return 0;
    }

    /**
     * Gets the Mean Motion.
     *
     * @return the Mean Motion (rad/s)
     */
    @Override
    public double getMeanMotion() {
        return 0;
    }

    /**
     * Gets the Eccentricity.
     *
     * @return the Eccentricity
     */
    @Override
    public double getE() {
        return 0;
    }

    /**
     * Gets the Inclination Angle at Reference Time.
     *
     * @return the Inclination Angle at Reference Time (rad)
     */
    @Override
    public double getI0() {
        return 0;
    }

    /**
     * Gets the Rate of Inclination Angle.
     *
     * @return the Rate of Inclination Angle (rad/s)
     */
    @Override
    public double getIDot() {
        return 0;
    }

    /**
     * Gets the Longitude of Ascending Node of Orbit Plane at Weekly Epoch.
     *
     * @return the Longitude of Ascending Node of Orbit Plane at Weekly Epoch (rad)
     */
    @Override
    public double getOmega0() {
        return 0;
    }

    /**
     * Gets the Rate of Right Ascension.
     *
     * @return the Rate of Right Ascension (rad/s)
     */
    @Override
    public double getOmegaDot() {
        return 0;
    }

    /**
     * Gets the Argument of Perigee.
     *
     * @return the Argument of Perigee (rad)
     */
    @Override
    public double getPa() {
        return 0;
    }

    /**
     * Gets the Mean Anomaly at Reference Time.
     *
     * @return the Mean Anomaly at Reference Time (rad)
     */
    @Override
    public double getM0() {
        return 0;
    }

    /**
     * Gets the Amplitude of the Cosine Harmonic Correction Term to the Argument of Latitude.
     *
     * @return the Amplitude of the Cosine Harmonic Correction Term to the Argument of Latitude (rad)
     */
    @Override
    public double getCuc() {
        return 0;
    }

    /**
     * Gets the Amplitude of the Sine Harmonic Correction Term to the Argument of Latitude.
     *
     * @return the Amplitude of the Sine Harmonic Correction Term to the Argument of Latitude (rad)
     */
    @Override
    public double getCus() {
        return 0;
    }

    /**
     * Gets the Amplitude of the Cosine Harmonic Correction Term to the Orbit Radius.
     *
     * @return the Amplitude of the Cosine Harmonic Correction Term to the Orbit Radius (m)
     */
    @Override
    public double getCrc() {
        return 0;
    }

    /**
     * Gets the Amplitude of the Sine Harmonic Correction Term to the Orbit Radius.
     *
     * @return the Amplitude of the Sine Harmonic Correction Term to the Orbit Radius (m)
     */
    @Override
    public double getCrs() {
        return 0;
    }

    /**
     * Gets the Amplitude of the Cosine Harmonic Correction Term to the Angle of Inclination.
     *
     * @return the Amplitude of the Cosine Harmonic Correction Term to the Angle of Inclination (rad)
     */
    @Override
    public double getCic() {
        return 0;
    }

    /**
     * Gets the Amplitude of the Sine Harmonic Correction Term to the Angle of Inclination.
     *
     * @return the Amplitude of the Sine Harmonic Correction Term to the Angle of Inclination (rad)
     */
    @Override
    public double getCis() {
        return 0;
    }

    /**
     * Gets the Issue Of Data Clock (IODC).
     *
     * @return the Issue Of Data Clock (IODC)
     * @since 9.3
     */
    @Override
    public int getIODC() {
        return 0;
    }

    /**
     * Gets the Issue Of Data Ephemeris (IODE).
     *
     * @return the Issue Of Data Ephemeris (IODE)
     * @since 9.3
     */
    @Override
    public int getIODE() {
        return 0;
    }

    /**
     * Gets the Zeroth Order Clock Correction.
     *
     * @return the Zeroth Order Clock Correction (s)
     * @see #getAf1()
     * @see #getAf2()
     * @see #getToc()
     * @since 9.3
     */
    @Override
    public double getAf0() {
        return 0;
    }

    /**
     * Gets the First Order Clock Correction.
     *
     * @return the First Order Clock Correction (s/s)
     * @see #getAf0()
     * @see #getAf2()
     * @see #getToc()
     * @since 9.3
     */
    @Override
    public double getAf1() {
        return 0;
    }

    /**
     * Gets the Second Order Clock Correction.
     *
     * @return the Second Order Clock Correction (s/s²)
     * @see #getAf0()
     * @see #getAf1()
     * @see #getToc()
     * @since 9.3
     */
    @Override
    public double getAf2() {
        return 0;
    }

    /**
     * Gets the clock correction reference time toc.
     *
     * @return the clock correction reference time (s)
     * @see #getAf0()
     * @see #getAf1()
     * @see #getAf2()
     * @since 9.3
     */
    @Override
    public double getToc() {
        return 0;
    }

    /**
     * Gets the estimated group delay differential TGD for L1-L2 correction.
     *
     * @return the estimated group delay differential TGD for L1-L2 correction (s)
     * @since 9.3
     */
    @Override
    public double getTGD() {
        return 0;
    }

    /**
     * Get the date.
     *
     * @return date attached to the object
     */
    @Override
    public AbsoluteDate getDate() {
        return null;
    }
}
