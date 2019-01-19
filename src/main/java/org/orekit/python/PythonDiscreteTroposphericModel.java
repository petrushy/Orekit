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


// TODO: Same name of methods!

package org.orekit.python;

import org.hipparchus.Field;
import org.hipparchus.RealFieldElement;
import org.orekit.models.earth.DiscreteTroposphericModel;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.FieldAbsoluteDate;
import org.orekit.utils.ParameterDriver;

import java.util.List;

public class PythonDiscreteTroposphericModel implements DiscreteTroposphericModel {
    /**
     * Calculates the tropospheric path delay for the signal path from a ground
     * station to a satellite.
     *
     * @param elevation  the elevation of the satellite, in radians
     * @param height     the height of the station in m above sea level
     * @param parameters tropospheric model parameters.
     * @param date       current date
     * @return the path delay due to the troposphere in m
     */
    @Override
    public native double pathDelay(double elevation, double height, double[] parameters, AbsoluteDate date);

    /**
     * Calculates the tropospheric path delay for the signal path from a ground
     * station to a satellite.
     *
     * @param elevation  the elevation of the satellite, in radians
     * @param height     the height of the station in m above sea level
     * @param parameters tropospheric model parameters.
     * @param date       current date
     * @return the path delay due to the troposphere in m
     */
    @Override
    public native <T extends RealFieldElement<T>> T pathDelay(T elevation, T height, T[] parameters, FieldAbsoluteDate<T> date);

    /**
     * This method allows the  computation of the zenith hydrostatic and
     * zenith wet delay. The resulting element is an array having the following form:
     * <ul>
     * <li>double[0] = D<sub>hz</sub> -&gt zenith hydrostatic delay
     * <li>double[1] = D<sub>wz</sub> -&gt zenith wet delay
     * </ul>
     *
     * @param height     the height of the station in m above sea level.
     * @param parameters tropospheric model parameters.
     * @param date       current date
     * @return a two components array containing the zenith hydrostatic and wet delays.
     */
    @Override
    public native double[] computeZenithDelay(double height, double[] parameters, AbsoluteDate date);

    /**
     * This method allows the  computation of the zenith hydrostatic and
     * zenith wet delay. The resulting element is an array having the following form:
     * <ul>
     * <li>T[0] = D<sub>hz</sub> -&gt zenith hydrostatic delay
     * <li>T[1] = D<sub>wz</sub> -&gt zenith wet delay
     * </ul>
     *
     * @param height     the height of the station in m above sea level.
     * @param parameters tropospheric model parameters.
     * @param date       current date
     * @return a two components array containing the zenith hydrostatic and wet delays.
     */
    @Override
    public native <T extends RealFieldElement<T>> T[] computeZenithDelay(T height, T[] parameters, FieldAbsoluteDate<T> date);

    /**
     * This method allows the computation of the hydrostatic and
     * wet mapping functions. The resulting element is an array having the following form:
     * <ul>
     * <li>double[0] = m<sub>h</sub>(e) -&gt hydrostatic mapping function
     * <li>double[1] = m<sub>w</sub>(e) -&gt wet mapping function
     * </ul>
     *
     * @param elevation  the elevation of the satellite, in radians.
     * @param height     the height of the station in m above sea level.
     * @param parameters tropospheric model parameters.
     * @param date       current date
     * @return a two components array containing the hydrostatic and wet mapping functions.
     */
    @Override
    public native double[] mappingFactors(double elevation, double height, double[] parameters, AbsoluteDate date);

    /**
     * This method allows the computation of the hydrostatic and
     * wet mapping functions. The resulting element is an array having the following form:
     * <ul>
     * <li>T[0] = m<sub>h</sub>(e) -&gt hydrostatic mapping function
     * <li>T[1] = m<sub>w</sub>(e) -&gt wet mapping function
     * </ul>
     *
     * @param elevation  the elevation of the satellite, in radians.
     * @param height     the height of the station in m above sea level.
     * @param parameters tropospheric model parameters.
     * @param date       current date
     * @return a two components array containing the hydrostatic and wet mapping functions.
     */
    @Override
    public native <T extends RealFieldElement<T>> T[] mappingFactors(T elevation, T height, T[] parameters, FieldAbsoluteDate<T> date);

    /**
     * Get the drivers for tropospheric model parameters.
     *
     * @return drivers for tropospheric model parameters
     */
    @Override
    public native List<ParameterDriver> getParametersDrivers();

    /**
     * Get tropospheric model parameters.
     *
     * @return tropospheric model parameters
     */
    @Override
    public native double[] getParameters();

    /**
     * Get tropospheric model parameters.
     *
     * @param field field to which the elements belong
     * @return tropospheric model parameters
     */
    @Override
    public native <T extends RealFieldElement<T>> T[] getParameters(Field<T> field);
}
