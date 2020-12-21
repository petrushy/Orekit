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

// this file was created by CS Group 2020 and is largely a derived work from the
// original java class/interface and the PythonODMParser class.

package org.orekit.files.ccsds;

import org.orekit.annotation.DefaultDataContext;
import org.orekit.data.DataContext;
import org.orekit.time.AbsoluteDate;
import org.orekit.utils.IERSConventions;

import java.io.InputStream;
import java.io.BufferedReader;

/**
 * A Python wrapper for the AEMParser class (parser for Attitude Ephemeris Messsage (AEM) files).
 * @author Olivier Podevin
 * @since 10.2
 */
public class PythonAEMParser extends AEMParser {

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
     * Simple constructor.
     * <p>
     * This class is immutable, and hence thread safe. When parts
     * must be changed, such as reference date for Mission Elapsed Time or
     * Mission Relative Time time systems, or the gravitational coefficient or
     * the IERS conventions, the various {@code withXxx} methods must be called,
     * which create a new immutable instance with the new parameters. This
     * is a combination of the
     * <a href="https://en.wikipedia.org/wiki/Builder_pattern">builder design
     * pattern</a> and a
     * <a href="http://en.wikipedia.org/wiki/Fluent_interface">fluent
     * interface</a>.
     * </p>
     * <p>
     * The initial date for Mission Elapsed Time and Mission Relative Time time systems is not set here.
     * If such time systems are used, it must be initialized before parsing by calling {@link
     * #withMissionReferenceDate(AbsoluteDate)}.
     * </p> <p>
     * The IERS conventions to use is not set here. If it is needed in order to
     * parse some reference frames or UT1 time scale, it must be initialized before
     * parsing by calling {@link #withConventions(IERSConventions)}.
     * </p>
     *
     * <p>This method uses the {@link DataContext#getDefault() default data context}. See
     * {@link #withDataContext(DataContext)}.
     */

  @DefaultDataContext
  public PythonAEMParser() {
        super(DataContext.getDefault());
    }

    /**
     * Constructor with data context.
     * <p>
     * This class is immutable, and hence thread safe. When parts
     * must be changed, such as reference date for Mission Elapsed Time or
     * Mission Relative Time time systems, or the gravitational coefficient or
     * the IERS conventions, the various {@code withXxx} methods must be called,
     * which create a new immutable instance with the new parameters. This
     * is a combination of the
     * <a href="https://en.wikipedia.org/wiki/Builder_pattern">builder design
     * pattern</a> and a
     * <a href="http://en.wikipedia.org/wiki/Fluent_interface">fluent
     * interface</a>.
     * </p>
     * <p>
     * The initial date for Mission Elapsed Time and Mission Relative Time time systems is not set here.
     * If such time systems are used, it must be initialized before parsing by calling {@link
     * #withMissionReferenceDate(AbsoluteDate)}.
     * </p> <p>
     * The IERS conventions to use is not set here. If it is needed in order to
     * parse some reference frames or UT1 time scale, it must be initialized before
     * parsing by calling {@link #withConventions(IERSConventions)}.
     * </p>
     *
     * @param dataContext used by the parser.
     * @see #AEMParser()
     * @see #withDataContext(DataContext)
     */
    public PythonAEMParser(final DataContext dataContext) {
        super(dataContext);
    }

    /** {@inheritDoc} */
    @Override
    public native AEMParser withMissionReferenceDate(final AbsoluteDate newMissionReferenceDate);

    /** {@inheritDoc} */
    @Override
    public native AEMParser withMu(final double newMu);

    /** {@inheritDoc} */
    @Override
    public native AEMParser withConventions(final IERSConventions newConventions);

    /** {@inheritDoc} */
    @Override
    public native AEMParser withSimpleEOP(final boolean newSimpleEOP);

    /** {@inheritDoc} */
    @Override
    public native AEMParser withInternationalDesignator(final int newLaunchYear,
                                                 final int newLaunchNumber,
                                                 final String newLaunchPiece);

    /** {@inheritDoc} */
    @Override
    public native AEMParser withDataContext(final DataContext dataContext);

    /* TODO: Two new methods since 10.3 in java */

    /** {@inheritDoc} */
    @Override
    public native AEMFile parse(final String fileName);

    /** {@inheritDoc} */
    @Override
    public native AEMFile parse(final InputStream stream);

    /** {@inheritDoc} */
    @Override
    public native AEMFile parse(final InputStream stream, final String fileName);

    /**
     * Parse an attitude ephemeris file from a stream.
     * @param reader   containing the ephemeris file.
     * @param fileName to use in error messages.
     * @return a parsed attitude ephemeris file.
     */
    @Override
    public native AEMFile parse(final BufferedReader reader, final String fileName);

}
