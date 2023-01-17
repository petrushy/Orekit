/* Copyright 2002-2021 CS GROUP
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

// this file was created by SSC 2021 and is largely a derived work from the
// original java class/interface

package org.orekit.files.ccsds.ndm;

import org.orekit.data.DataContext;
import org.orekit.files.ccsds.ndm.tdm.RangeUnitsConverter;
import org.orekit.time.AbsoluteDate;
import org.orekit.utils.IERSConventions;

public class PythonAbstractBuilder<T extends AbstractBuilder<T>> extends AbstractBuilder<T> {

    /** Part of JCC Python interface to object */
    private long pythonObject;

    /**
     * Complete constructor.
     *
     * @param conventions          IERS Conventions
     * @param dataContext          used to retrieve frames, time scales, etc.
     * @param missionReferenceDate reference date for Mission Elapsed Time or Mission Relative Time time systems
     * @param rangeUnitsConverter  converter for {@link RangeUnits#RU Range Units}
     */
    protected PythonAbstractBuilder(IERSConventions conventions, DataContext dataContext, AbsoluteDate missionReferenceDate, RangeUnitsConverter rangeUnitsConverter) {
        super(conventions, dataContext, missionReferenceDate, rangeUnitsConverter);
    }

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
     * Build an instance.
     *
     * @param newConventions          IERS Conventions
     * @param newDataContext          used to retrieve frames, time scales, etc.
     * @param newMissionReferenceDate reference date for Mission Elapsed Time or Mission Relative Time time systems
     * @param newRangeUnitsConverter  converter for {@link RangeUnits#RU Range Units}
     * @return new instance
     */
    @Override
    public native T create(IERSConventions newConventions, DataContext newDataContext, AbsoluteDate newMissionReferenceDate, RangeUnitsConverter newRangeUnitsConverter);
}
