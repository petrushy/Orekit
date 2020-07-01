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

// this file was created by SCC 2020 and is largely a derived work from the
// original java class/interface

package org.orekit.propagation.analytical.tle;

import org.orekit.annotation.DefaultDataContext;
import org.orekit.attitudes.AttitudeProvider;
import org.orekit.frames.Frame;

public class PythonTLEPropagator extends TLEPropagator {
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
     * Protected constructor for derived classes.
     *
     * <p>This constructor uses the {@link DataContext#getDefault() default data context}.
     *
     * @param initialTLE       the unique TLE to propagate
     * @param attitudeProvider provider for attitude computation
     * @param mass             spacecraft mass (kg)
     * @see #TLEPropagator(TLE, AttitudeProvider, double, Frame)
     */

    @DefaultDataContext
    public PythonTLEPropagator(TLE initialTLE, AttitudeProvider attitudeProvider, double mass) {
        super(initialTLE, attitudeProvider, mass);
    }

    /**
     * Protected constructor for derived classes.
     *
     * @param initialTLE       the unique TLE to propagate
     * @param attitudeProvider provider for attitude computation
     * @param mass             spacecraft mass (kg)
     * @param teme             the TEME frame to use for propagation.
     * @since 10.1
     */
    public PythonTLEPropagator(TLE initialTLE, AttitudeProvider attitudeProvider, double mass, Frame teme) {
        super(initialTLE, attitudeProvider, mass, teme);
    }

    /**
     * Initialization proper to each propagator (SGP or SDP).
     */
    @Override
    public native void sxpInitialize();

    /**
     * Propagation proper to each propagator (SGP or SDP).
     *
     * @param t the offset from initial epoch (min)
     */
    @Override
    public native void sxpPropagate(double t);
}
