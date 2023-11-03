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

/* TODO: Check what is reasonable implementation of this class

 */
package org.orekit.forces.maneuvers.trigger;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.Field;
import org.orekit.propagation.FieldPropagator;
import org.orekit.propagation.events.AbstractDetector;
import org.orekit.propagation.events.FieldAbstractDetector;
import org.orekit.propagation.events.FieldEventDetector;
import org.orekit.propagation.events.handlers.FieldEventHandler;
import org.orekit.utils.ParameterDriver;

import java.util.List;

public class PythonStartStopEventsTrigger<A extends AbstractDetector<A>, O extends AbstractDetector<O>> extends StartStopEventsTrigger<A, O> {
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

    public PythonStartStopEventsTrigger(A prototypeStartDetector, O prototypeStopDetector) {
        super(prototypeStartDetector, prototypeStopDetector);
    }

    /** {@inheritDoc} */
    @Override
    public native  <D extends FieldAbstractDetector<D, S>, S extends CalculusFieldElement<S>> FieldAbstractDetector<D, S> convertStartDetector(Field<S> field, A detector);

    /** {@inheritDoc} */
    @Override
    public native <D extends FieldAbstractDetector<D, S>, S extends CalculusFieldElement<S>> FieldAbstractDetector<D, S> convertStopDetector(Field<S> field, O detector);

    /** {@inheritDoc} */
    @Override
    public native List<ParameterDriver> getParametersDrivers();
}
