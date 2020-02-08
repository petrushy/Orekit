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

package org.orekit.data;

import org.hipparchus.RealFieldElement;

public class PythonSeriesTerm extends SeriesTerm {
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
     * Compute the argument for the current date.
     *
     * @param elements luni-solar and planetary elements for the current date
     * @return current value of the argument
     */
    @Override
    public native double argument(BodiesElements elements);

    /**
     * Compute the time derivative of the argument for the current date.
     *
     * @param elements luni-solar and planetary elements for the current date
     * @return current time derivative of the argument
     */
    @Override
    public native double argumentDerivative(BodiesElements elements);

    /**
     * Compute the argument for the current date.
     *
     * @param elements luni-solar and planetary elements for the current date
     * @return current value of the argument
     */
    @Override
    public <T extends RealFieldElement<T>> T argument(FieldBodiesElements<T> elements) {
        return this.argument_F(elements);
    }

    /**
     * Compute the argument for the current date. Extension point for Python
     *
     * @param elements luni-solar and planetary elements for the current date
     * @return current value of the argument
     */
    public native <T extends RealFieldElement<T>> T argument_F(FieldBodiesElements<T> elements);

    /**
     * Compute the time derivative of the argument for the current date.
     *
     * @param elements luni-solar and planetary elements for the current date
     * @return current time derivative of the argument
     */
    @Override
    public <T extends RealFieldElement<T>> T argumentDerivative(FieldBodiesElements<T> elements) {
        return this.argumentDerivative_F(elements);
    }

    /**
     * Compute the time derivative of the argument for the current date.
     * Extension point for Python.
     * @param elements luni-solar and planetary elements for the current date
     * @return current time derivative of the argument
     */

    public native <T extends RealFieldElement<T>> T argumentDerivative_F(FieldBodiesElements<T> elements);
}
