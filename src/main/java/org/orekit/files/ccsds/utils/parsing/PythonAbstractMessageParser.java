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


package org.orekit.files.ccsds.utils.parsing;

import org.orekit.files.ccsds.utils.FileFormat;
import org.orekit.files.ccsds.utils.lexical.ParseToken;

import java.util.List;
import java.util.function.Function;

public class PythonAbstractMessageParser<T> extends AbstractMessageParser<T> {



    /**
     * Simple constructor.
     *
     * @param root             root element for XML files
     * @param formatVersionKey key for format version
     */
    public PythonAbstractMessageParser(final String root, final String formatVersionKey,
                                          final Function<ParseToken, List<ParseToken>>[] filters) {
        super(root, formatVersionKey, filters);
    }

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
    private long pythonObject;

    /** Part of JCC Python interface to object */
    public void finalize()
            throws Throwable
    {
        pythonDecRef();
    }

    /** Part of JCC Python interface to object */
    public native void pythonDecRef();


    /**
     * Reset parser to initial state before parsing.
     *
     * @param fileFormat format of the file ready to be parsed
     */
    @Override
    public native void reset(FileFormat fileFormat);

    /**
     * Build the file from parsed entries.
     *
     * @return parsed file
     */
    @Override
    public native T build();
}
