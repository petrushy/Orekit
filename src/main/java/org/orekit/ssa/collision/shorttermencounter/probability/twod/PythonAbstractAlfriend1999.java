/* Copyright SSC 2023
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

// This file was created by SSC and updated by SSC in 2023 and is largely a derived work from the
// original java class/interface that it inherits/implements


package org.orekit.ssa.collision.shorttermencounter.probability.twod;

import org.hipparchus.CalculusFieldElement;

public class PythonAbstractAlfriend1999 extends AbstractAlfriend1999 {

    /** Part of JCC Python interface to object */
    protected long pythonObject;
    public void pythonExtension(long pythonObject) {
        this.pythonObject = pythonObject;
    }
    public long pythonExtension() {
        return this.pythonObject;
    }
    public void finalize() throws Throwable { pythonDecRef(); }
    public native void pythonDecRef();


    /**
     * Default constructor.
     *
     * @param name name of the method
     */
    public PythonAbstractAlfriend1999(String name) {
        super(name);
    }

    @Override
    public native double computeValue(double radius, double squaredMahalanobisDistance, double covarianceMatrixDeterminant);

    @Override
    public native <T extends CalculusFieldElement<T>> T computeValue(T radius, T squaredMahalanobisDistance, T covarianceMatrixDeterminant);

    @Override
    public native ShortTermEncounter2DPOCMethodType getType();
}
