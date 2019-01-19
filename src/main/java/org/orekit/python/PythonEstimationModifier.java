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

package org.orekit.python;

import org.orekit.estimation.measurements.EstimatedMeasurement;
import org.orekit.estimation.measurements.EstimationModifier;
import org.orekit.estimation.measurements.ObservedMeasurement;
import org.orekit.utils.ParameterDriver;

import java.util.List;

public class PythonEstimationModifier<T extends ObservedMeasurement<T>> implements EstimationModifier<T> {
    /**
     * Get the drivers for this modifier parameters.
     *
     * @return drivers for this modifier parameters
     */
    @Override
    public native List<ParameterDriver> getParametersDrivers();

    /**
     * Apply a modifier to an estimated measurement.
     *
     * @param estimated estimated measurement to modify
     */
    @Override
    public native void modify(EstimatedMeasurement<T> estimated);
}
