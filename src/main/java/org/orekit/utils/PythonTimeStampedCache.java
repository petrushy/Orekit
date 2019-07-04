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

package org.orekit.utils;

import org.orekit.time.AbsoluteDate;
import org.orekit.time.TimeStamped;
import org.orekit.utils.TimeStampedCache;

import java.util.stream.Stream;

public class PythonTimeStampedCache<T extends TimeStamped> implements TimeStampedCache<T> {

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
     * Get the entries surrounding a central date.
     * <p>
     * If the central date is well within covered range, the returned array will
     * be balanced with half the points before central date and half the points
     * after it (depending on n parity, of course). If the central date is near
     * the boundary, then the returned array will be unbalanced and will contain
     * only the n earliest (or latest) entries. A typical example of the later
     * case is leap seconds cache, since the number of leap seconds cannot be
     * arbitrarily increased.
     * <p>
     * This method is safe for multiple threads to execute concurrently.
     *
     * @param central central date
     * @return list of cached entries surrounding the specified date. The size
     * of the list is guaranteed to be {@link #getNeighborsSize()}.
     */
    @Override
    public native Stream<T> getNeighbors(AbsoluteDate central);

    /**
     * Get the fixed size of the lists returned by
     * {@link #getNeighbors(AbsoluteDate)}.
     *
     * @return size of the list
     */
    @Override
    public native int getNeighborsSize();

    /**
     * Get the earliest entry in this cache.
     *
     * @return earliest cached entry
     * @throws IllegalStateException if this cache is empty
     */
    @Override
    public native T getEarliest() throws IllegalStateException;

    /**
     * Get the latest entry in this cache.
     *
     * @return latest cached entry
     * @throws IllegalStateException if this cache is empty
     */
    @Override
    public native T getLatest() throws IllegalStateException;
}
