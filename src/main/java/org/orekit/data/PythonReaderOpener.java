/* Copyright 2002-2022 CS GROUP
 * Licensed to CS GROUP (CS) under one or more
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

// this file was created by SSC 2022 and is largely a derived work from the
// original java class/interface

package org.orekit.data;

import java.io.IOException;
import java.io.Reader;

public class PythonReaderOpener implements DataSource.ReaderOpener {

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
     * Open the stream once.
     * <p>
     * Beware that this interface is only intended for <em>lazy</em> opening a
     * stream, i.e. to delay this opening (or not open the stream at all).
     * It is <em>not</em> intended to open the stream several times. Some
     * implementations may fail if an attempt to open a stream several
     * times is made. This is particularly true for network-based streams.
     * </p>
     *
     * @return opened stream
     * @throws IOException if stream cannot be opened
     */
    @Override
    public native Reader openOnce() throws IOException;
}
