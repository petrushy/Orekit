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
// original java class/interface


package org.orekit.files.ccsds.section;

import org.orekit.files.ccsds.utils.generation.Generator;

import java.io.IOException;

public class PythonAbstractWriter extends AbstractWriter {

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
     * Simple constructor.
     *
     * @param xmlTag name of the XML tag surrounding the section
     * @param kvnTag name of the KVN tag surrounding the section (may be null)
     */
    public PythonAbstractWriter(String xmlTag, String kvnTag) {
        super(xmlTag, kvnTag);
    }

    /**
     * Write the content of the section, excluding surrounding tags.
     *
     * @param generator generator to use for producing output
     * @throws IOException if any buffer writing operations fails
     */
    @Override
    public native void writeContent(Generator generator) throws IOException;

    /**
     * Convert an array of integer to a comma-separated list.
     *
     * @param integers integers to write
     * @return arrays as a string
     */
    @Override
    public String intArrayToString(int[] integers) {
        return super.intArrayToString(integers);
    }
}
