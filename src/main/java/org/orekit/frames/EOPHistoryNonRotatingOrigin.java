/* Copyright 2002-2013 CS Systèmes d'Information
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
package org.orekit.frames;

import java.util.Collection;

import org.apache.commons.math3.analysis.interpolation.HermiteInterpolator;
import org.orekit.errors.OrekitException;
import org.orekit.errors.TimeStampedCacheException;
import org.orekit.time.AbsoluteDate;

/**
 * This class holds Earth Orientation Parameters data for Non-Rotating-Origin paradigm
 * throughout a large time range.
 *
 * @author Pascal Parraud
 */
public class EOPHistoryNonRotatingOrigin extends AbstractEOPHistory<EOPEntryNonRotatingOrigin> {

    /** Serializable UID. */
    private static final long serialVersionUID = 20130919L;

    /** Correction to use when no EOP data is available. */
    private static final double[] NULL_CORRECTION = new double[2];

    /**
     * Simple constructor.
     *
     * @param data the EOP data to use.
     */
    public EOPHistoryNonRotatingOrigin(final Collection<EOPEntryNonRotatingOrigin> data) {
        super(data);
    }

    /** {@inheritDoc}
     * <p>
     * For Non-Rotating-Origin paradigm, the nutation dorrection is the correction
     * in Celestial Intermediat Pole (CIP) coordinates dx and dy.
     * </p>
     */
    public double[] getNutationCorrection(final AbsoluteDate date) {
        // check if there is data for date
        if (!this.hasDataFor(date)) {
            // no EOP data available for this date, we use a default null correction
            return NULL_CORRECTION;
        }
        //we have EOP data for date -> interpolate correction
        try {
            final HermiteInterpolator interpolator = new HermiteInterpolator();
            for (final EOPEntryNonRotatingOrigin entry : getNeighbors(date)) {
                interpolator.addSamplePoint(entry.getDate().durationFrom(date),
                                            new double[] {
                                                entry.getDx(), entry.getDy()
                                            });
            }
            return interpolator.value(0);
        } catch (TimeStampedCacheException tce) {
            // this should not happen because of date check above
            throw OrekitException.createInternalError(tce);
        }
    }

}
