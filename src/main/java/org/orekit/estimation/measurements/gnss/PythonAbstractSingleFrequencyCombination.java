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

import org.orekit.gnss.SatelliteSystem;

public class PythonAbstractSingleFrequencyCombination extends AbstractSingleFrequencyCombination {
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
    protected PythonAbstractSingleFrequencyCombination(CombinationType type, SatelliteSystem system) {
        super(type, system);
    }

    /**
     * Get the combined observed value of two measurements.
     *
     * @param phase       observed value of the phase measurement
     * @param pseudoRange observed value of the range measurement
     * @return combined observed value
     */
    @Override
    public native double getCombinedValue(double phase, double pseudoRange);
}
