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


package org.orekit.files.ccsds.utils.generation;

import org.orekit.files.ccsds.definitions.TimeConverter;
import org.orekit.files.ccsds.utils.FileFormat;
 ;
import org.orekit.time.AbsoluteDate;
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
    public void finalize() throws Throwable { pythonDecRef(); }
    public native void pythonDecRef();

    // TODO: make some sane way of pythonizing this class

    /**
     * Get the name of the output (for error messages).
     *
     * @return name of the output
     */
    @Override
    public native String getOutputName();

    /**
     * Get the generated file format.
     *
     * @return generated file format
     */
    @Override
    public native FileFormat getFormat();

    /**
     * Start CCSDS message.
     *
     * @param root           root element for XML files
     * @param messageTypeKey key for message type
     * @param version        format version
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public native void startMessage(String root, String messageTypeKey, double version) throws IOException;

    /**
     * End CCSDS message.
     *
     * @param root root element for XML files
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public native void endMessage(String root) throws IOException;

    /**
     * Write comment lines.
     *
     * @param comments comments to write
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public native void writeComments(List<String> comments) throws IOException;

    /**
     * Write a single key/value entry.
     *
     * @param key       the keyword to write
     * @param value     the value to write
     * @param unit      output unit (may be null)
     * @param mandatory if true, null values triggers exception, otherwise they are silently ignored
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public native void writeEntry(String key, String value, Unit unit, boolean mandatory) throws IOException;

    /**
     * Write a single key/value entry.
     *
     * @param key       the keyword to write
     * @param value     the value to write
     * @param mandatory if true, null values triggers exception, otherwise they are silently ignored
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public native void writeEntry(String key, List<String> value, boolean mandatory) throws IOException;

    /**
     * Write a single key/value entry.
     *
     * @param key       the keyword to write
     * @param value     the value to write
     * @param mandatory if true, null values triggers exception, otherwise they are silently ignored
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public native void writeEntry(String key, Enum<?> value, boolean mandatory) throws IOException;

    @Override
    public native void writeEntry(String key, TimeConverter converter, AbsoluteDate date, boolean forceCalendar, boolean mandatory) throws IOException;


    /**
     * Write a single key/value entry.
     *
     * @param key       the keyword to write
     * @param value     the value to write
     * @param mandatory if true, null values triggers exception, otherwise they are silently ignored
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public native void writeEntry(String key, char value, boolean mandatory) throws IOException;

    /**
     * Write a single key/value entry.
     *
     * @param key       the keyword to write
     * @param value     the value to write
     * @param mandatory if true, null values triggers exception, otherwise they are silently ignored
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public native void writeEntry(String key, int value, boolean mandatory) throws IOException;

    /**
     * Write a single key/value entry.
     *
     * @param key       the keyword to write
     * @param value     the value to write (in SI units)
     * @param unit      output unit
     * @param mandatory if true, null values triggers exception, otherwise they are silently ignored
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public native void writeEntry(String key, double value, Unit unit, boolean mandatory) throws IOException;

    /**
     * Write a single key/value entry.
     *
     * @param key       the keyword to write
     * @param value     the value to write (in SI units)
     * @param unit      output unit
     * @param mandatory if true, null values triggers exception, otherwise they are silently ignored
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public native void writeEntry(String key, Double value, Unit unit, boolean mandatory) throws IOException;

    /**
     * Finish current line.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public native void newLine() throws IOException;

    /**
     * Write raw data.
     *
     * @param data raw data to write
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public native void writeRawData(char data) throws IOException;

    /**
     * Write raw data.
     *
     * @param data raw data to write
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public native void writeRawData(CharSequence data) throws IOException;

    /**
     * Enter into a new section.
     *
     * @param name section name
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public native void enterSection(String name) throws IOException;

    /**
     * Exit last section.
     *
     * @return section name
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public native String exitSection() throws IOException;

    /**
     * Close the generator.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public native void close() throws IOException;

    /**
     * Convert a date to string value with high precision.
     *
     * @param converter converter for dates
     * @param date      date to write
     * @return date as a string
     */
    @Override
    public native String dateToString(TimeConverter converter, AbsoluteDate date);

    @Override
    public native String dateToCalendarString(TimeConverter converter, AbsoluteDate date);

    /**
     * Convert a date to string value with high precision.
     *
     * @param year    year
     * @param month   month
     * @param day     day
     * @param hour    hour
     * @param minute  minute
     * @param seconds seconds
     * @return date as a string
     */
    @Override
    public native String dateToString(int year, int month, int day, int hour, int minute, double seconds);

    /**
     * Convert a double to string value with high precision.
     * <p>
     * We don't want to loose internal accuracy when writing doubles
     * but we also don't want to have ugly representations like STEP = 1.25000000000000000
     * so we try a few simple formats first and fall back to scientific notation
     * if it doesn't work.
     * </p>
     *
     * @param value value to format
     * @return formatted value, with all original value accuracy preserved, or null
     * if value is null or {@code Double.NaN}
     */
    @Override
    public native String doubleToString(double value);

    /**
     * Convert a list of units to a bracketed string.
     *
     * @param units lists to output (may be null or empty)
     * @return bracketed string (null if units list is null or empty)
     */
    @Override
    public native String unitsListToString(List<Unit> units);

    /**
     * Convert a SI unit name to a CCSDS name.
     *
     * @param siName si unit name
     * @return CCSDS name for the unit
     */
    @Override
    public native String siToCcsdsName(String siName);
}
