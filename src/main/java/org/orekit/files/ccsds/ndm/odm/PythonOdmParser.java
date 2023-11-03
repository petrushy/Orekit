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

package org.orekit.files.ccsds.ndm.odm;

import java.util.List;
import java.util.function.Function;
import org.orekit.data.DataContext;
import org.orekit.files.ccsds.ndm.NdmConstituent;
import org.orekit.files.ccsds.ndm.ParsedUnitsBehavior;
import org.orekit.files.ccsds.utils.FileFormat;
import org.orekit.time.AbsoluteDate;
import org.orekit.utils.IERSConventions;
import org.orekit.files.ccsds.utils.lexical.ParseToken;

public class PythonOdmParser<T extends NdmConstituent<OdmHeader, ?>, P extends OdmParser<T, ?>> extends OdmParser<T, P>
{

    /** Part of JCC Python interface to object */

    private long pythonObject;

    /** Complete constructor.
     * @param root root element for XML files
     * @param formatVersionKey key for format version
     * @param conventions IERS Conventions
     * @param simpleEOP if true, tidal effects are ignored when interpolating EOP
     * @param dataContext used to retrieve frames and time scales
     * @param missionReferenceDate reference date for Mission Elapsed Time or Mission Relative Time time systems
     * @param mu gravitational coefficient
     * @param parsedUnitsBehavior behavior to adopt for handling parsed units
     * @param filters filters to apply to parse tokens
     * @since 12.0
     */
    public PythonOdmParser(String root, String formatVersionKey, IERSConventions conventions, boolean simpleEOP, DataContext dataContext, AbsoluteDate missionReferenceDate, double mu, ParsedUnitsBehavior parsedUnitsBehavior, Function<ParseToken, List<ParseToken>>[] filters)
    {
        super(root, formatVersionKey, conventions, simpleEOP, dataContext, missionReferenceDate, mu, parsedUnitsBehavior, filters);
    }

    /**
     * Get the gravitational coefficient set at construction.
     *
     * @return gravitational coefficient set at construction
     */
    @Override
    public double getMuSet() {
        return super.getMuSet();
    }

    /**
     * Set the gravitational coefficient parsed in the ODM File.
     *
     * @param muParsed the coefficient to be set
     */
    @Override
    public void setMuParsed(double muParsed) {
        super.setMuParsed(muParsed);
    }

    /**
     * Set the gravitational coefficient created from the knowledge of the central body.
     *
     * @param muCreated the coefficient to be set
     */
    @Override
    public void setMuCreated(double muCreated) {
        super.setMuCreated(muCreated);
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

    /**
     * Build the file from parsed entries.
     *
     * @return parsed file
     */
    @Override
    public native T build();

    /**
     * Get file header to fill.
     *
     * @return file header to fill
     */
    @Override
    public native OdmHeader getHeader();

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

    /**
     * Get the file format.
     *
     * @return file format
     */
    @Override
    public FileFormat getFileFormat() {
        return super.getFileFormat();
    }
}
