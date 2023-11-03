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
//

package org.orekit.files.ccsds.utils.lexical;

import org.orekit.data.DataSource;
import org.orekit.files.ccsds.utils.FileFormat;

import java.util.Map;

public class PythonMessageParser<T> implements MessageParser<T> {

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
     * Parse a data source.
     *
     * @param source data source to parse
     * @return parsed file
     */
    @Override
    public native T parseMessage(DataSource source);

    /**
     * Get the key for format version.
     *
     * @return format version key
     */
    @Override
    public native String getFormatVersionKey();

    /**
     * Get the non-default token builders for special XML elements.
     *
     * @return map of token builders for special XML elements (keyed by XML element name)
     */
    @Override
    public native Map<String, XmlTokenBuilder> getSpecialXmlElementsBuilders();

    /**
     * Reset parser to initial state before parsing.
     *
     * @param fileFormat format of the file ready to be parsed
     */
    @Override
    public native void reset(FileFormat fileFormat);

    /**
     * Process a parse token.
     *
     * @param token token to process
     */
    @Override
    public native void process(ParseToken token);

    @Override
    public native T build();

    @Override
    public native FileFormat getFileFormat();
}
