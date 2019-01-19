/* Copyright 2016 Applied Defense Solutions (ADS)
 * Licensed to CS Systèmes d'Information (CS) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * ADS licenses this file to You under the Apache License, Version 2.0
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

package org.orekit.python;

import org.orekit.files.general.EphemerisFile;
import org.orekit.files.general.EphemerisFileWriter;

import java.io.IOException;

public class PythonEphemerisFileWriter implements EphemerisFileWriter {

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
     * Write the passed in {@link EphemerisFile} using the passed in
     * {@link Appendable}.
     *
     * @param writer        a configured Appendable to feed with text
     * @param ephemerisFile a populated ephemeris file to serialize into the buffer
     * @throws IOException if any buffer writing operations fail or if the underlying
     *                     format doesn't support a configuration in the EphemerisFile
     *                     (for example having multiple satellites in one file, having
     *                     the origin at an unspecified celestial body, etc.)
     */
    @Override
    public native void write(Appendable writer, EphemerisFile ephemerisFile) throws IOException;

    /**
     * Write the passed in {@link EphemerisFile} to a file at the output path
     * specified.
     *
     * @param outputFilePath a file path that the corresponding file will be written to
     * @param ephemerisFile  a populated ephemeris file to serialize into the buffer
     * @throws IOException if any file writing operations fail or if the underlying
     *                     format doesn't support a configuration in the EphemerisFile
     *                     (for example having multiple satellites in one file, having
     *                     the origin at an unspecified celestial body, etc.)
     */
    @Override
    public native void write(String outputFilePath, EphemerisFile ephemerisFile) throws IOException;
}
