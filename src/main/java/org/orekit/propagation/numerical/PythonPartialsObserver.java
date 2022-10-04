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

package org.orekit.propagation.numerical;

import org.orekit.propagation.SpacecraftState;

public class PythonPartialsObserver implements StateTransitionMatrixGenerator.PartialsObserver {
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
     * Callback called when partial derivatives have been computed.
     * <p>
     * The factor matrix is:
     * \[F = \begin{matrix}
     * 0         &             0         &             0         &             1         &             0         &             0        \\
     * 0         &             0         &             0         &             0         &             1         &             0        \\
     * 0         &             0         &             0         &             0         &             0         &             1        \\
     * \sum \frac{da_x}{dp_x} & \sum\frac{da_x}{dp_y} & \sum\frac{da_x}{dp_z} & \sum\frac{da_x}{dv_x} & \sum\frac{da_x}{dv_y} & \sum\frac{da_x}{dv_z}\\
     * \sum \frac{da_y}{dp_x} & \sum\frac{da_y}{dp_y} & \sum\frac{da_y}{dp_z} & \sum\frac{da_y}{dv_x} & \sum\frac{da_y}{dv_y} & \sum\frac{da_y}{dv_z}\\
     * \sum \frac{da_z}{dp_x} & \sum\frac{da_z}{dp_y} & \sum\frac{da_z}{dp_z} & \sum\frac{da_z}{dv_x} & \sum\frac{da_z}{dv_y} & \sum\frac{da_z}{dv_z}
     * \end{matrix}\]
     * </p>
     *
     * @param state                current spacecraft state
     * @param factor               factor matrix, flattened along rows
     * @param accelerationPartials partials derivatives of acceleration with respect to the parameter driver
     *                             that was registered (zero if no parameters were not selected or parameter is unknown)
     */
    @Override
    public native void partialsComputed(SpacecraftState state, double[] factor, double[] accelerationPartials);
}
