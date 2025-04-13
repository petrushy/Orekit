package org.orekit.estimation.sequential;

import org.hipparchus.filtering.kalman.KalmanFilter;
import org.hipparchus.linear.MatrixDecomposer;
import org.orekit.propagation.conversion.PropagatorBuilder;

import java.util.List;

public class PythonAbstractKalmanEstimator extends AbstractKalmanEstimator {
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
     * Constructor.
     *
     * @param decomposer matrix decomposer for filter
     * @param builders   list of propagator builders
     */
    public PythonAbstractKalmanEstimator(MatrixDecomposer decomposer, List<? extends PropagatorBuilder> builders) {
        super(decomposer, builders);
    }

    @Override
    public native KalmanEstimation getKalmanEstimation();


    /** {@inheritDoc} */
    @Override
    public native KalmanFilter<MeasurementDecorator> getKalmanFilter();

    /** {@inheritDoc} */
    @Override
    public native double[] getScale();
}
