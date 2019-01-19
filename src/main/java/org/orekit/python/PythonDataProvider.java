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

package org.orekit.python;

import org.orekit.data.DataLoader;
import org.orekit.data.DataProvider;

import java.util.regex.Pattern;

public class PythonDataProvider implements DataProvider {
    /**
     * Feed a data file loader by browsing the data collection.
     * <p>
     * The method crawls all files referenced in the instance (for example
     * all files in a directories tree) and for each file supported by the
     * file loader it asks the file loader to load it.
     * </p>
     * <p>
     * If the method completes without exception, then the data loader
     * is considered to have been fed successfully and the top level
     * {@link DataProvidersManager data providers manager} will return
     * immediately without attempting to use the next configured providers.
     * </p>
     * <p>
     * If the method completes abruptly with an exception, then the top level
     * {@link DataProvidersManager data providers manager} will try to use
     * the next configured providers, in case another one can feed the
     * {@link DataLoader data loader}.
     * </p>
     *
     * @param supported pattern for file names supported by the visitor
     * @param visitor   data file visitor to use
     * @return true if some data has been loaded
     */
    @Override
    public native boolean feed(Pattern supported, DataLoader visitor);
}
