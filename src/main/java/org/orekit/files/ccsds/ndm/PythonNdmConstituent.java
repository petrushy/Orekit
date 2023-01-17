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


package org.orekit.files.ccsds.ndm;

import org.orekit.data.DataContext;
import org.orekit.files.ccsds.section.Header;
import org.orekit.files.ccsds.section.Segment;
import org.orekit.utils.IERSConventions;

import java.util.List;

public class PythonNdmConstituent<H extends Header, S extends Segment<?, ?>> extends NdmConstituent<H, S> {

    /** Part of JCC Python interface to object */

    private long pythonObject;

    /**
     * Constructor.
     *
     * @param header      file header
     * @param segments    file segments
     * @param conventions IERS conventions
     * @param dataContext used for creating frames, time scales, etc.
     */
    protected PythonNdmConstituent(H header, List<S> segments, IERSConventions conventions, DataContext dataContext) {
        super(header, segments, conventions, dataContext);
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

    /**
     * Get the header.
     *
     * @return header
     * @since 11.0
     */
    @Override
    public native H getHeader();

    /**
     * Get the segments.
     *
     * @return segments
     * @since 11.0
     */
    @Override
    public native List<S> getSegments();

    /**
     * Get IERS conventions.
     *
     * @return IERS conventions
     */
    @Override
    public native IERSConventions getConventions();

    /**
     * Get the data context.
     *
     * @return the data context used for creating frames, time scales, etc.
     */
    @Override
    public native DataContext getDataContext();

    /**
     * Validate the file message for required and forbidden entries.
     * <p>
     * This method throws an exception if file does not meet format requirements.
     * The requirements may depend on format version, which is found in header.
     * </p>
     */
    @Override
    public native void validate();

    /** Part of JCC Python interface to object */
    public native void pythonDecRef();

    // no abstract methods in super, TODO: check which to implement


}
