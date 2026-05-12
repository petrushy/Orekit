/* Copyright 2022-2025 Petrus Hyvönen, SSC
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

// this file was created by SSC 2025 and is largely a derived work from
// the original java interface by Romain Serra and Luc Maisonobe.
// Expanded in 2026 to expose every interface method as native, so Python
// subclasses can selectively override any of them (DetectorModifier is a
// "wrap-and-override" interface and that pattern requires every default
// method to be re-declared as native here — JCC otherwise dispatches the
// default at the JVM level and the Python override is invisible).

package org.orekit.propagation.events;

import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.events.handlers.EventHandler;
import org.orekit.time.AbsoluteDate;

public class PythonDetectorModifier implements DetectorModifier {

    /** Part of JCC Python interface to object */
    private long pythonObject;

    /** Part of JCC Python interface to object */
    public void pythonExtension(long pythonObject) {
        this.pythonObject = pythonObject;
    }

    /** Part of JCC Python interface to object */
    public long pythonExtension() {
        return this.pythonObject;
    }

    /** Part of JCC Python interface to object */
    public void finalize()
            throws Throwable {
        pythonDecRef();
    }

    /** Part of JCC Python interface to object */
    public native void pythonDecRef();

    /** {@inheritDoc} */
    @Override
    public native EventDetector getDetector();

    /** {@inheritDoc} */
    @Override
    public native void init(SpacecraftState s0, AbsoluteDate t);

    /** {@inheritDoc} */
    @Override
    public native void reset(SpacecraftState state, AbsoluteDate target);

    /** {@inheritDoc} */
    @Override
    public native boolean dependsOnTimeOnly();

    /** {@inheritDoc} */
    @Override
    public native double g(SpacecraftState s);

    /** {@inheritDoc} */
    @Override
    public native EventHandler getHandler();

    /** {@inheritDoc} */
    @Override
    public native void finish(SpacecraftState state);

    /** {@inheritDoc} */
    @Override
    public native EventDetectionSettings getDetectionSettings();
}
