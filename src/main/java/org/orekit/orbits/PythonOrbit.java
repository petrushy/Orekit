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

package org.orekit.orbits;

import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.frames.Frame;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.TimeOffset;
import org.orekit.utils.TimeStampedPVCoordinates;

public class PythonOrbit extends Orbit {

    /** Part of JCC Python interface to object */
    private long pythonObject;

    /** Part of JCC Python interface to object */
    public void pythonExtension(long pythonObject) {
        this.pythonObject = pythonObject;
    }

    /** Part of JCC Python interface to object */
    public long pythonExtension() {
        return this.pythonObject;
    }

    /** Part of JCC Python interface to object */
    public void finalize() throws Throwable {
        pythonDecRef();
    }

    /** Part of JCC Python interface to object */
    public native void pythonDecRef();

    /**
     * Default constructor.
     * Build a new instance with arbitrary default elements.
     *
     * @param frame the frame in which the parameters are defined
     *              (<em>must</em> be a {@link Frame#isPseudoInertial pseudo-inertial frame})
     * @param date  date of the orbital parameters
     * @param mu    central attraction coefficient (m^3/s^2)
     * @throws IllegalArgumentException if frame is not a {@link
     *                                  Frame#isPseudoInertial pseudo-inertial frame}
     */
    public PythonOrbit(Frame frame, AbsoluteDate date, double mu) throws IllegalArgumentException {
        super(frame, date, mu);
    }

    /**
     * Set the orbit from Cartesian parameters.
     *
     * <p> The acceleration provided in {@code pvCoordinates} is accessible using
     * {@link #getPVCoordinates()} and {@link #getPVCoordinates(Frame)}. All other methods
     * use {@code mu} and the position to compute the acceleration, including
     * {@link #shiftedBy(double)} and {@link #getPVCoordinates(AbsoluteDate, Frame)}.
     *
     * @param pvCoordinates the position and velocity in the inertial frame
     * @param frame         the frame in which the {@link TimeStampedPVCoordinates} are defined
     *                      (<em>must</em> be a {@link Frame#isPseudoInertial pseudo-inertial frame})
     * @param mu            central attraction coefficient (m^3/s^2)
     * @throws IllegalArgumentException if frame is not a {@link
     *                                  Frame#isPseudoInertial pseudo-inertial frame}
     */
    public PythonOrbit(TimeStampedPVCoordinates pvCoordinates, Frame frame, double mu) throws IllegalArgumentException {
        super(pvCoordinates, frame, mu);
    }

    /** {@inheritDoc} */
    @Override
    public native OrbitType getType();

    /** {@inheritDoc} */
    @Override
    public native double getA();

    /** {@inheritDoc} */
    @Override
    public native double getADot();

    /** {@inheritDoc} */
    @Override
    public native double getEquinoctialEx();

    /** {@inheritDoc} */
    @Override
    public native double getEquinoctialExDot();

    /** {@inheritDoc} */
    @Override
    public native double getEquinoctialEy();

    /** {@inheritDoc} */
    @Override
    public native double getEquinoctialEyDot();

    /** {@inheritDoc} */
    @Override
    public native double getHx();

    /** {@inheritDoc} */
    @Override
    public native double getHxDot();

    /** {@inheritDoc} */
    @Override
    public native double getHy();

    /** {@inheritDoc} */
    @Override
    public native double getHyDot();

    /** {@inheritDoc} */
    @Override
    public native double getLE();

    /** {@inheritDoc} */
    @Override
    public native double getLEDot();

    /** {@inheritDoc} */
    @Override
    public native double getLv();

    /** {@inheritDoc} */
    @Override
    public native double getLvDot();

    /** {@inheritDoc} */
    @Override
    public native double getLM();

    /** {@inheritDoc} */
    @Override
    public native double getLMDot();

    /** {@inheritDoc} */
    @Override
    public native double getE();

    /** {@inheritDoc} */
    @Override
    public native double getEDot();

    /** {@inheritDoc} */
    @Override
    public native double getI();

    /** {@inheritDoc} */
    @Override
    public native double getIDot();

    /** {@inheritDoc} */
    @Override
    public native Vector3D initPosition();

    /** {@inheritDoc} */
    @Override
    public native TimeStampedPVCoordinates initPVCoordinates();

    /** {@inheritDoc} */
    @Override
    public native Orbit shiftedBy(double dt);

    /** {@inheritDoc} */
    @Override
    public native double[][] computeJacobianMeanWrtCartesian();

    /** {@inheritDoc} */
    @Override
    public native double[][] computeJacobianEccentricWrtCartesian();

    /** {@inheritDoc} */
    @Override
    public native double[][] computeJacobianTrueWrtCartesian();

    /** {@inheritDoc} */
    @Override
    public native void addKeplerContribution(PositionAngleType type, double gm, double[] pDot);

    /** {@inheritDoc} */
    @Override
    public native Orbit inFrame(Frame inertialFrame);

    /** {@inheritDoc} */
    @Override
    public native Orbit shiftedBy(TimeOffset dt);
}
