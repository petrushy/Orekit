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

package org.orekit.time;

import org.orekit.python.JCCBase;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class PythonTimeInterpolator<T extends TimeInterpolator<T> & TimeStamped> implements TimeInterpolator<T> {

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

    /** {@inheritDoc} */
    @Override
    public native T interpolate(AbsoluteDate date, Stream<T> sample);

    /** {@inheritDoc} */
    @Override
    public native T interpolate(AbsoluteDate interpolationDate, Collection<T> sample);

    /** {@inheritDoc} */
    @Override
    public native List<TimeInterpolator<? extends TimeStamped>> getSubInterpolators();

    /** {@inheritDoc} */
    @Override
    public native int getNbInterpolationPoints();

    /** {@inheritDoc} */
    @Override
    public native double getExtrapolationThreshold();
}
