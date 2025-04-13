/* Copyright 2002-2022 CS GROUP
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

// this file was created by SSC 2022 and is largely a derived work from the
// original java class/interface

package org.orekit.propagation.analytical;

import org.hipparchus.analysis.differentiation.Gradient;
import org.orekit.utils.ParameterDriver;

import java.util.List;

public class PythonAbstractAnalyticalGradientConverter extends AbstractAnalyticalGradientConverter {
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
     * Constructor.
     *
     * @param propagator          analytical orbit propagator
     * @param mu                  central attraction coefficient
     * @param freeStateParameters number of free parameters
     */
    public PythonAbstractAnalyticalGradientConverter( AbstractAnalyticalPropagator propagator, final int freeStateParameters) 
    {
        super(propagator, freeStateParameters);
    }

    //** {@inheritDoc} */
    @Override
    public native List<ParameterDriver> getParametersDrivers();

    /** {@inheritDoc} */
    @Override
    public native FieldAbstractAnalyticalPropagator<Gradient> getPropagator();

}
