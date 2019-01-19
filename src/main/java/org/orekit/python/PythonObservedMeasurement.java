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

import org.orekit.estimation.measurements.*;
import org.orekit.propagation.Propagator;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.conversion.PropagatorBuilder;
import org.orekit.time.AbsoluteDate;
import org.orekit.utils.ParameterDriver;

import java.util.List;
import java.util.SortedSet;

public class PythonObservedMeasurement<T extends ObservedMeasurement<T>> implements ObservedMeasurement<T> {
    /**
     * Enable or disable a measurement.
     * <p>
     * Disabling a measurement allow to not consider it at
     * one stage of the orbit determination (for example when
     * it appears to be an outlier as per current estimated
     * covariance).
     * </p>
     *
     * @param enabled if true the measurement will be enabled,
     *                otherwise it will be disabled
     */
    @Override
    public native void setEnabled(boolean enabled);

    /**
     * Check if a measurement is enabled.
     *
     * @return true if the measurement is enabled
     */
    @Override
    public native boolean isEnabled();

    /**
     * Get the dimension of the measurement.
     * <p>
     * Dimension is the size of the array containing the
     * value. It will be one for a scalar measurement like
     * a range or range-rate, but 6 for a position-velocity
     * measurement.
     * </p>
     *
     * @return dimension of the measurement
     */
    @Override
    public native int getDimension();

    /**
     * Get the theoretical standard deviation.
     * <p>
     * The theoretical standard deviation is a theoretical value
     * used for normalizing the residuals. It acts as a weighting
     * factor to mix appropriately measurements with different units
     * and different accuracy. The value has the same dimension as
     * the measurement itself (i.e. when a residual is divided by
     * this value, it becomes dimensionless).
     * </p>
     *
     * @return expected standard deviation
     * @see #getBaseWeight()
     */
    @Override
    public native double[] getTheoreticalStandardDeviation();

    /**
     * Get the base weight associated with the measurement
     * <p>
     * The base weight is used on residuals already normalized thanks to
     * {@link #getTheoreticalStandardDeviation()} to increase or
     * decrease relative effect of some measurements with respect to
     * other measurements. It is a dimensionless value, typically between
     * 0 and 1 (but it can really have any non-negative value).
     * </p>
     *
     * @return base weight
     * @see #getTheoreticalStandardDeviation()
     * @see EstimatedMeasurement#getCurrentWeight()
     */
    @Override
    public native double[] getBaseWeight();

    /**
     * Add a modifier.
     * <p>
     * The modifiers are applied in the order in which they are added in order to
     * {@link #estimate(int, int, SpacecraftState[]) estimate} the measurement.
     * </p>
     *
     * @param modifier modifier to add
     * @see #getModifiers()
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
     * Get the drivers for this measurement parameters, including its modifiers parameters.
     *
     * @return drivers for this measurement parameters, including its modifiers parameters
     */
    @Override
    public native List<ParameterDriver> getParametersDrivers();

    /**
     * Get the indices of the {@link Propagator propagators}
     * related to this measurement.
     * <p>
     * The propagators are indexed starting from 0 and ordered according to
     * the order of the {@link PropagatorBuilder
     * propagators builders} in the orbit determination engine used.
     * </p>
     *
     * @return indices of the {@link Propagator propagators}
     * related to this measurement
     * @since 9.0
     * @deprecated as of 9.3, replaced by {@link #getSatellites()}
     */
    @Override
    public native List<Integer> getPropagatorsIndices();

    /**
     * Get the satellites related to this measurement.
     *
     * @return satellites related to this measurement
     * @since 9.3
     */
    @Override
    public native List<ObservableSatellite> getSatellites();

    /**
     * Estimate the theoretical value of the measurement.
     * <p>
     * The estimated value is the <em>combination</em> of the raw estimated
     * value and all the modifiers that apply to the measurement.
     * </p>
     *
     * @param iteration  iteration number
     * @param evaluation evaluations number
     * @param states     orbital states at measurement date
     * @return estimated measurement
     */
    @Override
    public native EstimatedMeasurement<T> estimate(int iteration, int evaluation, SpacecraftState[] states);

    /**
     * Get the observed value.
     * <p>
     * The observed value is the value that was measured by the instrument.
     * </p>
     *
     * @return observed value (array of size {@link #getDimension()}
     */
    @Override
    public native double[] getObservedValue();

    /**
     * {@inheritDoc}
     * <p>
     * Measurements comparison is primarily chronological, but measurements
     * with the same date are sorted based on the observed value. Even if they
     * have the same value too, they will <em>not</em> be considered equal if they
     * correspond to different instances. This allows to store measurements in
     * {@link SortedSet SortedSet} without losing any measurements, even
     * redundant ones.
     * </p>
     *
     * @param other
     */
    @Override
    public native int compareTo(ComparableMeasurement other);

    /**
     * Get the date.
     *
     * @return date attached to the object
     */
    @Override
    public native AbsoluteDate getDate();
}
