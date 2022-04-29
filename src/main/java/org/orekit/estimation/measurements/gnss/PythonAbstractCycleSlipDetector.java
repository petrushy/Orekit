/* Copyright 2002-2020 CS GROUP
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

// this file was created by SCC 2020 and is largely a derived work from the
// original java class/interface


package org.orekit.estimation.measurements.gnss;

import org.orekit.gnss.Frequency;
import org.orekit.gnss.ObservationDataSet;
import org.orekit.gnss.SatelliteSystem;
import org.orekit.time.AbsoluteDate;

import java.util.List;
import java.util.Map;

public class PythonAbstractCycleSlipDetector extends AbstractCycleSlipDetector {

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
     * Cycle-slip detector Abstract Constructor.
     *
     * @param dt time gap between two consecutive measurements in seconds
     *           (if time between two consecutive measurement is greater than dt, a cycle slip is declared)
     * @param n  number of measures needed before starting test if a cycle-slip occurs
     */
    PythonAbstractCycleSlipDetector(double dt, int n) {
        super(dt, n);
    }

    /**
     * The method is in charge of collecting the measurements, manage them, and call the detection method.
     *
     * @param observation observation data set
     */
    @Override
    public native void manageData(ObservationDataSet observation);

    /**
     * Get the minimum number of measurement needed before being able to figure out cycle-slip occurrence.
     *
     * @return the minimum number of measurement needed before being able to figure out cycle-slip occurrence.
     */
    @Override
    public int getMinMeasurementNumber() {
        return super.getMinMeasurementNumber();
    }

    /**
     * Get the maximum time lapse between 2 measurements without considering a cycle-slip has occurring between both.
     *
     * @return the maximum time lapse between 2 measurements
     */
    @Override
    public double getMaxTimeBeetween2Measurement() {
        return super.getMaxTimeBeetween2Measurement();
    }

    /**
     * Get on all the results computed by the detector (e.g.: dates of cycle-slip).
     *
     * @return all the results computed by the detector (e.g.: dates of cycle-slip).
     */
    @Override
    public List<CycleSlipDetectorResults> getResults() {
        return super.getResults();
    }

    /**
     * Get the stuff (all the things needed for, the detector).
     *
     * @return return stuff
     */
    @Override
    public List<Map<Frequency, DataForDetection>> getStuffReference() {
        return super.getStuffReference();
    }

    /**
     * Set the data: collect data at the current Date, at the current frequency, for a given satellite, add it within the attributes data and stuff.
     *
     * @param nameSat name of the satellite (e.g. "GPS - 7")
     * @param date    date of the measurement
     * @param value   measurement at the current date
     * @param freq    frequency used
     */
    @Override
    public void cycleSlipDataSet(String nameSat, AbsoluteDate date, double value, Frequency freq) {
        super.cycleSlipDataSet(nameSat, date, value, freq);
    }

    /**
     * Create the name of a satellite from its PRN number and satellite System it belongs to.
     *
     * @param numSat satellite PRN number
     * @param sys    Satellite System of the satellite
     * @return the satellite name on a specified format (e.g.: "GPS - 7")
     */
    @Override
    public String setName(int numSat, SatelliteSystem sys) {
        return super.setName(numSat, sys);
    }
}
