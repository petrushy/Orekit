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

package org.orekit.utils;

import org.orekit.time.AbsoluteDate;
import org.orekit.utils.ParameterDriver;
import org.orekit.utils.ParameterObserver;

public class PythonParameterObserver implements ParameterObserver {

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
     * Notify that a parameter value has been changed.
     *
     * @param previousValue previous value
     * @param driver        parameter driver that has been changed
     */
    @Override
    public native void valueChanged(double previousValue, ParameterDriver driver);

    /**
     * Notify that a parameter reference date has been changed.
     * <p>
     * The default implementation does nothing
     * </p>
     *
     * @param previousReferenceDate previous date (null if it is the first time
     *                              the reference date is changed)
     * @param driver                parameter driver that has been changed
     * @since 9.0
     */
    @Override
    public native void referenceDateChanged(AbsoluteDate previousReferenceDate, ParameterDriver driver);

    /**
     * Notify that a parameter name has been changed.
     * <p>
     * The default implementation does nothing
     * </p>
     *
     * @param previousName previous name
     * @param driver       parameter driver that has been changed
     * @since 9.0
     */
    @Override
    public native void nameChanged(String previousName, ParameterDriver driver);

    /**
     * Notify that a parameter selection status has been changed.
     * <p>
     * The default implementation does nothing
     * </p>
     *
     * @param previousSelection previous selection
     * @param driver            parameter driver that has been changed
     * @since 9.0
     */
    @Override
    public native void selectionChanged(boolean previousSelection, ParameterDriver driver);

    /**
     * Notify that a parameter reference value has been changed.
     * <p>
     * The default implementation does nothing
     * </p>
     *
     * @param previousReferenceValue previous reference value
     * @param driver                 parameter driver that has been changed
     * @since 9.0
     */
    @Override
    public native void referenceValueChanged(double previousReferenceValue, ParameterDriver driver);

    /**
     * Notify that a parameter minimum value has been changed.
     * <p>
     * The default implementation does nothing
     * </p>
     *
     * @param previousMinValue previous minimum value
     * @param driver           parameter driver that has been changed
     * @since 9.0
     */
    @Override
    public native void minValueChanged(double previousMinValue, ParameterDriver driver);

    /**
     * Notify that a parameter maximum value has been changed.
     * <p>
     * The default implementation does nothing
     * </p>
     *
     * @param previousMaxValue previous maximum value
     * @param driver           parameter driver that has been changed
     * @since 9.0
     */
    @Override
    public native void maxValueChanged(double previousMaxValue, ParameterDriver driver);

    /**
     * Notify that a parameter scale has been changed.
     * <p>
     * The default implementation does nothing
     * </p>
     *
     * @param previousScale previous scale
     * @param driver        parameter driver that has been changed
     * @since 9.0
     */
    @Override
    public native void scaleChanged(double previousScale, ParameterDriver driver);
}
