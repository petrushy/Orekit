/* Copyright 2002-2020 CS GROUP
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


// this file was created by SCC 2020 and is largely a derived work from the
// original java class/interface


package org.orekit.forces.maneuvers.propulsion;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.geometry.euclidean.threed.FieldVector3D;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.SpacecraftState;
import org.orekit.time.AbsoluteDate;

public class PythonThrustPropulsionModel implements ThrustPropulsionModel {
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

    @Override
    public native void init(SpacecraftState initialState, AbsoluteDate target);

    /**
     * Get the thrust vector in spacecraft frame (N).
     *
     * @param s current spacecraft state
     * @return thrust vector in spacecraft frame (N)
     */
    @Override
    public native Vector3D getThrustVector(SpacecraftState s);

    /**
     * Get the flow rate (kg/s).
     *
     * @param s current spacecraft state
     * @return flow rate (kg/s)
     */
    @Override
    public native double getFlowRate(SpacecraftState s);

    /**
     * Get the thrust vector in spacecraft frame (N).
     *
     * @param s          current spacecraft state
     * @param parameters propulsion model parameters
     * @return thrust vector in spacecraft frame (N)
     */
    @Override
    public native Vector3D getThrustVector(SpacecraftState s, double[] parameters);

    /**
     * Get the flow rate (kg/s).
     *
     * @param s          current spacecraft state
     * @param parameters propulsion model parameters
     * @return flow rate (kg/s)
     */
    @Override
    public native double getFlowRate(SpacecraftState s, double[] parameters);

    /**
     * Get the thrust vector in spacecraft frame (N).
     *
     * @param s          current spacecraft state
     * @param parameters propulsion model parameters
     * @return thrust vector in spacecraft frame (N)
     */
    @Override
    public <T extends CalculusFieldElement<T>> FieldVector3D<T> getThrustVector(FieldSpacecraftState<T> s, T[] parameters) {
        return this.getThrustVector_FT(s, parameters);
    }

    public native <T extends CalculusFieldElement<T>> FieldVector3D<T> getThrustVector_FT(FieldSpacecraftState<T> s, T[] parameters);

    /**
     * Get the flow rate (kg/s).
     *
     * @param s          current spacecraft state
     * @param parameters propulsion model parameters
     * @return flow rate (kg/s)
     */
    @Override
    public <T extends CalculusFieldElement<T>> T getFlowRate(FieldSpacecraftState<T> s, T[] parameters) {
        return this.getFlowRate_FT(s, parameters);
    }

    public native <T extends CalculusFieldElement<T>> T getFlowRate_FT(FieldSpacecraftState<T> s, T[] parameters);
}
