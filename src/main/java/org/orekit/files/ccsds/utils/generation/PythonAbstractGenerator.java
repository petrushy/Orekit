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

import org.orekit.files.ccsds.utils.FileFormat;
import org.orekit.utils.units.Unit;

import java.io.IOException;
import java.util.List;

public class PythonAbstractGenerator extends AbstractGenerator {

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


    public PythonAbstractGenerator( Appendable output,  String outputName,
                                    double maxRelativeOffset,  boolean writeUnits) {
        super(output, outputName, maxRelativeOffset, writeUnits);
    }

    /** {@inheritDoc} */
    @Override
    public native FileFormat getFormat();

    /** {@inheritDoc} */
    @Override
    public native void startMessage(String root, String messageTypeKey, double version) throws IOException;

    /** {@inheritDoc} */
    @Override
    public native void endMessage(String root) throws IOException;

    /** {@inheritDoc} */
    @Override
    public native void writeComments(List<String> comments) throws IOException;

    /** {@inheritDoc} */
    @Override
    public native void writeEntry(String key, String value, Unit unit, boolean mandatory) throws IOException;
}
