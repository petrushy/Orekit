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

import org.hipparchus.RealFieldElement;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.FieldAbsoluteDate;
import org.orekit.time.TimeScale;

public class PythonTimeScale implements TimeScale {

    private static final long serialVersionUID = 1472684095479052858L;
    
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
     * Get the offset to convert locations from {@link TAIScale} to instance.
     *
     * @param date conversion date
     * @return offset in seconds to add to a location in <em>{@link TAIScale}
     * time scale</em> to get a location in <em>instance time scale</em>
     * @see #offsetToTAI(DateComponents, TimeComponents)
     */
    @Override
    public native double offsetFromTAI(AbsoluteDate date);

    /**
     * Get the offset to convert locations from {@link TAIScale} to instance.
     *
     * @param date conversion date
     * @return offset in seconds to add to a location in <em>{@link TAIScale}
     * time scale</em> to get a location in <em>instance time scale</em>
     * @see #offsetToTAI(DateComponents, TimeComponents)
     * @since 9.0
     */
    @Override
    public <T extends RealFieldElement<T>> T offsetFromTAI(FieldAbsoluteDate<T> date) {
        return this.offsetFromTAI_F(date);
    }

    /**
     * Get the offset to convert locations from {@link TAIScale} to instance.
     *
     * @param date conversion date
     * @return offset in seconds to add to a location in <em>{@link TAIScale}
     * time scale</em> to get a location in <em>instance time scale</em>
     * @see #offsetToTAI(DateComponents, TimeComponents)
     * @since 9.0
     */
    public native <T extends RealFieldElement<T>> T offsetFromTAI_F(FieldAbsoluteDate<T> date);


    /**
     * Get the name time scale.
     *
     * @return name of the time scale
     */
    @Override
    public native String getName();
}
