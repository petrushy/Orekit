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

package org.orekit.data;

import org.orekit.data.DataLoader;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

public class PythonDataLoader implements DataLoader {

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
     * Check if the loader still accepts new data.
     * Extension point for Python.
     * <p>
     * This method is used to speed up data loading by interrupting crawling
     * the data sets as soon as a loader has found the data it was waiting for.
     * For loaders that can merge data from any number of sources (for example
     * JPL ephemerides or Earth Orientation Parameters that are split among
     * several files), this method should always return true to make sure no
     * data is left over.
     * </p>
     *
     * @return true while the loader still accepts new data
     */
    @Override
    public native boolean stillAcceptsData();

    /**
     * Load data from a stream.
     * Extension point for Python.
     *
     * @param input data input stream
     * @param name  name of the file (or zip entry)
     * @throws IOException    if data can't be read
     * @throws ParseException if data can't be parsed
     *                        or if some loader specific error occurs
     */
    @Override
    public native void loadData(InputStream input, String name) throws IOException, ParseException;
}
