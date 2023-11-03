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

import org.orekit.files.rinex.observation.ObservationDataSet;
import org.orekit.gnss.Frequency;
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

    /** {@inheritDoc} */
    @Override
    public int getMinMeasurementNumber() {
        return super.getMinMeasurementNumber();
    }

    /** {@inheritDoc} */
    @Override
    public double getMaxTimeBeetween2Measurement() {
        return super.getMaxTimeBeetween2Measurement();
    }

    /** {@inheritDoc} */
    @Override
    public List<CycleSlipDetectorResults> getResults() {
        return super.getResults();
    }

    /** {@inheritDoc} */
    @Override
    public List<Map<Frequency, DataForDetection>> getStuffReference() {
        return super.getStuffReference();
    }

    /** {@inheritDoc} */
    @Override
    public void cycleSlipDataSet(String nameSat, AbsoluteDate date, double value, Frequency freq) {
        super.cycleSlipDataSet(nameSat, date, value, freq);
    }

    /** {@inheritDoc} */
    @Override
    public String setName(int numSat, SatelliteSystem sys) {
        return super.setName(numSat, sys);
    }
}
