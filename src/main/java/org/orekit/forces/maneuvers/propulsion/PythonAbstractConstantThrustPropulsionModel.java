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
import org.orekit.propagation.SpacecraftState;
import org.orekit.time.AbsoluteDate;
import org.orekit.utils.ParameterDriver;

import java.util.List;

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

    @Override
    public Vector3D getInitialThrustVector() {
        return super.getInitialThrustVector();
    }


    /** {@inheritDoc} */
    @Override
    public native Vector3D getThrustVector();

    // TODO: Check how to implement difference on these
    @Override
    public native Vector3D getThrustVector(AbsoluteDate date);

    /** {@inheritDoc} */
    @Override
    public native double getFlowRate();

    /** {@inheritDoc} */
    @Override
    public native double getFlowRate(AbsoluteDate date);

    /** {@inheritDoc} */
    @Override
    public native Vector3D getThrustVector(double[] parameters);

    /** {@inheritDoc} */
    @Override
    public native double getFlowRate(double[] parameters);

    /** {@inheritDoc} */
    @Override
    public <T extends CalculusFieldElement<T>> FieldVector3D<T> getThrustVector(T[] parameters) {
        return this.getThrustVector_T(parameters);
    }

    /** {@inheritDoc} */
    public native <T extends CalculusFieldElement<T>> FieldVector3D<T> getThrustVector_T(T[] parameters);

    /** {@inheritDoc} */
    @Override
    public <T extends CalculusFieldElement<T>> T getFlowRate(T[] parameters) {
        return this.getFlowRate_T(parameters);
    }


    /** {@inheritDoc} */
    public native <T extends CalculusFieldElement<T>> T getFlowRate_T(T[] parameters);

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
    public native void init(SpacecraftState initialState, AbsoluteDate target);

    /** {@inheritDoc} */
    @Override
    public List<ParameterDriver> getParametersDrivers() {
        return null;
    }
}
