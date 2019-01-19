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

import org.orekit.time.AbsoluteDate;
import org.orekit.time.DatesSelector;

import java.util.List;

public class PythonDatesSelector implements DatesSelector {

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
     * Select dates within an interval.
     * <p>
     * The {@code start} and {@code end} date may be either in direct or reverse
     * chronological order. The list is produced in the same order as {@code start}
     * and {@code end}, i.e. direct chronological order if {@code start} is earlier
     * than {@code end} or reverse chronological order if {@code start} is later
     * than {@code end}.
     * </p>
     * <p>
     * The ordering (direct or reverse chronological order) should not be changed
     * between calls, otherwise unpredictable results may occur.
     * </p>
     *
     * @param start interval start
     * @param end   interval end
     * @return selected dates within this interval
     */
    @Override
    public native List<AbsoluteDate> selectDates(AbsoluteDate start, AbsoluteDate end);
}
