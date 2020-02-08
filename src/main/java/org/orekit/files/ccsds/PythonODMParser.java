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

package org.orekit.files.ccsds;

import org.orekit.data.DataContext;
import org.orekit.time.AbsoluteDate;
import org.orekit.utils.IERSConventions;

import java.io.InputStream;

public class PythonODMParser extends ODMParser {
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


    /** Complete constructor.
     * @param missionReferenceDate reference date for Mission Elapsed Time or Mission Relative Time time systems
     * @param mu gravitational coefficient
     * @param conventions IERS Conventions
     * @param simpleEOP if true, tidal effects are ignored when interpolating EOP
     * @param launchYear launch year for TLEs
     * @param launchNumber launch number for TLEs
     * @param launchPiece piece of launch (from "A" to "ZZZ") for TLEs
     * @param dataContext used to retrieve frames and time scales.
     * @since 10.1
     */
    protected PythonODMParser(AbsoluteDate missionReferenceDate, double mu, IERSConventions conventions, boolean simpleEOP, int launchYear, int launchNumber, String launchPiece, DataContext dataContext) {
        super(missionReferenceDate, mu, conventions, simpleEOP, launchYear, launchNumber, launchPiece, dataContext);
    }

    /**
     * Set initial date.
     *
     * @param newMissionReferenceDate mission reference date to use while parsing
     * @return a new instance, with mission reference date replaced
     * @see #getMissionReferenceDate()
     */
    @Override
    public native ODMParser withMissionReferenceDate(AbsoluteDate newMissionReferenceDate);

    /**
     * Set gravitational coefficient.
     *
     * @param newMu gravitational coefficient to use while parsing
     * @return a new instance, with gravitational coefficient date replaced
     * @see #getMu()
     */
    @Override
    public native ODMParser withMu(double newMu);

    /**
     * Set IERS conventions.
     *
     * @param newConventions IERS conventions to use while parsing
     * @return a new instance, with IERS conventions replaced
     * @see #getConventions()
     */
    @Override
    public native ODMParser withConventions(IERSConventions newConventions);

    /**
     * Set EOP interpolation method.
     *
     * @param newSimpleEOP if true, tidal effects are ignored when interpolating EOP
     * @return a new instance, with EOP interpolation method replaced
     * @see #isSimpleEOP()
     */
    @Override
    public native ODMParser withSimpleEOP(boolean newSimpleEOP);

    /**
     * Set international designator.
     * <p>
     * This method may be used to ensure the launch year number and pieces are
     * correctly set if they are not present in the CCSDS file header in the
     * OBJECT_ID in the form YYYY-NNN-P{PP}. If they are already in the header,
     * they will be parsed automatically regardless of this method being called
     * or not (i.e. header information override information set here).
     * </p>
     *
     * @param newLaunchYear   launch year
     * @param newLaunchNumber launch number
     * @param newLaunchPiece  piece of launch (from "A" to "ZZZ")
     * @return a new instance, with TLE settings replaced
     */
    @Override
    public native ODMParser withInternationalDesignator(int newLaunchYear, int newLaunchNumber, String newLaunchPiece);

    /**
     * Set the data context.
     *
     * @param newDataContext used for frames, time scales, and celestial bodies.
     * @return a new instance with the data context replaced.
     */
    @Override
    public native ODMParser withDataContext(DataContext newDataContext);

    /**
     * Parse a CCSDS Orbit Data Message.
     *
     * @param stream   stream containing message
     * @param fileName name of the file containing the message (for error messages)
     * @return parsed orbit
     */
    @Override
    public native ODMFile parse(InputStream stream, String fileName);
}
