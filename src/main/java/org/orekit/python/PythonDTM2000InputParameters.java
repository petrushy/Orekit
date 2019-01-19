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

package org.orekit.python;

import org.orekit.forces.drag.atmosphere.DTM2000InputParameters;
import org.orekit.time.AbsoluteDate;

public class PythonDTM2000InputParameters implements DTM2000InputParameters {

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
     * Gets the available data range minimum date.
     *
     * @return the minimum date.
     */
    @Override
    public native AbsoluteDate getMinDate();

    /**
     * Gets the available data range maximum date.
     *
     * @return the maximum date.
     */
    @Override
    public native AbsoluteDate getMaxDate();

    /**
     * Get the value of the instantaneous solar flux.
     *
     * @param date the current date
     * @return the instantaneous solar flux
     */
    @Override
    public native double getInstantFlux(AbsoluteDate date);

    /**
     * Get the value of the mean solar flux.
     *
     * @param date the current date
     * @return the mean solar flux
     */
    @Override
    public native double getMeanFlux(AbsoluteDate date);

    /**
     * Get the value of the 3 hours geomagnetic index.
     * With a delay of 3 hours at pole to 6 hours at equator using:
     * delay=6-abs(lat)*0.033 (lat in deg.)
     *
     * @param date the current date
     * @return the 3H geomagnetic index
     */
    @Override
    public native double getThreeHourlyKP(AbsoluteDate date);

    /**
     * Get the last 24H mean geomagnetic index.
     *
     * @param date the current date
     * @return the 24H geomagnetic index
     */
    @Override
    public native double get24HoursKp(AbsoluteDate date);
}
