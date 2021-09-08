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

package org.orekit.propagation;


import org.hipparchus.CalculusFieldElement;
import org.orekit.attitudes.AttitudeProvider;
import org.orekit.frames.Frame;
import org.orekit.propagation.events.FieldEventDetector;
import org.orekit.propagation.integration.FieldAbstractIntegratedPropagator;
import org.orekit.propagation.integration.FieldAdditionalEquations;
import org.orekit.propagation.sampling.FieldOrekitFixedStepHandler;
import org.orekit.propagation.sampling.FieldOrekitStepHandler;
import org.orekit.propagation.sampling.FieldStepHandlerMultiplexer;
import org.orekit.time.FieldAbsoluteDate;
import org.orekit.utils.TimeStampedFieldPVCoordinates;

import java.util.Collection;
import java.util.List;


public class PythonFieldPropagator<T extends CalculusFieldElement<T>> implements FieldPropagator<T> {

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
     * Set the propagator to master mode with fixed steps.
     * <p>This mode is used when the user needs to have some custom function called at the
     * end of each finalized step during integration. The (master) propagator integration
     * loop calls the (slave) application callback methods at each finalized step.</p>
     *
     * @param h       fixed stepsize (s)
     * @param handler handler called at the end of each finalized step
     * @see #setSlaveMode()
     * @see #setMasterMode(FieldOrekitStepHandler)
     * @see #setEphemerisMode()
     * @see #getMode()
     * @see #MASTER_MODE
     */
    public native void setMasterMode_TF(T h, FieldOrekitFixedStepHandler<T> handler);




    /**
     * Get the multiplexer holding all step handlers.
     *
     * @return multiplexer holding all step handlers
     * @since 11.0
     */
    @Override
    public native FieldStepHandlerMultiplexer<T> getMultiplexer();

    /**
     * Set up an ephemeris generator that will monitor the propagation for building
     * an ephemeris from it once completed.
     *
     * <p>
     * This generator can be used when the user needs fast random access to the orbit
     * state at any time between the initial and target times. A typical example is the
     * implementation of search and iterative algorithms that may navigate forward and
     * backward inside the propagation range before finding their result even if the
     * propagator used is integration-based and only goes from one initial time to one
     * target time.
     * </p>
     * <p>
     * Beware that when used with integration-based propagators, the generator will
     * store <strong>all</strong> intermediate results. It is therefore memory intensive
     * for long integration-based ranges and high precision/short time steps. When
     * used with analytical propagators, the generator only stores start/stop time
     * and a reference to the analytical propagator itself to call it back as needed,
     * so it is less memory intensive.
     * </p>
     * <p>
     * The returned ephemeris generator will be initially empty, it will be filled
     * with propagation data when a subsequent call to either {@link #propagate(FieldAbsoluteDate)
     * propagate(target)} or {@link #propagate(FieldAbsoluteDate, FieldAbsoluteDate)
     * propagate(start, target)} is called. The proper way to use this method is
     * therefore to do:
     * </p>
     * <pre>
     *   FieldEphemerisGenerator&lt;T&gt; generator = propagator.getEphemerisGenerator();
     *   propagator.propagate(target);
     *   FieldBoundedPropagator&lt;T&gt; ephemeris = generator.getGeneratedEphemeris();
     * </pre>
     *
     * @return ephemeris generator
     */
    @Override
    public native FieldEphemerisGenerator<T> getEphemerisGenerator();

    /**
     * Get the propagator initial state.
     *
     * @return initial state
     */
    @Override
    public native FieldSpacecraftState<T> getInitialState();

    /**
     * Reset the propagator initial state.
     *
     * @param state new initial state to consider
     */
    @Override
    public native void resetInitialState(FieldSpacecraftState<T> state);

    /**
     * Add a set of user-specified state parameters to be computed along with the orbit propagation.
     *
     * @param additionalStateProvider provider for additional state
     */
    @Override
    public native void addAdditionalStateProvider(FieldAdditionalStateProvider<T> additionalStateProvider);

    /**
     * Get an unmodifiable list of providers for additional state.
     *
     * @return providers for the additional states
     */
    @Override
    public native List<FieldAdditionalStateProvider<T>> getAdditionalStateProviders();

    /**
     * Check if an additional state is managed.
     * <p>
     * Managed states are states for which the propagators know how to compute
     * its evolution. They correspond to additional states for which an
     * {@link FieldAdditionalStateProvider additional state provider} has been registered
     * by calling the {@link #addAdditionalStateProvider(FieldAdditionalStateProvider)
     * addAdditionalStateProvider} method. If the propagator is an {@link
     * FieldAbstractIntegratedPropagator integrator-based
     * propagator}, the states for which a set of {@link
     * FieldAdditionalEquations additional equations} has
     * been registered by calling the {@link
     * FieldAbstractIntegratedPropagator#addAdditionalEquations(
     *FieldAdditionalEquations) addAdditionalEquations}
     * method are also counted as managed additional states.
     * </p>
     * <p>
     * Additional states that are present in the {@link #getInitialState() initial state}
     * but have no evolution method registered are <em>not</em> considered as managed states.
     * These unmanaged additional states are not lost during propagation, though. Their
     * value will simply be copied unchanged throughout propagation.
     * </p>
     *
     * @param name name of the additional state
     * @return true if the additional state is managed
     */
    @Override
    public native boolean isAdditionalStateManaged(String name);

    /**
     * Get all the names of all managed states.
     *
     * @return names of all managed states
     */
    @Override
    public native String[] getManagedAdditionalStates();

    /**
     * Add an event detector.
     *
     * @param detector event detector to add
     * @see #clearEventsDetectors()
     * @see #getEventsDetectors()
     */
    @Override
    public native <D extends FieldEventDetector<T>> void addEventDetector(D detector);

    /**
     * Get all the events detectors that have been added.
     *
     * @return an unmodifiable collection of the added detectors
     * @see #addEventDetector(FieldEventDetector)
     * @see #clearEventsDetectors()
     */
    @Override
    public native Collection<FieldEventDetector<T>> getEventsDetectors();

    /**
     * Remove all events detectors.
     *
     * @see #addEventDetector(FieldEventDetector)
     * @see #getEventsDetectors()
     */
    @Override
    public native void clearEventsDetectors();

    /**
     * Get attitude provider.
     *
     * @return attitude provider
     */
    @Override
    public native AttitudeProvider getAttitudeProvider();

    /**
     * Set attitude provider.
     *
     * @param attitudeProvider attitude provider
     */
    @Override
    public native void setAttitudeProvider(AttitudeProvider attitudeProvider);

    /**
     * Get the frame in which the orbit is propagated.
     * <p>
     * The propagation frame is the definition frame of the initial
     * state, so this method should be called after this state has
     * been set, otherwise it may return null.
     * </p>
     *
     * @return frame in which the orbit is propagated
     * @see #resetInitialState(FieldSpacecraftState)
     */
    @Override
    public native Frame getFrame();

    /**
     * Propagate towards a target date.
     * <p>Simple propagators use only the target date as the specification for
     * computing the propagated state. More feature rich propagators can consider
     * other information and provide different operating modes or G-stop
     * facilities to stop at pinpointed events occurrences. In these cases, the
     * target date is only a hint, not a mandatory objective.</p>
     *
     * @param target target date towards which orbit state should be propagated
     * @return propagated state
     */
    @Override
    public native FieldSpacecraftState<T> propagate(FieldAbsoluteDate<T> target);

    /**
     * Propagate from a start date towards a target date.
     * <p>Those propagators use a start date and a target date to
     * compute the propagated state. For propagators using event detection mechanism,
     * if the provided start date is different from the initial state date, a first,
     * simple propagation is performed, without processing any event computation.
     * Then complete propagation is performed from start date to target date.</p>
     *
     * @param start  start date from which orbit state should be propagated
     * @param target target date to which orbit state should be propagated
     * @return propagated state
     */
    @Override
    public FieldSpacecraftState<T> propagate(FieldAbsoluteDate<T> start, FieldAbsoluteDate<T> target) {
        return this.propagate_FF(start, target);
    }

    /**
     * Propagate from a start date towards a target date.
     * <p>Those propagators use a start date and a target date to
     * compute the propagated state. For propagators using event detection mechanism,
     * if the provided start date is different from the initial state date, a first,
     * simple propagation is performed, without processing any event computation.
     * Then complete propagation is performed from start date to target date.</p>
     *
     * @param start  start date from which orbit state should be propagated
     * @param target target date to which orbit state should be propagated
     * @return propagated state
     */
    public native FieldSpacecraftState<T> propagate_FF(FieldAbsoluteDate<T> start, FieldAbsoluteDate<T> target);


    /**
     * Get the {@link FieldPVCoordinates} of the body in the selected frame.
     *
     * @param date  current date
     * @param frame the frame where to define the position
     * @return time-stamped position/velocity of the body (m and m/s)
     */
    @Override
    public native TimeStampedFieldPVCoordinates<T> getPVCoordinates(FieldAbsoluteDate<T> date, Frame frame);
}
