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

import org.hipparchus.exception.Localizable;
import org.orekit.errors.LocalizedException;

import java.util.Locale;

public class PythonLocalizedException implements LocalizedException {
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
     * Gets the message in a specified locale.
     *
     * @param locale Locale in which the message should be translated
     * @return localized message
     */
    @Override
    public native String getMessage(Locale locale);

    /**
     * Get the localizable specifier of the error message.
     *
     * @return localizable specifier of the error message
     */
    @Override
    public native Localizable getSpecifier();

    /**
     * Get the variable parts of the error message.
     *
     * @return a copy of the variable parts of the error message
     */
    @Override
    public native Object[] getParts();
}
