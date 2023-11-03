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
import org.orekit.orbits.PositionAngleType;
import org.orekit.utils.TimeStampedPVCoordinates;

import java.util.stream.Stream;

// TODO more abstract classes in this package to be subclassed

public class PythonOrbit extends Orbit {

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

    /**
     * Get the orbit type.
     *
     * @return orbit type
     */
    @Override
    public native OrbitType getType();

    /**
     * Get the semi-major axis.
     * <p>Note that the semi-major axis is considered negative for hyperbolic orbits.</p>
     *
     * @return semi-major axis (m)
     */
    @Override
    public native double getA();

    /**
     * Get the semi-major axis derivative.
     * <p>Note that the semi-major axis is considered negative for hyperbolic orbits.</p>
     * <p>
     * If the orbit was created without derivatives, the value returned is {@link Double#NaN}.
     * </p>
     *
     * @return semi-major axis  derivative (m/s)
     * @see #hasDerivatives()
     * @since 9.0
     */
    @Override
    public native double getADot();

    /**
     * Get the first component of the equinoctial eccentricity vector derivative.
     *
     * @return first component of the equinoctial eccentricity vector derivative
     */
    @Override
    public native double getEquinoctialEx();

    /**
     * Get the first component of the equinoctial eccentricity vector.
     * <p>
     * If the orbit was created without derivatives, the value returned is {@link Double#NaN}.
     * </p>
     *
     * @return first component of the equinoctial eccentricity vector
     * @see #hasDerivatives()
     * @since 9.0
     */
    @Override
    public native double getEquinoctialExDot();

    /**
     * Get the second component of the equinoctial eccentricity vector derivative.
     *
     * @return second component of the equinoctial eccentricity vector derivative
     */
    @Override
    public native double getEquinoctialEy();

    /**
     * Get the second component of the equinoctial eccentricity vector.
     * <p>
     * If the orbit was created without derivatives, the value returned is {@link Double#NaN}.
     * </p>
     *
     * @return second component of the equinoctial eccentricity vector
     * @see #hasDerivatives()
     * @since 9.0
     */
    @Override
    public native double getEquinoctialEyDot();

    /**
     * Get the first component of the inclination vector.
     *
     * @return first component of the inclination vector
     */
    @Override
    public native double getHx();

    /**
     * Get the first component of the inclination vector derivative.
     * <p>
     * If the orbit was created without derivatives, the value returned is {@link Double#NaN}.
     * </p>
     *
     * @return first component of the inclination vector derivative
     * @see #hasDerivatives()
     * @since 9.0
     */
    @Override
    public native double getHxDot();

    /**
     * Get the second component of the inclination vector.
     *
     * @return second component of the inclination vector
     */
    @Override
    public native double getHy();

    /**
     * Get the second component of the inclination vector derivative.
     * <p>
     * If the orbit was created without derivatives, the value returned is {@link Double#NaN}.
     * </p>
     *
     * @return second component of the inclination vector derivative
     * @see #hasDerivatives()
     * @since 9.0
     */
    @Override
    public native double getHyDot();

    /**
     * Get the eccentric longitude argument.
     *
     * @return E + ω + Ω eccentric longitude argument (rad)
     */
    @Override
    public native double getLE();

    /**
     * Get the eccentric longitude argument derivative.
     * <p>
     * If the orbit was created without derivatives, the value returned is {@link Double#NaN}.
     * </p>
     *
     * @return d(E + ω + Ω)/dt eccentric longitude argument derivative (rad/s)
     * @see #hasDerivatives()
     * @since 9.0
     */
    @Override
    public native double getLEDot();

    /**
     * Get the true longitude argument.
     *
     * @return v + ω + Ω true longitude argument (rad)
     */
    @Override
    public native double getLv();

    /**
     * Get the true longitude argument derivative.
     * <p>
     * If the orbit was created without derivatives, the value returned is {@link Double#NaN}.
     * </p>
     *
     * @return d(v + ω + Ω)/dt true longitude argument derivative (rad/s)
     * @see #hasDerivatives()
     * @since 9.0
     */
    @Override
    public native double getLvDot();

    /**
     * Get the mean longitude argument.
     *
     * @return M + ω + Ω mean longitude argument (rad)
     */
    @Override
    public native double getLM();

    /**
     * Get the mean longitude argument derivative.
     * <p>
     * If the orbit was created without derivatives, the value returned is {@link Double#NaN}.
     * </p>
     *
     * @return d(M + ω + Ω)/dt mean longitude argument derivative (rad/s)
     * @see #hasDerivatives()
     * @since 9.0
     */
    @Override
    public native double getLMDot();

    /**
     * Get the eccentricity.
     *
     * @return eccentricity
     */
    @Override
    public native double getE();

    /**
     * Get the eccentricity derivative.
     * <p>
     * If the orbit was created without derivatives, the value returned is {@link Double#NaN}.
     * </p>
     *
     * @return eccentricity derivative
     * @see #hasDerivatives()
     * @since 9.0
     */
    @Override
    public native double getEDot();

    /**
     * Get the inclination.
     *
     * @return inclination (rad)
     */
    @Override
    public native double getI();

    /**
     * Get the inclination derivative.
     * <p>
     * If the orbit was created without derivatives, the value returned is {@link Double#NaN}.
     * </p>
     *
     * @return inclination derivative (rad/s)
     * @see #hasDerivatives()
     * @since 9.0
     */
    @Override
    public native double getIDot();

    @Override
    public native Vector3D initPosition();

    /**
     * Compute the position/velocity coordinates from the canonical parameters.
     *
     * @return computed position/velocity coordinates
     */
    @Override
    public native TimeStampedPVCoordinates initPVCoordinates();

    /**
     * Get a time-shifted orbit.
     * <p>
     * The orbit can be slightly shifted to close dates. The shifting model is a
     * Keplerian one if no derivatives are available in the orbit, or Keplerian
     * plus quadratic effect of the non-Keplerian acceleration if derivatives are
     * available. Shifting is <em>not</em> intended as a replacement for proper
     * orbit propagation but should be sufficient for small time shifts or coarse
     * accuracy.
     * </p>
     *
     * @param dt time shift in seconds
     * @return a new orbit, shifted with respect to the instance (which is immutable)
     */
    @Override
    public native Orbit shiftedBy(double dt);

    /**
     * Compute the Jacobian of the orbital parameters with mean angle with respect to the Cartesian parameters.
     * <p>
     * Element {@code jacobian[i][j]} is the derivative of parameter i of the orbit with
     * respect to Cartesian coordinate j. This means each row correspond to one orbital parameter
     * whereas columns 0 to 5 correspond to the Cartesian coordinates x, y, z, xDot, yDot and zDot.
     * </p>
     *
     * @return 6x6 Jacobian matrix
     * @see #computeJacobianEccentricWrtCartesian()
     * @see #computeJacobianTrueWrtCartesian()
     */
    @Override
    public native double[][] computeJacobianMeanWrtCartesian();

    /**
     * Compute the Jacobian of the orbital parameters with eccentric angle with respect to the Cartesian parameters.
     * <p>
     * Element {@code jacobian[i][j]} is the derivative of parameter i of the orbit with
     * respect to Cartesian coordinate j. This means each row correspond to one orbital parameter
     * whereas columns 0 to 5 correspond to the Cartesian coordinates x, y, z, xDot, yDot and zDot.
     * </p>
     *
     * @return 6x6 Jacobian matrix
     * @see #computeJacobianMeanWrtCartesian()
     * @see #computeJacobianTrueWrtCartesian()
     */
    @Override
    public native double[][] computeJacobianEccentricWrtCartesian();

    /**
     * Compute the Jacobian of the orbital parameters with true angle with respect to the Cartesian parameters.
     * <p>
     * Element {@code jacobian[i][j]} is the derivative of parameter i of the orbit with
     * respect to Cartesian coordinate j. This means each row correspond to one orbital parameter
     * whereas columns 0 to 5 correspond to the Cartesian coordinates x, y, z, xDot, yDot and zDot.
     * </p>
     *
     * @return 6x6 Jacobian matrix
     * @see #computeJacobianMeanWrtCartesian()
     * @see #computeJacobianEccentricWrtCartesian()
     */
    @Override
    public native double[][] computeJacobianTrueWrtCartesian();

    @Override
    public native void addKeplerContribution(PositionAngleType type, double gm, double[] pDot);

}
