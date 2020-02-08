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


package org.orekit.estimation.measurements.generation;

import org.orekit.estimation.measurements.EstimationModifier;
import org.orekit.estimation.measurements.ObservedMeasurement;
import org.orekit.propagation.SpacecraftState;

import org.orekit.time.AbsoluteDate;

import java.util.List;

public class PythonMeasurementBuilderI<T extends ObservedMeasurement<T>> implements MeasurementBuilder<T> {
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
     * Initialize builder at the start of a measurements generation.
     * <p>
     * This method is called once at the start of the measurements generation. It
     * may be used by the builder to initialize some internal data
     * if needed, typically setting up parameters reference dates.
     * </p>
     *
     * @param start start of the measurements time span
     * @param end   end of the measurements time span
     */
    @Override
    public native void init(AbsoluteDate start, AbsoluteDate end);

    /**
     * Add a modifier.
     *
     * @param modifier modifier to add
     */
    @Override
    public native void addModifier(EstimationModifier<T> modifier);

    /**
     * Get the modifiers that apply to a measurement.
     *
     * @return modifiers that apply to a measurement
     * @see #addModifier(EstimationModifier)
     */
    @Override
    public native List<EstimationModifier<T>> getModifiers();

    /**
     * Generate a single measurement.
     *
     * @param states all spacecraft states (i.e. including ones that may not be relevant for the current builder)
     * @return generated measurement
     */
    @Override
    public native T build(SpacecraftState[] states);
}
