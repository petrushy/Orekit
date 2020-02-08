/* Contributed in the public domain.
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

// this file was created by SCC 2020 and is largely a derived work from the
// original java class/interface

package org.orekit.frames;

import org.orekit.time.TimeScale;
import org.orekit.utils.IERSConventions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class PythonAbstractEopParser extends AbstractEopParser {
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
     * @param converter           converter to use
     * @param itrfVersionProvider to use for determining the ITRF version of the EOP.
     * @param utc                 time scale for parsing dates.
     */
    public PythonAbstractEopParser(IERSConventions.NutationCorrectionConverter converter, ItrfVersionProvider itrfVersionProvider, TimeScale utc) {
        super(converter, itrfVersionProvider, utc);
    }

    /**
     * Parse EOP from the given input stream.
     *
     * @param input stream to parse.
     * @param name  of the stream for error messages.
     * @return parsed EOP entries.
     * @throws IOException if {@code input} throws one during parsing.
     * TODO: Unclear what is reasonable to expose here.
     */
    @Override
    public native Collection<EOPEntry> parse(InputStream input, String name) throws IOException;
}
