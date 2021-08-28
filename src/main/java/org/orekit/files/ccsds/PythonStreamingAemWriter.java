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

// this file was created by CS Group 2020 and is largely a derived work from the
// original java class/interface.

package org.orekit.files.ccsds;

import org.orekit.time.TimeScale;

import java.io.IOException;
import java.util.Map;

/**
 * A Python wrapper for the StreamingAemWriter class (streaming writer for Attitude Ephemeris Messsage (AEM) files).
 * @author Olivier Podevin
 * @since 10.2
 */
public class PythonStreamingAemWriter extends StreamingAemWriter {

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
     * Create an AEM writer that streams data to the given output stream.
     *
     * @param writer    The output stream for the AEM file. Most methods will append data
     *                  to this {@code writer}.
     * @param timeScale for all times in the AEM except {@link Keyword#CREATION_DATE}. See
     *                  Section 4.2.5.4.2 and Annex A.
     * @param metadata  for the satellite.
     */
    public PythonStreamingAemWriter(final Appendable writer,
                              final TimeScale timeScale,
                              final Map<Keyword, String> metadata) {
        super(writer, timeScale, metadata);
    }

    /**
     * Writes the standard AEM header for the file.
     * @throws IOException if the stream cannot write to stream
     */
    @Override
    public native void writeHeader() throws IOException;

    /**
     * Create a writer for a new AEM attitude ephemeris segment.
     * <p> The returned writer can only write a single attitude ephemeris segment in an AEM.
     * This method must be called to create a writer for each attitude ephemeris segment.
     * @param segmentMetadata the metadata to use for the segment. Overrides for this
     *                        segment any other source of meta data values. See {@link
     *                        #StreamingAemWriter} for a description of which metadata are
     *                        required and how they are determined.
     * @return a new AEM segment, ready for writing.
     */
    @Override
    public native AEMSegment newSegment(final Map<Keyword, String> segmentMetadata);
}
