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

import org.orekit.bodies.CelestialBodies;
import org.orekit.bodies.CelestialBody;
import org.orekit.forces.gravity.potential.GravityFields;
import org.orekit.frames.Frame;
import org.orekit.frames.Frames;
import org.orekit.models.earth.GeoMagneticField;
import org.orekit.models.earth.GeoMagneticFields;
import org.orekit.time.TimeScale;
import org.orekit.time.TimeScales;

public class PythonDataContext implements DataContext {
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
     * Get a factory for constructing {@link TimeScale}s based on the auxiliary data in
     * this context.
     *
     * @return the set of common time scales using this data context.
     */
    @Override
    public native TimeScales getTimeScales();

    /**
     * Get a factory constructing {@link Frame}s based on the auxiliary data in this
     * context.
     *
     * @return the set of common reference frames using this data context.
     */
    @Override
    public native Frames getFrames();

    /**
     * Get a factory constructing {@link CelestialBody}s based on the auxiliary data in
     * this context.
     *
     * @return the set of common celestial bodies using this data context.
     */
    @Override
    public native CelestialBodies getCelestialBodies();

    /**
     * Get a factory constructing gravity fields based on the auxiliary data in this
     * context.
     *
     * @return the gravity fields using this data context.
     */
    @Override
    public native GravityFields getGravityFields();

    /**
     * Get a factory constructing {@link GeoMagneticField}s based on the auxiliary
     * data in this context.
     *
     * @return the geomagnetic fields using this data context.
     */
    @Override
    public native GeoMagneticFields getGeoMagneticFields();
}
