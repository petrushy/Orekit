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

package org.orekit.files.ccsds.utils.lexical;

import org.xml.sax.Attributes;

import java.util.List;

public class PythonXmlTokenBuilder implements XmlTokenBuilder {

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
     * Create a list of parse tokens.
     *
     * @param startTag   if true we are parsing the start tag from an XML element
     * @param qName      element qualified name
     * @param content    element content
     * @param attributes element attributes
     * @param lineNumber number of the line in the CCSDS data message
     * @param fileName   name of the file
     * @return list of parse tokens
     */
    @Override
    public native List<ParseToken> buildTokens(boolean startTag, String qName, String content, Attributes attributes, int lineNumber, String fileName);
}
