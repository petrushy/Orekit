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

// this file was created by SSC 2022 and is largely a derived work from the
// original java class/interface

package org.orekit.forces.maneuvers.trigger;

import org.orekit.propagation.SpacecraftState;

public class PythonManeuverTriggersResetter implements ManeuverTriggersResetter {
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
     * Observe a maneuver trigger.
     * <p>
     * The {@code start} parameter corresponds to physical flow of time
     * from past to future, not to propagation direction which can be backward.
     * This means that during forward propagations, the first call will have
     * {@code start} set to {@code true} and the second call will have
     * {@code start} set to {@code false}, whereas in backward propagation,
     * the first call will have {@code start} set to {@code false} and the second
     * call will have {@code start} set to {@code true}.
     * </p>
     *
     * @param state spacecraft state at trigger date (before applying the maneuver)
     * @param start if true, the trigger is the start of the maneuver
     */
    @Override
    public void maneuverTriggered(SpacecraftState state, boolean start) {

    }

    /**
     * Reset state as a maneuver triggers.
     *
     * @param state spacecraft state at trigger date
     * @return reset state taking into account maneuver start/stop
     */
    @Override
    public native SpacecraftState resetState(SpacecraftState state);
}
