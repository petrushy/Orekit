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
import org.orekit.bodies.GeodeticPoint;
import org.orekit.frames.TopocentricFrame;
import org.orekit.models.earth.ionosphere.IonosphericModel;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.SpacecraftState;
import org.orekit.time.AbsoluteDate;
import org.orekit.utils.ParameterDriver;

import java.util.List;

public class PythonIonosphericModel implements IonosphericModel {

    private static final long serialVersionUID = 1716300861604915492L;
    
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
     * Calculates the ionospheric path delay for the signal path from a ground
     * station to a satellite.
     * <p>
     * This method is intended to be used for orbit determination issues.
     * In that respect, if the elevation is below 0° the path delay will be equal to zero.
     * </p><p>
     * For individual use of the ionospheric model (i.e. not for orbit determination), another
     * method signature can be implemented to compute the path delay for any elevation angle.
     * </p>
     *
     * @param state      spacecraft state
     * @param baseFrame  base frame associated with the station
     * @param frequency  frequency of the signal in Hz
     * @param parameters ionospheric model parameters
     * @return the path delay due to the ionosphere in m
     */
    @Override
    public double pathDelay(SpacecraftState state, TopocentricFrame baseFrame, double frequency, double[] parameters) {
        return this.pathDelay_STdd(state, baseFrame, frequency, parameters);
    }

    public native double pathDelay_STdd(SpacecraftState state, TopocentricFrame baseFrame, double frequency, double[] parameters);




    /**
     * Calculates the ionospheric path delay for the signal path from a ground
     * station to a satellite.
     * <p>
     * This method is intended to be used for orbit determination issues.
     * In that respect, if the elevation is below 0° the path delay will be equal to zero.
     * </p><p>
     * For individual use of the ionospheric model (i.e. not for orbit determination), another
     * method signature can be implemented to compute the path delay for any elevation angle.
     * </p>
     *
     * @param state      spacecraft state
     * @param baseFrame  base frame associated with the station
     * @param frequency  frequency of the signal in Hz
     * @param parameters ionospheric model parameters
     * @return the path delay due to the ionosphere in m
     */
    @Override
    public <T extends RealFieldElement<T>> T pathDelay(FieldSpacecraftState<T> state, TopocentricFrame baseFrame, double frequency, T[] parameters) {
        return this.pathDelay_FTdT(state, baseFrame, frequency, parameters);
    }

    public native <T extends RealFieldElement<T>> T pathDelay_FTdT(FieldSpacecraftState<T> state, TopocentricFrame baseFrame, double frequency, T[] parameters);

    /**
     * Get the drivers for ionospheric model parameters.
     *
     * @return drivers for ionospheric model parameters
     */
    @Override
    public native List<ParameterDriver> getParametersDrivers();

    /**
     * Get ionospheric model parameters.
     *
     * @return ionospheric model parameters
     */
    @Override
    public native double[] getParameters();

    /**
     * Get ionospheric model parameters.
     *
     * @param field field to which the elements belong
     * @return ionospheric model parameters
     */
    @Override
    public <T extends RealFieldElement<T>> T[] getParameters(Field<T> field) {
        return this.getParameters_F(field);
    }

    public native <T extends RealFieldElement<T>> T[] getParameters_F(Field<T> field);
}
