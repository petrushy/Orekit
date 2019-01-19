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

import org.orekit.estimation.measurements.ComparableMeasurement;
import org.orekit.time.AbsoluteDate;

import java.util.SortedSet;

public class PythonComparableMeasurement implements ComparableMeasurement {

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
     * Get the observed value.
     * <p>
     * The observed value is the value that was measured by the instrument.
     * </p>
     *
     * @return observed value (array of size {@link #getDimension()}
     */
    @Override
    public native double[] getObservedValue();

    /**
     * {@inheritDoc}
     * <p>
     * Measurements comparison is primarily chronological, but measurements
     * with the same date are sorted based on the observed value. Even if they
     * have the same value too, they will <em>not</em> be considered equal if they
     * correspond to different instances. This allows to store measurements in
     * {@link SortedSet SortedSet} without losing any measurements, even
     * redundant ones.
     * </p>
     *
     * @param other
     */
    @Override
    public native int compareTo(ComparableMeasurement other);

    /**
     * Get the date.
     *
     * @return date attached to the object
     */
    @Override
    public native AbsoluteDate getDate();
}
