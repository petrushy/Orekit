/* Copyright 2002-2025 CS GROUP
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
package org.orekit.models.earth.atmosphere;

import org.orekit.time.AbsoluteDate;

/**
 * Python implementation of the JB2006InputParameters interface.
 * This class is part of the JCC Python interface and exposes all methods natively.
 */
public class PythonJB2006InputParameters implements JB2006InputParameters {
    /** Part of JCC Python interface to object */
    private long pythonObject;

    public void pythonExtension(long pythonObject) {
        this.pythonObject = pythonObject;
    }

    public long pythonExtension() {
        return this.pythonObject;
    }

    public void finalize() throws Throwable {
        pythonDecRef();
    }

    public native void pythonDecRef();

    /** {@inheritDoc} */
    @Override
    public native double getF10(AbsoluteDate date);

    /** {@inheritDoc} */
    @Override
    public native double getF10B(AbsoluteDate date);

    /** {@inheritDoc} */
    @Override
    public native double getS10(AbsoluteDate date);

    /** {@inheritDoc} */
    @Override
    public native double getS10B(AbsoluteDate date);

    /** {@inheritDoc} */
    @Override
    public native double getXM10(AbsoluteDate date);

    /** {@inheritDoc} */
    @Override
    public native double getXM10B(AbsoluteDate date);

    /** {@inheritDoc} */
    @Override
    public native double getAp(AbsoluteDate date);

    /** {@inheritDoc} */
    @Override
    public native AbsoluteDate getMinDate();

    /** {@inheritDoc} */
    @Override
    public native AbsoluteDate getMaxDate();
}
