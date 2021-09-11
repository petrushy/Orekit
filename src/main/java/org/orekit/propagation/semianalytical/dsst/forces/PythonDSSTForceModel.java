/* Copyright 2002-2021 CS GROUP
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

// this file was created by SSC 2021 and is largely a derived work from the
// original java class

package org.orekit.propagation.semianalytical.dsst.forces;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.Field;
import org.orekit.attitudes.AttitudeProvider;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.PropagationType;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.events.EventDetector;
import org.orekit.propagation.events.FieldEventDetector;
import org.orekit.propagation.semianalytical.dsst.utilities.AuxiliaryElements;
import org.orekit.propagation.semianalytical.dsst.utilities.FieldAuxiliaryElements;
import org.orekit.utils.ParameterDriver;

import java.util.List;

public class PythonDSSTForceModel implements DSSTForceModel {
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
     * Performs initialization prior to propagation for the current force model.
     * <p>
     * This method aims at being called at the very beginning of a propagation.
     * </p>
     *
     * @param auxiliaryElements auxiliary elements related to the current orbit
     * @param type              type of the elements used during the propagation
     * @param parameters        values of the force model parameters
     * @return a list of objects that will hold short period terms (the objects
     * are also retained by the force model, which will update them during propagation)
     */
    @Override
    public native List<ShortPeriodTerms> initializeShortPeriodTerms(AuxiliaryElements auxiliaryElements, PropagationType type, double[] parameters);

    /**
     * Performs initialization prior to propagation for the current force model.
     * <p>
     * This method aims at being called at the very beginning of a propagation.
     * </p>
     *
     * @param auxiliaryElements auxiliary elements related to the current orbit
     * @param type              type of the elements used during the propagation
     * @param parameters        values of the force model parameters
     * @return a list of objects that will hold short period terms (the objects
     * are also retained by the force model, which will update them during propagation)
     */

    public native <T extends CalculusFieldElement<T>> List<FieldShortPeriodTerms<T>> initializeShortPeriodTerms_FPT(FieldAuxiliaryElements<T> auxiliaryElements, PropagationType type, T[] parameters);

    @Override
    public <T extends CalculusFieldElement<T>> List<FieldShortPeriodTerms<T>> initializeShortPeriodTerms(FieldAuxiliaryElements<T> auxiliaryElements, PropagationType type, T[] parameters) {
        return this.initializeShortPeriodTerms_FPT(auxiliaryElements, type, parameters);
    }

    /**
     * Computes the mean equinoctial elements rates da<sub>i</sub> / dt.
     *
     * @param state             current state information: date, kinematics, attitude
     * @param auxiliaryElements auxiliary elements related to the current orbit
     * @param parameters        values of the force model parameters
     * @return the mean element rates dai/dt
     */
    @Override
    public native double[] getMeanElementRate(SpacecraftState state, AuxiliaryElements auxiliaryElements, double[] parameters);

    /**
     * Computes the mean equinoctial elements rates da<sub>i</sub> / dt.
     *
     * @param state             current state information: date, kinematics, attitude
     * @param auxiliaryElements auxiliary elements related to the current orbit
     * @param parameters        values of the force model parameters
     * @return the mean element rates dai/dt
     */
    public native <T extends CalculusFieldElement<T>> T[] getMeanElementRate_FFT(FieldSpacecraftState<T> state, FieldAuxiliaryElements<T> auxiliaryElements, T[] parameters);

    @Override
    public <T extends CalculusFieldElement<T>> T[] getMeanElementRate(FieldSpacecraftState<T> state, FieldAuxiliaryElements<T> auxiliaryElements, T[] parameters) {
        return this.getMeanElementRate_FFT(state, auxiliaryElements, parameters);
    }


    /**
     * Get the discrete events related to the model.
     *
     * @return array of events detectors or null if the model is not
     * related to any discrete events
     */
    @Override
    public native EventDetector[] getEventsDetectors();

    /**
     * Get the discrete events related to the model.
     *
     * @param field field used by default
     * @return array of events detectors or null if the model is not
     * related to any discrete events
     */
    @Override
    public native <T extends CalculusFieldElement<T>> FieldEventDetector<T>[] getFieldEventsDetectors(Field<T> field);

    /**
     * Register an attitude provider.
     * <p>
     * Register an attitude provider that can be used by the force model.
     * </p>
     *
     * @param provider the {@link AttitudeProvider}
     */
    @Override
    public native void registerAttitudeProvider(AttitudeProvider provider);

    /**
     * Update the short period terms.
     * <p>
     * The {@link ShortPeriodTerms short period terms} that will be updated
     * are the ones that were returned during the call to {@link
     * #initializeShortPeriodTerms(AuxiliaryElements, PropagationType, double[])}.
     * </p>
     *
     * @param parameters values of the force model parameters
     * @param meanStates mean states information: date, kinematics, attitude
     */
    @Override
    public native void updateShortPeriodTerms(double[] parameters, SpacecraftState... meanStates);

    /**
     * Update the short period terms.
     * <p>
     * The {@link ShortPeriodTerms short period terms} that will be updated
     * are the ones that were returned during the call to {@link
     * #initializeShortPeriodTerms(AuxiliaryElements, PropagationType, double[])}.
     * </p>
     *
     * @param parameters values of the force model parameters
     * @param meanStates mean states information: date, kinematics, attitude
     */
    public native <T extends CalculusFieldElement<T>> void updateShortPeriodTerms_TF(T[] parameters, FieldSpacecraftState<T>... meanStates);

    @Override
    public <T extends CalculusFieldElement<T>> void updateShortPeriodTerms(T[] parameters, FieldSpacecraftState<T>... meanStates) {
        this.updateShortPeriodTerms_TF(parameters, meanStates);
    }

    /**
     * Get the drivers for force model parameters.
     *
     * @return drivers for force model parameters
     */
    @Override
    public native List<ParameterDriver> getParametersDrivers();
}
