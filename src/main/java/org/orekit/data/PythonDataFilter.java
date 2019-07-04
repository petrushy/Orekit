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

package org.orekit.data;

import org.orekit.data.DataFilter;
import org.orekit.data.NamedData;

import java.io.IOException;

public class PythonDataFilter implements DataFilter {

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
     * Filter the named data.
     * Extension point for Python.
     * <p>
     * Filtering is often based on suffix. For example a gzip compressed
     * file will have an original name of the form base.ext.gz when the
     * corresponding uncompressed file will have a filtered name base.ext.
     * </p>
     * <p>
     * Beware that as the {@link DataProvidersManager data providers manager}
     * will attempt to pile all filters in a stack as long as their implementation
     * of this method returns a value different from the {@code original} parameter.
     * This implies that the filter, <em>must</em> perform some checks to see if it must
     * be applied or not. If for example there is a need for a deciphering filter
     * to be applied once to all data, then the filter should for example check
     * for a suffix in the {@link NamedData#getName() name} and create a new
     * filtered {@link NamedData} instance <em>only</em> if the suffix is present,
     * removing the suffix from the filtered instance. Failing to do so and simply
     * creating a filtered instance with one deciphering layer without changing the
     * name would result in an infinite stack of deciphering filters being built, until
     * a stack overflow or memory exhaustion exception occurs.
     * </p>
     *
     * @param original original named data
     * @return filtered named data, or {@code original} if this filter
     * does not apply to this named data
     * @throws IOException if filtered stream cannot be created
     */
    @Override
    public native NamedData filter(NamedData original) throws IOException;
}
