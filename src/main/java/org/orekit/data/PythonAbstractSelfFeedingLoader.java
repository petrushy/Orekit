/* Contributed in the public domain.
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

package org.orekit.data;
// TODO: Not sure which methods to expose to Python
public class PythonAbstractSelfFeedingLoader extends AbstractSelfFeedingLoader {
    /**
     * Create an abstract data loader that can feed itself.
     *
     * @param supportedNames regular expression. See {@link DataProvidersManager#feed(String,
     *                       DataLoader)}.
     * @param manager        the source of auxiliary data files.
     */
    public PythonAbstractSelfFeedingLoader(String supportedNames, DataProvidersManager manager) {
        super(supportedNames, manager);
    }
}
