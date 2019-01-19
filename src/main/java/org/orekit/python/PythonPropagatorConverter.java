/* Copyright 2002-2019 CS Systèmes d'Information
 * Licensed to CS Systèmes d'Information (CS) under one or more
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
// this file was created by SCC 2019 and is largely a derived work from the
// original java class/interface

package org.orekit.python;

import org.orekit.propagation.Propagator;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.conversion.PropagatorConverter;

import java.util.List;

public class PythonPropagatorConverter implements PropagatorConverter {

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
     * Convert a propagator into another one.
     *
     * @param source         propagator to convert
     * @param timeSpan       time span considered for conversion
     * @param nbPoints       number of points for sampling over the time span
     * @param freeParameters names of the free parameters
     * @return adapted propagator
     */
    @Override
    public Propagator convert(Propagator source, double timeSpan, int nbPoints, List<String> freeParameters) {
        return null;
    }

    /**
     * Convert a propagator into another one.
     *
     * @param source         propagator to convert
     * @param timeSpan       time span considered for conversion
     * @param nbPoints       number of points for sampling over the time span
     * @param freeParameters names of the free parameters
     * @return adapted propagator
     */
    @Override
    public Propagator convert(Propagator source, double timeSpan, int nbPoints, String... freeParameters) {
        return null;
    }

    /**
     * Find the propagator that minimize the mean square error for a sample of {@link SpacecraftState states}.
     *
     * @param states         spacecraft states sample to fit
     * @param positionOnly   if true, consider only position data otherwise both position and velocity are used
     * @param freeParameters names of the free parameters
     * @return adapted propagator
     */
    @Override
    public Propagator convert(List<SpacecraftState> states, boolean positionOnly, List<String> freeParameters) {
        return null;
    }

    /**
     * Find the propagator that minimize the mean square error for a sample of {@link SpacecraftState states}.
     *
     * @param states         spacecraft states sample to fit
     * @param positionOnly   if true, consider only position data otherwise both position and velocity are used
     * @param freeParameters names of the free parameters
     * @return adapted propagator
     */
    @Override
    public Propagator convert(List<SpacecraftState> states, boolean positionOnly, String... freeParameters) {
        return null;
    }
}
