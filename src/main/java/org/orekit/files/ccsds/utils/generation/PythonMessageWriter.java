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
import java.io.IOException;

public class PythonMessageWriter<H extends Header, S extends Segment<?, ?>, F extends NdmConstituent<H, S>> implements MessageWriter<H, S, F> {

    /** Part of JCC Python interface to object */
    protected long pythonObject;
    public void pythonExtension(long pythonObject) {
        this.pythonObject = pythonObject;
    }
    public long pythonExtension() {
        return this.pythonObject;
    }
    public void finalize() throws Throwable { pythonDecRef(); }
    public native void pythonDecRef();

    /**
     * Write header for the file.
     *
     * @param generator generator to use for producing output
     * @param header    header to write (creation date and originator will be added if missing)
     * @throws IOException if the stream cannot write to stream
     */
    @Override
    public native void writeHeader(Generator generator, H header) throws IOException;

    /**
     * Write one segment.
     *
     * @param generator generator to use for producing output
     * @param segment   segment to write
     * @throws IOException if any buffer writing operations fails
     */
    @Override
    public native void writeSegment(Generator generator, S segment) throws IOException;

    /**
     * Write footer for the file.
     *
     * @param generator generator to use for producing output
     * @throws IOException if the stream cannot write to stream
     */
    @Override
    public native void writeFooter(Generator generator) throws IOException;

    @Override
    public native String getRoot();

    @Override
    public native String getFormatVersionKey();

    @Override
    public native double getVersion();
}
