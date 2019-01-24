package org.orekit.python;

import org.hipparchus.analysis.MultivariateVectorFunction;
import org.hipparchus.optim.nonlinear.vector.leastsquares.MultivariateJacobianFunction;
import org.orekit.propagation.conversion.AbstractPropagatorConverter;
import org.orekit.propagation.conversion.PropagatorBuilder;

public class PythonAbstractPropagatorConverter extends AbstractPropagatorConverter {

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
     * Build a new instance.
     *
     * @param builder       propagator builder
     * @param threshold     absolute convergence threshold for optimization algorithm
     * @param maxIterations maximum number of iterations for fitting
     */
    public PythonAbstractPropagatorConverter(PropagatorBuilder builder, double threshold, int maxIterations) {
        super(builder, threshold, maxIterations);
    }

    /**
     * Get the function computing position/velocity at sample points.
     *
     * @return function computing position/velocity at sample points
     */
    @Override
    public native MultivariateVectorFunction getObjectiveFunction();

    /**
     * Get the Jacobian of the function computing position/velocity at sample points.
     *
     * @return Jacobian of the function computing position/velocity at sample points
     */
    @Override
    public native MultivariateJacobianFunction getModel();
}
