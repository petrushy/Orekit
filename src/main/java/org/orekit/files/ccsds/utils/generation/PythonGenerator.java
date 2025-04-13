/* Copyright SSC 2023
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

// This file was created by SSC and updated by SSC in 2023 and is largely a derived work from the
// original java class/interface that it inherits/implements


package org.orekit.files.ccsds.utils.generation;

import org.orekit.files.ccsds.definitions.TimeConverter;
import org.orekit.files.ccsds.utils.FileFormat;
import org.orekit.time.AbsoluteDate;
import org.orekit.utils.Formatter;
import org.orekit.utils.units.Unit;

import java.io.IOException;
import java.util.List;

public class PythonGenerator implements Generator {

    /** Part of JCC Python interface to object */
    protected long pythonObject;

    public void pythonExtension(long pythonObject) {
        this.pythonObject = pythonObject;
    }

    public long pythonExtension() {
        return this.pythonObject;
    }

    public void finalize() throws Throwable {
        pythonDecRef();
    }

    public native void pythonDecRef();

    /** {@inheritDoc} */
    @Override
    public native String getOutputName();

    /** {@inheritDoc} */
    @Override
    public native FileFormat getFormat();

    /** {@inheritDoc} */
    @Override
    public native void startMessage(String root, String messageTypeKey, double version) throws IOException;

    /** {@inheritDoc} */
    @Override
    public native void endMessage(String root) throws IOException;

    /** {@inheritDoc} */
    @Override
    public native void writeComments(List<String> comments) throws IOException;

    /** {@inheritDoc} */
    @Override
    public native void writeEntry(String key, String value, Unit unit, boolean mandatory) throws IOException;

    /** {@inheritDoc} */
    @Override
    public native void writeEntry(String key, List<String> value, boolean mandatory) throws IOException;

    /** {@inheritDoc} */
    @Override
    public native void writeEntry(String key, Enum<?> value, boolean mandatory) throws IOException;

    /** {@inheritDoc} */
    @Override
    public native void writeEntry(String key, TimeConverter converter, AbsoluteDate date, boolean forceCalendar, boolean mandatory) throws IOException;

    /** {@inheritDoc} */
    @Override
    public native void writeEntry(String key, char value, boolean mandatory) throws IOException;

    /** {@inheritDoc} */
    @Override
    public native void writeEntry(String key, int value, boolean mandatory) throws IOException;

    /** {@inheritDoc} */
    @Override
    public native void writeEntry(String key, double value, Unit unit, boolean mandatory) throws IOException;

    /** {@inheritDoc} */
    @Override
    public native void writeEntry(String key, Double value, Unit unit, boolean mandatory) throws IOException;

    /** {@inheritDoc} */
    @Override
    public native void newLine() throws IOException;

    /** {@inheritDoc} */
    @Override
    public native void writeRawData(char data) throws IOException;

    /** {@inheritDoc} */
    @Override
    public native void writeRawData(CharSequence data) throws IOException;

    /** {@inheritDoc} */
    @Override
    public native void enterSection(String name) throws IOException;

    /** {@inheritDoc} */
    @Override
    public native String exitSection() throws IOException;

    /** {@inheritDoc} */
    @Override
    public native void close() throws IOException;

    /** {@inheritDoc} */
    @Override
    public native String dateToString(TimeConverter converter, AbsoluteDate date);

    /** {@inheritDoc} */
    @Override
    public native String dateToCalendarString(TimeConverter converter, AbsoluteDate date);

    /** {@inheritDoc} */
    @Override
    public native String dateToString(int year, int month, int day, int hour, int minute, double seconds);

    /** {@inheritDoc} */
    @Override
    public native String doubleToString(double value);

    /** {@inheritDoc} */
    @Override
    public native String unitsListToString(List<Unit> units);

    /** {@inheritDoc} */
    @Override
    public native String siToCcsdsName(String siName);

    /** {@inheritDoc} */
    @Override
    public native Formatter getFormatter();
}
