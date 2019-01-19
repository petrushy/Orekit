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

import org.orekit.propagation.PropagatorsParallelizer;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.sampling.MultiSatStepHandler;
import org.orekit.propagation.sampling.OrekitStepInterpolator;
import org.orekit.time.AbsoluteDate;

import java.util.List;

public class PythonMultiSatStepHandler implements MultiSatStepHandler {

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
     * Initialize step handler at the start of a propagation.
     * <p>
     * This method is called once at the start of the propagation. It
     * may be used by the step handler to initialize some internal data
     * if needed.
     * </p>
     * <p>
     * The default method does nothing
     * </p>
     *
     * @param states0 initial states, one for each satellite in the same order
     *                used to {@link PropagatorsParallelizer#PropagatorsParallelizer(List, MultiSatStepHandler)
     *                build} the {@link PropagatorsParallelizer multi-sat propagator}.
     * @param t       target time for the integration
     */
    @Override
    public void init(List<SpacecraftState> states0, AbsoluteDate t) {

    }

    /**
     * Handle the current step.
     * <p>
     * When called by {@link PropagatorsParallelizer PropagatorsParallelizer},
     * all interpolators have the same time range.
     * </p>
     *
     * @param interpolators interpolators set up for the current step in the same order
     *                      used to {@link PropagatorsParallelizer#PropagatorsParallelizer(List, MultiSatStepHandler)
     *                      build} the {@link PropagatorsParallelizer multi-sat propagator}
     * @param isLast        if true, this is the last integration step
     */
    @Override
    public void handleStep(List<OrekitStepInterpolator> interpolators, boolean isLast) {

    }
}
