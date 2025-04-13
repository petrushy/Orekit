
package org.orekit.propagation;

import org.orekit.frames.Frame;
import org.orekit.frames.LOFType;
import org.orekit.orbits.Orbit;
import org.orekit.orbits.OrbitType;
import org.orekit.orbits.PositionAngleType;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.TimeInterpolator;
import org.orekit.time.TimeStampedPair;

import java.util.List;

/**
 * Python implementation of the AbstractStateCovarianceInterpolator class.
 * This class is part of the JCC Python interface and exposes abstract methods natively.
 */
public class PythonAbstractStateCovarianceInterpolator
        extends AbstractStateCovarianceInterpolator {

    /** Part of JCC Python interface to object */
    private long pythonObject;

    /**
     * Constructor.
     *
     * @param interpolationPoints number of interpolation points
     * @param extrapolationThreshold extrapolation threshold beyond which the propagation will fail
     * @param orbitInterpolator orbit interpolator
     * @param outLOF local orbital frame
     */
    public PythonAbstractStateCovarianceInterpolator(final int interpolationPoints, final double extrapolationThreshold,
                                                     final TimeInterpolator<Orbit> orbitInterpolator,
                                                     final LOFType outLOF) {
        super(interpolationPoints, extrapolationThreshold, orbitInterpolator, outLOF);
    }

    /**
     * Constructor.
     *
     * @param interpolationPoints number of interpolation points
     * @param extrapolationThreshold extrapolation threshold beyond which the propagation will fail
     * @param orbitInterpolator orbit interpolator
     * @param outFrame desired output covariance frame
     * @param outPositionAngleType desired output position angle
     * @param outOrbitType desired output orbit type
     */
    public PythonAbstractStateCovarianceInterpolator(final int interpolationPoints, final double extrapolationThreshold,
                                                     final TimeInterpolator<Orbit> orbitInterpolator,
                                                     final Frame outFrame,
                                                     final OrbitType outOrbitType,
                                                     final PositionAngleType outPositionAngleType) {
        super(interpolationPoints, extrapolationThreshold, orbitInterpolator, outFrame, outOrbitType, outPositionAngleType);
    }

    /**
     * Part of JCC Python interface to object
     */
    public void pythonExtension(long pythonObject) {
        this.pythonObject = pythonObject;
    }

    /**
     * Part of JCC Python interface to object
     */
    public long pythonExtension() {
        return this.pythonObject;
    }

    /**
     * Part of JCC Python interface to object
     */
    public void finalize() throws Throwable {
        pythonDecRef();
    }

    /**
     * Part of JCC Python interface to object
     */
    public native void pythonDecRef();

    /**
     * Compute the interpolated covariance expressed in the interpolated orbit frame.
     *
     * @param uncertainStates list of orbits and associated covariances
     * @param interpolatedOrbit interpolated orbit
     *
     * @return interpolated covariance expressed in the interpolated orbit frame
     */
    @Override
    public native StateCovariance computeInterpolatedCovarianceInOrbitFrame(
            List<TimeStampedPair<Orbit, StateCovariance>> uncertainStates,
            Orbit interpolatedOrbit);
}