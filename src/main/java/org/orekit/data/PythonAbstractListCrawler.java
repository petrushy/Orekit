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

package org.orekit.data;

import java.io.IOException;
import java.io.InputStream;

public class PythonAbstractListCrawler<T> extends AbstractListCrawler<T> {

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
     * Get the complete name of a input.
     *
     * @param input input to consider
     * @return complete name of the input
     */
    @Override
    public native String getCompleteName(T input);

    /**
     * Get the base name of an input.
     *
     * @param input input to consider
     * @return base name of the input
     */
    @Override
    public native String getBaseName(T input);

    /**
     * Get a zip/jar crawler for an input.
     *
     * @param input input to consider
     * @return zip/jar crawler for an input
     */
    @Override
    public native ZipJarCrawler getZipJarCrawler(T input);

    /**
     * Get the stream to read from an input.
     *
     * @param input input to read from
     * @return stream to read the content of the input
     * @throws IOException if the input cannot be opened for reading
     */
    @Override
    public native InputStream getStream(T input) throws IOException;
}
