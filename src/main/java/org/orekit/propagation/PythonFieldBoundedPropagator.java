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
import org.orekit.time.FieldAbsoluteDate;
import org.orekit.utils.TimeStampedFieldPVCoordinates;

import java.util.Collection;
import java.util.List;

public class PythonFieldBoundedPropagator<T extends CalculusFieldElement<T>> implements FieldBoundedPropagator<T> {
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
     * Get the first date of the range.
     *
     * @return the first date of the range
     */
    @Override
    public native FieldAbsoluteDate<T> getMinDate();

    /**
     * Get the last date of the range.
     *
     * @return the last date of the range
     */
    @Override
    public native FieldAbsoluteDate<T> getMaxDate();

    /**
     * Get the current operating mode of the propagator.
     *
     * @return one of {@link #SLAVE_MODE}, {@link #MASTER_MODE},
     * {@link #EPHEMERIS_GENERATION_MODE}
     * @see #setSlaveMode()
     * @see #setMasterMode(RealFieldElement, FieldOrekitFixedStepHandler)
     * @see #setMasterMode(FieldOrekitStepHandler)
     * @see #setEphemerisMode()
     */
    @Override
    public native int getMode();

    /**
     * Set the propagator to slave mode.
     * <p>This mode is used when the user needs only the final orbit at the target time.
     * The (slave) propagator computes this result and return it to the calling
     * (master) application, without any intermediate feedback.<p>
     * <p>This is the default mode.</p>
     *
     * @see #setMasterMode(CalculusFieldElement, FieldOrekitFixedStepHandler)
     * @see #setMasterMode(FieldOrekitStepHandler)
     * @see #setEphemerisMode()
     * @see #getMode()
     * @see #SLAVE_MODE
     */
    @Override
    public native void setSlaveMode();

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
    @Override
    public void setMasterMode(T h, FieldOrekitFixedStepHandler<T> handler) {
        this.setMasterMode_TF(h, handler);
    }

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
     * Set the propagator to master mode with variable steps.
     * <p>This mode is used when the user needs to have some custom function called at the
     * end of each finalized step during integration. The (master) propagator integration
     * loop calls the (slave) application callback methods at each finalized step.</p>
     *
     * @param handler handler called at the end of each finalized step
     * @see #setSlaveMode()
     * @see #setMasterMode(RealFieldElement, FieldOrekitFixedStepHandler)
     * @see #setEphemerisMode()
     * @see #getMode()
     * @see #MASTER_MODE
     */
    @Override
    public native void setMasterMode(FieldOrekitStepHandler<T> handler);

    /**
     * Set the propagator to ephemeris generation mode.
     * <p>This mode is used when the user needs random access to the orbit state at any time
     * between the initial and target times, and in no sequential order. A typical example is
     * the implementation of search and iterative algorithms that may navigate forward and
     * backward inside the propagation range before finding their result.</p>
     * <p>Beware that since this mode stores <strong>all</strong> intermediate results,
     * it may be memory intensive for long integration ranges and high precision/short
     * time steps.</p>
     *
     * @see #getGeneratedEphemeris()
     * @see #setSlaveMode()
     * @see #setMasterMode(RealFieldElement, FieldOrekitFixedStepHandler)
     * @see #setMasterMode(FieldOrekitStepHandler)
     * @see #getMode()
     * @see #EPHEMERIS_GENERATION_MODE
     */
    @Override
    public native void setEphemerisMode();

    /**
     * Get the ephemeris generated during propagation.
     *
     * @return generated ephemeris
     * @throws IllegalStateException if the propagator was not set in ephemeris
     *                               generation mode before propagation
     * @see #setEphemerisMode()
     */
    @Override
    public native FieldBoundedPropagator<T> getGeneratedEphemeris() throws IllegalStateException;

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
