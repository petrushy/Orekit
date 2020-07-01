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

import java.io.IOException;

/**
 * A writer for Attitude Ephemeris Messsage (AEM) files.
 * @author Bryan Cazabonne
 * @since 10.2
 */
public class PythonAEMWriter extends AEMWriter {

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
     * Standard default constructor that creates a writer with default
     * configurations.
     */
    public PythonAEMWriter() {
        super(StreamingAemWriter.DEFAULT_ORIGINATOR, null, null);
    }

    /**
     * Constructor used to create a new AEM writer configured with the necessary
     * parameters to successfully fill in all required fields that aren't part
     * of a standard object.
     *
     * @param originator the originator field string
     * @param spaceObjectId the spacecraft ID
     * @param spaceObjectName the space object common name
     */
    public PythonAEMWriter(final String originator, final String spaceObjectId,
                     final String spaceObjectName) {
        super(originator, spaceObjectId, spaceObjectName);
    }

    /**
     * Write the passed in {@link AEMFile} using the passed in {@link Appendable}.
     * @param writer a configured Appendable to feed with text
     * @param aemFile  a populated aem file to serialize into the buffer
     * @throws IOException if any buffer writing operations fail or if the underlying
     *         format doesn't support a configuration in the EphemerisFile
     *         for example having multiple satellites in one file, having
     *         the origin at an unspecified celestial body, etc.)
     */
    @Override
    public native void write(final Appendable writer, final AEMFile aemFile)
        throws IOException;

    /**
     * Write the passed in {@link AEMFile} to a file at the output path specified.
     * @param outputFilePath a file path that the corresponding file will be written to
     * @param aemFile a populated aem file to serialize into the buffer
     * @throws IOException if any file writing operations fail or if the underlying
     *         format doesn't support a configuration in the EphemerisFile
     *         (for example having multiple satellites in one file, having
     *         the origin at an unspecified celestial body, etc.)
     */
    @Override
    public native void write(final String outputFilePath, final AEMFile aemFile)
        throws IOException;

}
