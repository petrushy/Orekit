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

package org.orekit.frames;

import org.orekit.time.UT1Scale;
import org.orekit.utils.IERSConventions;

public class PythonFrames implements Frames {

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

    @Override
    public native EOPHistory getEOPHistory(IERSConventions conventions, boolean simpleEOP);

    @Override
    public native Frame getFrame(Predefined factoryKey);

    @Override
    public native Frame getGCRF();

    @Override
    public native Frame getICRF();

    @Override
    public native Frame getEcliptic(IERSConventions conventions);

    @Override
    public native FactoryManagedFrame getEME2000();

    @Override
    public native FactoryManagedFrame getITRF(IERSConventions conventions, boolean simpleEOP);

    @Override
    public native FactoryManagedFrame getTIRF(IERSConventions conventions);

    @Override
    public native VersionedITRF getITRF(ITRFVersion version, IERSConventions conventions, boolean simpleEOP);

    @Override
    public native Frame buildUncachedITRF(UT1Scale ut1);

    @Override
    public native FactoryManagedFrame getTIRF(IERSConventions conventions, boolean simpleEOP);

    @Override
    public native FactoryManagedFrame getCIRF(IERSConventions conventions, boolean simpleEOP);

    @Override
    public native FactoryManagedFrame getVeis1950();

    @Override
    public native FactoryManagedFrame getITRFEquinox(IERSConventions conventions, boolean simpleEOP);

    @Override
    public native FactoryManagedFrame getGTOD(boolean applyEOPCorr);

    @Override
    public native FactoryManagedFrame getGTOD(IERSConventions conventions, boolean simpleEOP);

    @Override
    public native FactoryManagedFrame getTOD(boolean applyEOPCorr);

    @Override
    public native FactoryManagedFrame getTOD(IERSConventions conventions, boolean simpleEOP);

    @Override
    public native FactoryManagedFrame getMOD(boolean applyEOPCorr);

    @Override
    public native FactoryManagedFrame getMOD(IERSConventions conventions);

    @Override
    public native FactoryManagedFrame getTEME();

    @Override
    public native FactoryManagedFrame getPZ9011(IERSConventions convention, boolean simpleEOP);
}
