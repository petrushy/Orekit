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

import org.orekit.forces.drag.atmosphere.JB2008InputParameters;
import org.orekit.time.AbsoluteDate;

public class PythonJB2008InputParameters implements JB2008InputParameters {
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
    public AbsoluteDate getMinDate() {
        return null;
    }

    /**
     * Gets the available data range maximum date.
     *
     * @return the maximum date.
     */
    @Override
    public native AbsoluteDate getMaxDate();

    /**
     * Get the value of the instantaneous solar flux index
     * (1e<sup>-22</sup>*Watt/(m²*Hertz)).
     * <p>Tabular time 1.0 day earlier.</p>
     *
     * @param date the current date
     * @return the instantaneous F10.7 index
     */
    @Override
    public native double getF10(AbsoluteDate date);

    /**
     * Get the value of the mean solar flux.
     * Averaged 81-day centered F10.7 B index on the input time.
     * <p>Tabular time 1.0 day earlier.</p>
     *
     * @param date the current date
     * @return the mean solar flux F10.7B index
     */
    @Override
    public native double getF10B(AbsoluteDate date);

    /**
     * Get the EUV index (26-34 nm) scaled to F10.
     * <p>Tabular time 1.0 day earlier.</p>
     *
     * @param date the current date
     * @return the the EUV S10 index
     */
    @Override
    public native double getS10(AbsoluteDate date);

    /**
     * Get the EUV 81-day averaged centered index.
     * <p>Tabular time 1.0 day earlier.</p>
     *
     * @param date the current date
     * @return the the mean EUV S10B index
     */
    @Override
    public native double getS10B(AbsoluteDate date);

    /**
     * Get the MG2 index scaled to F10.
     * <p>Tabular time 2.0 days earlier.</p>
     *
     * @param date the current date
     * @return the the MG2 index
     */
    @Override
    public native double getXM10(AbsoluteDate date);

    /**
     * Get the MG2 81-day average centered index.
     * <p>Tabular time 2.0 days earlier.</p>
     *
     * @param date the current date
     * @return the the mean MG2 index
     */
    @Override
    public native double getXM10B(AbsoluteDate date);

    /**
     * Get the Solar X-Ray & Lya index scaled to F10.
     * <p>Tabular time 5.0 days earlier.</p>
     *
     * @param date the current date
     * @return the Solar X-Ray & Lya index scaled to F10
     */
    @Override
    public native double getY10(AbsoluteDate date);

    /**
     * Get the Solar X-Ray & Lya 81-day ave. centered index.
     * <p>Tabular time 5.0 days earlier.</p>
     *
     * @param date the current date
     * @return the Solar X-Ray & Lya 81-day ave. centered index
     */
    @Override
    public native double getY10B(AbsoluteDate date);

    /**
     * Get the temperature change computed from Dst index.
     *
     * @param date the current date
     * @return the temperature change computed from Dst index
     */
    @Override
    public native double getDSTDTC(AbsoluteDate date);
}
