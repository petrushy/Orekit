package org.orekit.estimation.sequential;

import org.hipparchus.linear.RealMatrix;
import org.orekit.estimation.sequential.AbstractCovarianceMatrixProvider;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.conversion.PropagatorBuilder;

public class PythonAbstractCovarianceMatrixProvider extends AbstractCovarianceMatrixProvider {
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
     * Simple constructor.
     *
     * @param initialNoiseMatrix initial process noise
     */
    public PythonAbstractCovarianceMatrixProvider(RealMatrix initialNoiseMatrix) {
        super(initialNoiseMatrix);
    }

    /**
     * Get the process noise matrix between previous and current states.
     * Extension point for Python.
     * <p>
     * The process noise matrix is a covariance matrix corresponding to the
     * parameters managed by the {@link KalmanEstimator Kalman estimator}.
     * The number of rows/columns and their order are as follows:
     * </p>
     * <ul>
     * <li>The first 6 components correspond to the 6 orbital parameters
     * of the associated propagator. All 6 parameters must always be present,
     * regardless of the fact they are estimated or not.</li>
     * <li>The following components correspond to the subset of propagation
     * parameters of the associated propagator that are estimated.</li>
     * <li>The remaining components correspond to the subset of measurements
     * parameters that are estimated, considering all measurements, even
     * the ones that correspond to spacecrafts not related to the
     * associated propagator</li>
     * </ul>
     * <p>
     * In most cases, the process noise for the part corresponding to measurements
     * (the final rows and columns) will be set to 0 for the process noise corresponding
     * to the evolution between a non-null previous and current state.
     * </p>
     *
     * @param previous previous state
     * @param current  current state
     * @return physical (i.e. non normalized) process noise matrix between
     * previous and current states
     * @see PropagatorBuilder#getOrbitalParametersDrivers()
     * @see PropagatorBuilder#getPropagationParametersDrivers()
     */
    @Override
    public native RealMatrix getProcessNoiseMatrix(SpacecraftState previous, SpacecraftState current);
}
