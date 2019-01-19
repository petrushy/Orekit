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

import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.propagation.numerical.TimeDerivativesEquations;

public class PythonTimeDerivativesEquations implements TimeDerivativesEquations {
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
     * Add the contribution of the Kepler evolution.
     * <p>Since the Kepler evolution is the most important, it should
     * be added after all the other ones, in order to improve
     * numerical accuracy.</p>
     *
     * @param mu central body gravitational constant
     */
    @Override
    public native void addKeplerContribution(double mu);

    /**
     * Add the contribution of a non-Keplerian acceleration.
     *
     * @param gamma acceleration vector in the same inertial frame the spacecraft state is defined in (m/s²)
     * @since 9.0
     */
    @Override
    public native void addNonKeplerianAcceleration(Vector3D gamma);

    /**
     * Add the contribution of the flow rate (dm/dt).
     *
     * @param q the flow rate, must be negative (dm/dt)
     * @throws IllegalArgumentException if flow-rate is positive
     */
    @Override
    public native void addMassDerivative(double q);
}
