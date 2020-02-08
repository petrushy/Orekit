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

package org.orekit.time;

import org.orekit.frames.EOPHistory;
import org.orekit.utils.IERSConventions;

public class PythonAbstractTimeScales extends AbstractTimeScales {

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
     * Get the EOP history for the given conventions.
     *
     * @param conventions to use in computing the EOP history.
     * @param simpleEOP   whether to ignore some small tidal effects.
     * @return EOP history.
     */
    @Override
    public native EOPHistory getEopHistory(IERSConventions conventions, boolean simpleEOP);

    /**
     * Get the International Atomic Time scale.
     *
     * @return International Atomic Time scale
     */
    @Override
    public native TAIScale getTAI();

    /**
     * Get the Universal Time Coordinate scale.
     *
     * @return Universal Time Coordinate scale
     */
    @Override
    public native UTCScale getUTC();

    /**
     * Get the Terrestrial Time scale.
     *
     * @return Terrestrial Time scale
     */
    @Override
    public native TTScale getTT();

    /**
     * Get the Galileo System Time scale.
     *
     * @return Galileo System Time scale
     */
    @Override
    public native GalileoScale getGST();

    /**
     * Get the GLObal NAvigation Satellite System time scale.
     *
     * @return GLObal NAvigation Satellite System time scale
     */
    @Override
    public native GLONASSScale getGLONASS();

    /**
     * Get the Quasi-Zenith Satellite System time scale.
     *
     * @return Quasi-Zenith Satellite System time scale
     */
    @Override
    public native QZSSScale getQZSS();

    /**
     * Get the Global Positioning System scale.
     *
     * @return Global Positioning System scale
     */
    @Override
    public native GPSScale getGPS();

    /**
     * Get the Geocentric Coordinate Time scale.
     *
     * @return Geocentric Coordinate Time scale
     */
    @Override
    public native TCGScale getTCG();

    /**
     * Get the Barycentric Dynamic Time scale.
     *
     * @return Barycentric Dynamic Time scale
     */
    @Override
    public native TDBScale getTDB();

    /**
     * Get the Barycentric Coordinate Time scale.
     *
     * @return Barycentric Coordinate Time scale
     */
    @Override
    public native TCBScale getTCB();

    /**
     * Get the Indian Regional Navigation Satellite System time scale.
     *
     * @return Indian Regional Navigation Satellite System time scale
     */
    @Override
    public native IRNSSScale getIRNSS();

    /**
     * Get the BeiDou Navigation Satellite System time scale.
     *
     * @return BeiDou Navigation Satellite System time scale
     */
    @Override
    public native BDTScale getBDT();
}
