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

package org.orekit.models.earth.troposphere;

import org.hipparchus.Field;
import org.hipparchus.RealFieldElement;
import org.orekit.models.earth.troposphere.MappingFunction;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.FieldAbsoluteDate;
import org.orekit.utils.ParameterDriver;

import java.util.List;

public class PythonMappingFunction implements MappingFunction {

    private static final long serialVersionUID = -9157284216952908403L;
    
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
    public <T extends RealFieldElement<T>> T[] mappingFactors(T elevation, T height, T[] parameters, FieldAbsoluteDate<T> date) {
        return this.mappingFactors_TTTF(elevation, height, parameters, date);
    }


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
    public native <T extends RealFieldElement<T>> T[] mappingFactors_TTTF(T elevation, T height, T[] parameters, FieldAbsoluteDate<T> date);


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
    public <T extends RealFieldElement<T>> T[] getParameters(Field<T> field) {
        return this.getParameters_F(field);
    }

    /**
     * Get tropospheric model parameters.
     *
     * @param field field to which the elements belong
     * @return tropospheric model parameters
     */
    public native <T extends RealFieldElement<T>> T[] getParameters_F(Field<T> field);
}