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

import org.orekit.time.TimeScales;
import org.orekit.utils.IERSConventions;

import java.util.function.Supplier;

// TODO: Check if this is reasonable interface

public class PythonAbstractFrames extends AbstractFrames {
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
     * @param timeScales   to use when creating frames.
     * @param icrfSupplier used to implement {@link #getICRF()};
     */
    public PythonAbstractFrames(TimeScales timeScales, Supplier<Frame> icrfSupplier) {
        super(timeScales, icrfSupplier);
    }

    /**
     * Get Earth Orientation Parameters history.
     *
     * @param conventions conventions for which EOP history is requested
     * @param simpleEOP   if true, tidal effects are ignored when interpolating EOP
     * @return Earth Orientation Parameters history
     */
    @Override
    public native EOPHistory getEOPHistory(IERSConventions conventions, boolean simpleEOP);
}
