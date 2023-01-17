/* Copyright 2002-2021 CS GROUP
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


// this file was created by SSC 2021 and is largely a derived work from the
// original java class

package org.orekit.files.ccsds.utils.generation;

import org.orekit.files.ccsds.ndm.NdmConstituent;
import org.orekit.files.ccsds.section.Header;
import org.orekit.files.ccsds.section.Segment;
import org.orekit.files.ccsds.utils.ContextBinding;

import java.io.IOException;

public class PythonAbstractMessageWriter<H extends Header, S extends Segment<?, ?>, F extends NdmConstituent<H, S>> extends AbstractMessageWriter<H, S, F> {

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
     * Constructor used to create a new NDM writer configured with the necessary parameters
     * to successfully fill in all required fields that aren't part of a standard object.
     * <p>
     * If creation date and originator are not present in header, built-in defaults will be used
     * </p>
     *
     * @param root             root element for XML files
     * @param formatVersionKey key for format version
     * @param defaultVersion   default format version
     * @param context          context binding (may be reset for each segment)
     */
    public PythonAbstractMessageWriter(String root, String formatVersionKey, double defaultVersion, ContextBinding context) {
        super(root, formatVersionKey, defaultVersion, context);
    }

    /**
     * Write one segment content (without XML wrapping).
     *
     * @param generator     generator to use for producing output
     * @param formatVersion format version to use
     * @param segment       segment to write
     * @throws IOException if any buffer writing operations fails
     */
    @Override
    public native void writeSegmentContent(Generator generator, double formatVersion, S segment) throws IOException;
}
