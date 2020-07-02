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

import org.hipparchus.RealFieldElement;
import org.hipparchus.geometry.euclidean.threed.FieldVector3D;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.attitudes.Attitude;
import org.orekit.attitudes.FieldAttitude;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.SpacecraftState;
import org.orekit.time.AbsoluteDate;
import org.orekit.utils.ParameterDriver;

public class PythonAbstractConstantThrustPropulsionModel extends AbstractConstantThrustPropulsionModel implements ThrustPropulsionModel  {


    /** Part of JCC Python interface to object */
    private long pythonObject;

    /**
     * Generic constructor.
     *
     * @param thrust    initial thrust value (N)
     * @param isp       initial isp value (s)
     * @param direction initial thrust direction in S/C frame
     * @param name      name of the maneuver
     */
    public PythonAbstractConstantThrustPropulsionModel(double thrust, double isp, Vector3D direction, String name) {
        super(thrust, isp, direction, name);
    }

    /**
     * Get the thrust vector in spacecraft frame (N).
     * Here it does not depend on current S/C state.
     *
     * @return thrust vector in spacecraft frame (N)
     */
    @Override
    public native Vector3D getThrustVector();

    /**
     * Get the flow rate (kg/s).
     * Here it does not depend on current S/C.
     *
     * @return flow rate (kg/s)
     */
    @Override
    public native double getFlowRate();

    /**
     * Get the thrust vector in spacecraft frame (N).
     * Here it does not depend on current S/C state.
     *
     * @param parameters propulsion model parameters
     * @return thrust vector in spacecraft frame (N)
     */
    @Override
    public native Vector3D getThrustVector(double[] parameters);

    /**
     * Get the flow rate (kg/s).
     * Here it does not depend on current S/C state.
     *
     * @param parameters propulsion model parameters
     * @return flow rate (kg/s)
     */
    @Override
    public native double getFlowRate(double[] parameters);

    /**
     * Get the thrust vector in spacecraft frame (N).
     * Here it does not depend on current S/C state.
     *
     * @param parameters propulsion model parameters
     * @return thrust vector in spacecraft frame (N)
     */
    @Override
    public <T extends RealFieldElement<T>> FieldVector3D<T> getThrustVector(T[] parameters) {
        return this.getThrustVector_T(parameters);
    }

    /**
     * Get the thrust vector in spacecraft frame (N).
     * Here it does not depend on current S/C state.
     *
     * @param parameters propulsion model parameters
     * @return thrust vector in spacecraft frame (N)
     */

    public native <T extends RealFieldElement<T>> FieldVector3D<T> getThrustVector_T(T[] parameters);

    /**
     * Get the flow rate (kg/s).
     * Here it does not depend on current S/C state.
     *
     * @param parameters propulsion model parameters
     * @return flow rate (kg/s)
     */
    @Override
    public <T extends RealFieldElement<T>> T getFlowRate(T[] parameters) {
        return this.getFlowRate_T(parameters);
    }


    /**
     * Get the flow rate (kg/s).
     * Here it does not depend on current S/C state.
     *
     * @param parameters propulsion model parameters
     * @return flow rate (kg/s)
     */
    public native <T extends RealFieldElement<T>> T getFlowRate_T(T[] parameters);

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
     * Initialization method.
     * Called in when Maneuver.init(...) is called (from ForceModel.init(...))
     *
     * @param initialState initial spacecraft state (at the start of propagation).
     * @param target       date of propagation. Not equal to {@code initialState.getDate()}.
     */
    @Override
    public void init(SpacecraftState initialState, AbsoluteDate target) {

    }
}
