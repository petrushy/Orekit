/* Copyright 2002-2020 CS Group
 * Licensed to CS Group (CS) under one or more
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

// this file was created by SCC 2020 and is largely a derived work from the
// original java class/interface

package org.orekit.forces.gravity.potential;

import org.orekit.annotation.DefaultDataContext;
import org.orekit.errors.OrekitException;
import org.orekit.time.TimeScale;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

public class PythonPotentialCoefficientsReader extends PotentialCoefficientsReader {

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
     * Simple constructor.
     * <p>Build an uninitialized reader.</p>
     *
     * <p>This constructor uses the {@link DataContext#getDefault() default data context}.
     *
     * @param supportedNames             regular expression for supported files names
     * @param missingCoefficientsAllowed allow missing coefficients in the input data
     * @see #PotentialCoefficientsReader(String, boolean, TimeScale)
     */

    @DefaultDataContext
    public PythonPotentialCoefficientsReader(String supportedNames, boolean missingCoefficientsAllowed) {
        super(supportedNames, missingCoefficientsAllowed);
    }

    /**
     * Simple constructor.
     * <p>Build an uninitialized reader.</p>
     *
     * @param supportedNames             regular expression for supported files names
     * @param missingCoefficientsAllowed allow missing coefficients in the input data
     * @param timeScale                  to use when parsing dates.
     * @since 10.1
     */
    public PythonPotentialCoefficientsReader(String supportedNames, boolean missingCoefficientsAllowed, TimeScale timeScale) {
        super(supportedNames, missingCoefficientsAllowed, timeScale);
    }

    /**
     * {@inheritDoc}
     *
     * @param input
     * @param name
     */
    @Override
    public native void loadData(InputStream input, String name) throws IOException, ParseException, OrekitException;

    /**
     * Get a provider for read spherical harmonics coefficients.
     *
     * @param wantNormalized if true, the provider will provide normalized coefficients,
     *                       otherwise it will provide un-normalized coefficients
     * @param degree         maximal degree
     * @param order          maximal order
     * @return a new provider
     * @see #getConstantProvider(boolean, int, int)
     * @since 6.0
     */
    @Override
    public native RawSphericalHarmonicsProvider getProvider(boolean wantNormalized, int degree, int order);
}
