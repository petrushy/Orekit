/* Copyright 2002-2010 CS Communication & Systèmes
 * Licensed to CS Communication & Systèmes (CS) under one or more
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

import org.apache.commons.math.geometry.Rotation;
import org.apache.commons.math.geometry.Vector3D;
import org.apache.commons.math.util.MathUtils;
import org.orekit.errors.OrekitException;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.DateComponents;
import org.orekit.time.TimeComponents;
import org.orekit.time.TimeScalesFactory;
import org.orekit.utils.Constants;

/** Greenwich True Of Date Frame, also known as True of Date Rotating frame (TDR)
 * or Greenwich Rotating Coordinate frame (GCR).
 * <p> This frame handles the sidereal time according to IAU-82 model.</p>
 * <p> Its parent frame is the {@link TODFrame}.</p>
 * <p> The pole motion is not applied here.</p>
 * @author Pascal Parraud
 * @author Thierry Ceolin
 * @version $Revision$ $Date$
 */
class GTODFrame extends FactoryManagedFrame {

    /** Serializable UID. */
    private static final long serialVersionUID = -7304302237325702464L;

    /** Radians per second of time. */
    private static final double RADIANS_PER_SECOND = MathUtils.TWO_PI / Constants.JULIAN_DAY;

    /** Angular velocity of the Earth, in rad/s. */
    private static final double AVE = 7.292115146706979e-5;

    /** Reference date for IAU 1982 GMST-UT1 model. */
    private static final AbsoluteDate GMST_REFERENCE =
        new AbsoluteDate(DateComponents.J2000_EPOCH, TimeComponents.H12, TimeScalesFactory.getTAI());

    /** First coefficient of IAU 1982 GMST-UT1 model. */
    private static final double GMST_0 = 24110.54841;

    /** Second coefficient of IAU 1982 GMST-UT1 model. */
    private static final double GMST_1 = 8640184.812866;

    /** Third coefficient of IAU 1982 GMST-UT1 model. */
    private static final double GMST_2 = 0.093104;

    /** Fourth coefficient of IAU 1982 GMST-UT1 model. */
    private static final double GMST_3 = -6.2e-6;

    /** Cached date to avoid useless calculus. */
    private AbsoluteDate cachedDate;

    /** Flag for EOP correction application. */
    private final boolean applyEOPCorrection;

    /** Simple constructor, applying EOP corrections (here, lod).
     * @param factoryKey key of the frame within the factory
     * @exception OrekitException if EOP parameters cannot be read
     */
    protected GTODFrame(final Predefined factoryKey)
        throws OrekitException {
        this(true, factoryKey);
    }

    /** Simple constructor.
     * @param applyEOPCorr if true, EOP corrections are applied (here, lod)
     * @param factoryKey key of the frame within the factory
     * @exception OrekitException if EOP parameters are desired but cannot be read
     */
    protected GTODFrame(final boolean applyEOPCorr, final Predefined factoryKey)
        throws OrekitException {

        super(FramesFactory.getTOD(applyEOPCorr), null, false, factoryKey);

        applyEOPCorrection = applyEOPCorr;

        // everything is in place, we can now synchronize the frame
        updateFrame(AbsoluteDate.J2000_EPOCH);

    }

    /** Indicate if EOP correction is applied.
     * @return true if EOP correction is applied
     */
    boolean isEOPCorrectionApplied() {
        return applyEOPCorrection;
    }

    /** Update the frame to the given date.
     * <p>The update considers the earth rotation from IERS data.</p>
     * @param date new value of the date
     * @exception OrekitException if the nutation model data embedded in the
     * library cannot be read
     */
    protected void updateFrame(final AbsoluteDate date) throws OrekitException {

        if ((cachedDate == null) || !cachedDate.equals(date)) {

            final TODFrame tod = (TODFrame) getParent();
            final MODFrame mod = (MODFrame) tod.getParent();

            // offset from J2000.0 epoch
            final double eqe = tod.getEquationOfEquinoxes(date);

            // offset in julian centuries from J2000 epoch (UT1 scale)
            final double dtai = date.durationFrom(GMST_REFERENCE);
            final double dutc = TimeScalesFactory.getUTC().offsetFromTAI(date);
            final double dut1 = FramesFactory.getEOP1980History().getUT1MinusUTC(date);

            final double tut1 = dtai + dutc + dut1;
            final double tt   = tut1 / Constants.JULIAN_CENTURY;

            // Seconds in the day, adjusted by 12 hours because the
            // UT1 is supplied as a Julian date beginning at noon.
            final double sd = (tut1 + Constants.JULIAN_DAY / 2.) % Constants.JULIAN_DAY;

            // compute Greenwich mean sidereal time, in radians
            final double gmst = (((GMST_3 * tt + GMST_2) * tt + GMST_1) * tt + GMST_0 + sd) *
                                RADIANS_PER_SECOND;

            // compute Greenwich apparent sidereal time, in radians
            final double gast = gmst + eqe;

            // compute true angular rotation of Earth, in rad/s
            final double lod = mod.getLOD(date);
            final double omp = AVE * (1 - lod / Constants.JULIAN_DAY);
            final Vector3D rotationRate = new Vector3D(omp, Vector3D.PLUS_K);

            // set up the transform from parent TOD
            setTransform(new Transform(new Rotation(Vector3D.PLUS_K, -gast), rotationRate));

            cachedDate = date;

        }
    }

}
