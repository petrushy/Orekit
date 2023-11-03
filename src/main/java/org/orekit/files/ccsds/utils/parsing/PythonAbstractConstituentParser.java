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
//

package org.orekit.files.ccsds.utils.parsing;

import org.orekit.data.DataContext;
import org.orekit.files.ccsds.ndm.NdmConstituent;
import org.orekit.files.ccsds.ndm.ParsedUnitsBehavior;
import org.orekit.files.ccsds.section.Header;
import org.orekit.files.ccsds.utils.FileFormat;
import org.orekit.files.ccsds.utils.lexical.ParseToken;
import org.orekit.utils.IERSConventions;

import java.util.List;
import java.util.function.Function;

public class PythonAbstractConstituentParser<H extends Header, T extends NdmConstituent<H, ?>, P extends AbstractConstituentParser<H, T, ?>> extends AbstractConstituentParser<H, T, P> {
    /** Part of JCC Python interface to object */
    private long pythonObject;


    public PythonAbstractConstituentParser(final String root,
                                           final String formatVersionKey,
                                           final IERSConventions conventions,
                                           final boolean simpleEOP,
                                           final DataContext dataContext,
                                           final ParsedUnitsBehavior parsedUnitsBehavior,
                                           final Function<ParseToken, List<ParseToken>>[] filters)
    {
        super(root, formatVersionKey, conventions, simpleEOP, dataContext, parsedUnitsBehavior, filters);
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


    /** {@inheritDoc} */
    @Override
    public native void reset(FileFormat fileFormat);

    /** {@inheritDoc} */
    @Override
    public native T build();

    /** {@inheritDoc} */
    @Override
    public native H getHeader();

    /** {@inheritDoc} */
    @Override
    public native boolean prepareHeader();

    /** {@inheritDoc} */
    @Override
    public native boolean inHeader();

    /** {@inheritDoc} */
    @Override
    public native boolean finalizeHeader();

    /** {@inheritDoc} */
    @Override
    public native boolean prepareMetadata();

    /** {@inheritDoc} */
    @Override
    public native boolean inMetadata();

    /** {@inheritDoc} */
    @Override
    public native boolean finalizeMetadata();

    /** {@inheritDoc} */
    @Override
    public native boolean prepareData();

    /** {@inheritDoc} */
    @Override
    public native boolean inData();

    /** {@inheritDoc} */
    @Override
    public native boolean finalizeData();
}
