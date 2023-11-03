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
// original java class

package org.orekit.files.ccsds.ndm.adm;

import org.orekit.data.DataContext;
import org.orekit.files.ccsds.ndm.NdmConstituent;
import org.orekit.files.ccsds.ndm.ParsedUnitsBehavior;
import org.orekit.files.ccsds.section.Header;
import org.orekit.files.ccsds.utils.FileFormat;
import org.orekit.files.ccsds.utils.lexical.ParseToken;
import org.orekit.files.ccsds.utils.parsing.AbstractConstituentParser;
import org.orekit.time.AbsoluteDate;
import org.orekit.utils.IERSConventions;

import java.util.List;
import java.util.function.Function;

public class PythonAdmParser<T extends NdmConstituent<AdmHeader, ?>, P extends AbstractConstituentParser<AdmHeader,T, ?>> extends AdmParser<T, P>
{

    /** Part of JCC Python interface to object */

    private long pythonObject;

    /** Complete constructor.
     * @param root root element for XML files
     * @param formatVersionKey key for format version
     * @param conventions IERS Conventions
     * @param simpleEOP if true, tidal effects are ignored when interpolating EOP
     * @param dataContext used to retrieve frames, time scales, etc.
     * @param missionReferenceDate reference date for Mission Elapsed Time or Mission Relative Time time systems
     * (may be null if time system is absolute)
     * @param parsedUnitsBehavior behavior to adopt for handling parsed units
     * @param filters filters to apply to parse tokens
     * @since 12.0
     */
    public PythonAdmParser(String root, String formatVersionKey, IERSConventions conventions, boolean simpleEOP, DataContext dataContext, AbsoluteDate missionReferenceDate, ParsedUnitsBehavior parsedUnitsBehavior, Function<ParseToken, List<ParseToken>>[] filters) {
        super(root, formatVersionKey, conventions, simpleEOP, dataContext, missionReferenceDate, parsedUnitsBehavior, filters);
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
     * Reset parser to initial state before parsing.
     *
     * @param fileFormat format of the file ready to be parsed
     */
    @Override
    public native void reset(FileFormat fileFormat);

    @Override
    public native T build();


    @Override
    public native AdmHeader getHeader();

    /**
     * Prepare header for parsing.
     *
     * @return true if parser was able to perform the action
     */
    @Override
    public native boolean prepareHeader();

    /**
     * Acknowledge header parsing has started.
     *
     * @return true if parser was able to perform the action
     */
    @Override
    public native boolean inHeader();

    /**
     * Finalize header after parsing.
     *
     * @return true if parser was able to perform the action
     */
    @Override
    public native boolean finalizeHeader();

    /**
     * Prepare metadata for parsing.
     *
     * @return true if parser was able to perform the action
     */
    @Override
    public native boolean prepareMetadata();

    /**
     * Acknowledge metada parsing has started.
     *
     * @return true if parser was able to perform the action
     */
    @Override
    public native boolean inMetadata();

    /**
     * Finalize metadata after parsing.
     *
     * @return true if parser was able to perform the action
     */
    @Override
    public native boolean finalizeMetadata();

    /**
     * Prepare data for parsing.
     *
     * @return true if parser was able to perform the action
     */
    @Override
    public native boolean prepareData();

    /**
     * Acknowledge data parsing has started.
     *
     * @return true if parser was able to perform the action
     */
    @Override
    public native boolean inData();

    /**
     * Finalize data after parsing.
     *
     * @return true if parser was able to perform the action
     */
    @Override
    public native boolean finalizeData();
}
