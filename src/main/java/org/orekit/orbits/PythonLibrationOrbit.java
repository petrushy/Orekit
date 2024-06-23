/* Copyright 2002-2020 CS GROUP
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

// this file was created by SCC 2020 and is largely a derived work from the
// original java class/interface

package org.orekit.orbits;

import org.orekit.bodies.CR3BPSystem;
import org.orekit.utils.PVCoordinates;

public class PythonLibrationOrbit extends LibrationOrbit {

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
     * Constructor.
     *
     * @param system        CR3BP System considered
     * @param initialPV     initial position on a libration Orbit
     * @param orbitalPeriod initial orbital period of the libration Orbit
     */
    public PythonLibrationOrbit(CR3BPSystem system, PVCoordinates initialPV, double orbitalPeriod) {
        super(system, initialPV, orbitalPeriod);
    }

    /** {@inheritDoc} */
    @Override
    public native PVCoordinates applyCorrectionOnPV(CR3BPDifferentialCorrection diff);
}
