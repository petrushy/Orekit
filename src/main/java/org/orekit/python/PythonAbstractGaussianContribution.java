package org.orekit.python;

import org.orekit.forces.ForceModel;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.events.EventDetector;
import org.orekit.propagation.semianalytical.dsst.forces.AbstractGaussianContribution;

public class PythonAbstractGaussianContribution extends AbstractGaussianContribution {

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
     * @param coefficientsKeyPrefix prefix for coefficients keys
     * @param threshold             tolerance for the choice of the Gauss quadrature order
     * @param contribution          the {@link ForceModel} to be numerically averaged
     */
    public PythonAbstractGaussianContribution(String coefficientsKeyPrefix, double threshold, ForceModel contribution) {
        super(coefficientsKeyPrefix, threshold, contribution);
    }

    /**
     * Compute the limits in L, the true longitude, for integration.
     *
     * @param state current state information: date, kinematics, attitude
     * @return the integration limits in L
     */
    @Override
    public native double[] getLLimits(SpacecraftState state);

    /**
     * Get the discrete events related to the model.
     *
     * @return array of events detectors or null if the model is not
     * related to any discrete events
     */
    @Override
    public native EventDetector[] getEventsDetectors();
}
