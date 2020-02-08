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

package org.orekit.estimation.measurements.gnss;

import org.orekit.gnss.Frequency;
import org.orekit.gnss.SatelliteSystem;

public class PythonAbstractDualFrequencyCombination extends AbstractDualFrequencyCombination {
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
     * Constructor.
     *
     * @param type   combination of measurements type
     * @param system satellite system
     */
    public PythonAbstractDualFrequencyCombination(CombinationType type, SatelliteSystem system) {
        super(type, system);
    }

    /**
     * Get the combined observed value of two measurements.
     *
     * @param obs1 observed value of the first measurement
     * @param f1   frequency of the first measurement
     * @param obs2 observed value of the second measurement
     * @param f2   frequency of the second measurement
     * @return combined observed value
     */
    @Override
    public native double getCombinedValue(double obs1, Frequency f1, double obs2, Frequency f2);

    /**
     * Get the combined frequency of two measurements.
     *
     * @param f1 frequency of the first measurement
     * @param f2 frequency of the second measurement
     * @return combined frequency in MHz
     */
    @Override
    public native double getCombinedFrequency(Frequency f1, Frequency f2);
}
