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

// this file was created by SCC 2022 and is largely a derived work from the
// original java class/interface

package org.orekit.forces.maneuvers.trigger;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.Field;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.events.EventDetector;
import org.orekit.propagation.events.FieldEventDetector;

import java.util.stream.Stream;

public class PythonAbstractManeuverTriggers extends AbstractManeuverTriggers {
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
     * Method to check if the thruster is firing on initialization. can be called by
     * sub classes
     *
     * @param initialState initial spacecraft state
     * @param isForward    if true, propagation will be in the forward direction
     * @return true if firing in propagation direction
     */
    @Override
    public native boolean isFiringOnInitialState(SpacecraftState initialState, boolean isForward);

    /**
     * Get the event detectors associated with the triggers.
     *
     * @return the event detectors
     */
    @Override
    public native Stream<EventDetector> getEventsDetectors();

    /**
     * Get the event detectors associated with the triggers.
     *
     * @param field field to which the state belongs
     * @return the event detectors
     */
    @Override
    public native <T extends CalculusFieldElement<T>> Stream<FieldEventDetector<T>> getFieldEventsDetectors(Field<T> field);
}
