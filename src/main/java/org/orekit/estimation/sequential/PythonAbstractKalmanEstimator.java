package org.orekit.estimation.sequential;

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
     * @param builders list of propagator builders
     */
    public PythonAbstractKalmanEstimator(List<? extends PropagatorBuilder> builders) {
        super(builders);
    }

    @Override
    public native KalmanEstimation getKalmanEstimation();
}
