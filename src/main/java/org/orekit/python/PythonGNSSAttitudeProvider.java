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

import org.hipparchus.RealFieldElement;
import org.orekit.attitudes.Attitude;
import org.orekit.attitudes.FieldAttitude;
import org.orekit.frames.Frame;
import org.orekit.gnss.attitude.GNSSAttitudeProvider;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.FieldAbsoluteDate;
import org.orekit.utils.FieldPVCoordinatesProvider;
import org.orekit.utils.PVCoordinatesProvider;

public class PythonGNSSAttitudeProvider implements GNSSAttitudeProvider {
    /**
     * Get start of validity for this provider.
     *
     * @return start of validity for this provider
     */
    @Override
    public native AbsoluteDate validityStart();

    /**
     * Get end of validity for this provider.
     *
     * @return end of validity for this provider
     */
    @Override
    public native AbsoluteDate validityEnd();

    /**
     * Compute the attitude corresponding to an orbital state.
     *
     * @param pvProv local position-velocity provider around current date
     * @param date   current date
     * @param frame  reference frame from which attitude is computed
     * @return attitude attitude on the specified date and position-velocity state
     */
    @Override
    public native Attitude getAttitude(PVCoordinatesProvider pvProv, AbsoluteDate date, Frame frame);

    /**
     * Compute the attitude corresponding to an orbital state.
     *
     * @param pvProv local position-velocity provider around current date
     * @param date   current date
     * @param frame  reference frame from which attitude is computed
     * @return attitude attitude on the specified date and position-velocity state
     * @since 9.0
     */
    @Override
    public native <T extends RealFieldElement<T>> FieldAttitude<T> getAttitude(FieldPVCoordinatesProvider<T> pvProv, FieldAbsoluteDate<T> date, Frame frame);
}
