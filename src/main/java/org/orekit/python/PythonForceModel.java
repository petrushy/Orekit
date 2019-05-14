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

import org.hipparchus.Field;
import org.hipparchus.RealFieldElement;
import org.hipparchus.geometry.euclidean.threed.FieldVector3D;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.forces.ForceModel;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.events.EventDetector;
import org.orekit.propagation.events.FieldEventDetector;
import org.orekit.propagation.numerical.FieldTimeDerivativesEquations;
import org.orekit.propagation.numerical.TimeDerivativesEquations;
import org.orekit.time.AbsoluteDate;
import org.orekit.utils.ParameterDriver;

import java.util.stream.Stream;

// TODO: CHECK HOW TO DEAL WITH METHODS OF SAME NAME!!

public class PythonForceModel implements ForceModel {

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
     * Initialize the force model at the start of propagation. This method will be called
     * before any calls to {@link #addContribution(SpacecraftState, TimeDerivativesEquations)},
     * {@link #addContribution(FieldSpacecraftState, FieldTimeDerivativesEquations)},
     * {@link #acceleration(SpacecraftState, double[])} or {@link #acceleration(FieldSpacecraftState, RealFieldElement[])}
     *
     * <p> The default implementation of this method does nothing.</p>
     *
     * @param initialState spacecraft state at the start of propagation.
     * @param target       date of propagation. Not equal to {@code initialState.getDate()}.
     */
    @Override
    public native void init(SpacecraftState initialState, AbsoluteDate target);

    /**
     * Compute the contribution of the force model to the perturbing
     * acceleration.
     * <p>
     * The default implementation simply adds the {@link #acceleration(SpacecraftState, double[]) acceleration}
     * as a non-Keplerian acceleration.
     * </p>
     *
     * @param s     current state information: date, kinematics, attitude
     * @param adder object where the contribution should be added
     */
    @Override
    public native void addContribution(SpacecraftState s, TimeDerivativesEquations adder);

    /**
     * Compute the contribution of the force model to the perturbing
     * acceleration.
     *
     * @param s     current state information: date, kinematics, attitude
     * @param adder object where the contribution should be added
     */
    @Override
    public <T extends RealFieldElement<T>> void addContribution(FieldSpacecraftState<T> s, FieldTimeDerivativesEquations<T> adder) {
       this.addFieldContribution(s, adder);
    }

    public native <T extends RealFieldElement<T>> void addFieldContribution(FieldSpacecraftState<T> s, FieldTimeDerivativesEquations<T> adder);

    /**
     * Get force model parameters.
     *
     * @return force model parameters
     * @since 9.0
     */
    @Override
    public native double[] getParameters();

    /**
     * Get force model parameters.
     *
     * @param field field to which the elements belong
     * @return force model parameters
     * @since 9.0
     */
    @Override
    public <T extends RealFieldElement<T>> T[] getParameters(Field<T> field) {
        return this.getParameters_F(field);
    }

    /**
     * Get force model parameters.
     *
     * @param field field to which the elements belong
     * @return force model parameters
     * @since 9.0
     */
    public native <T extends RealFieldElement<T>> T[] getParameters_F(Field<T> field);

    /**
     * Check if force models depends on position only.
     *
     * @return true if force model depends on position only, false
     * if it depends on velocity, either directly or due to a dependency
     * on attitude
     * @since 9.0
     */
    @Override
    public native boolean dependsOnPositionOnly();

    /**
     * Compute acceleration.
     *
     * @param s          current state information: date, kinematics, attitude
     * @param parameters values of the force model parameters
     * @return acceleration in same frame as state
     * @since 9.0
     */
    @Override
    public native Vector3D acceleration(SpacecraftState s, double[] parameters);

    /**
     * Compute acceleration.
     *
     * @param s          current state information: date, kinematics, attitude
     * @param parameters values of the force model parameters
     * @return acceleration in same frame as state
     * @since 9.0
     */
    @Override
    public native <T extends RealFieldElement<T>> FieldVector3D<T> acceleration(FieldSpacecraftState<T> s, T[] parameters);

    /**
     * Get the discrete events related to the model.
     *
     * @return stream of events detectors
     */
    @Override
    public native Stream<EventDetector> getEventsDetectors();

    /**
     * Get the discrete events related to the model.
     *
     * @param field field to which the state belongs
     * @return stream of events detectors
     */
    @Override
    public native <T extends RealFieldElement<T>> Stream<FieldEventDetector<T>> getFieldEventsDetectors(Field<T> field);

    /**
     * Get the drivers for force model parameters.
     *
     * @return drivers for force model parameters
     * @since 8.0
     */
    @Override
    public native ParameterDriver[] getParametersDrivers();

    /**
     * Get parameter value from its name.
     *
     * @param name parameter name
     * @return parameter value
     * @since 8.0
     */
    @Override
    public native ParameterDriver getParameterDriver(String name);

    /**
     * Check if a parameter is supported.
     * <p>Supported parameters are those listed by {@link #getParametersDrivers()}.</p>
     *
     * @param name parameter name to check
     * @return true if the parameter is supported
     * @see #getParametersDrivers()
     */
    @Override
    public native boolean isSupported(String name);
}
