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
    /** {@inheritDoc} */
    @Override
    public native List<ShortPeriodTerms> initializeShortPeriodTerms(AuxiliaryElements auxiliaryElements, PropagationType type, double[] parameters);

    /** {@inheritDoc} */
    @Override
    public native <T extends CalculusFieldElement<T>> List<FieldShortPeriodTerms<T>> initializeShortPeriodTerms(FieldAuxiliaryElements<T> auxiliaryElements, PropagationType type, T[] parameters);

    /** {@inheritDoc} */
    @Override
    public native double[] getMeanElementRate(SpacecraftState state, AuxiliaryElements auxiliaryElements, double[] parameters);

    /** {@inheritDoc} */
    @Override
    public native <T extends CalculusFieldElement<T>> T[] getMeanElementRate(FieldSpacecraftState<T> state, FieldAuxiliaryElements<T> auxiliaryElements, T[] parameters);

    /** {@inheritDoc} */
    @Override
    public native void registerAttitudeProvider(AttitudeProvider provider);

    /** {@inheritDoc} */
    @Override
    public native void updateShortPeriodTerms(double[] parameters, SpacecraftState... meanStates);

    /** {@inheritDoc} */
    @Override
    public native <T extends CalculusFieldElement<T>> void updateShortPeriodTerms(T[] parameters, FieldSpacecraftState<T>... meanStates);

    /** {@inheritDoc} */
    @Override
    public native List<ParameterDriver> getParametersDrivers();
}
