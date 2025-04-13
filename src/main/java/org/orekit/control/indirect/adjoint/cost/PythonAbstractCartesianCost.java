package org.orekit.control.indirect.adjoint.cost;

import org.hipparchus.geometry.euclidean.threed.Vector3D;

/**
 * Python implementation of the AbstractCartesianCost class.
 * This class is part of the JCC Python interface and exposes abstract methods natively.
 */
public class PythonAbstractCartesianCost extends AbstractCartesianCost {

    /** Part of JCC Python interface to object */
    private long pythonObject;

    /**
     * Constructor.
     *
     * @param name               name
     * @param massFlowRateFactor mass flow rate factor
     */
    protected PythonAbstractCartesianCost(String name, double massFlowRateFactor) {
        super(name, massFlowRateFactor);
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
     * {@inheritDoc}
     */
    @Override
    public native Vector3D getThrustAccelerationVector(double[] adjointVariables, double mass);

    /**
     * {@inheritDoc}
     */
    @Override
    public native void updateAdjointDerivatives(double[] adjointVariables, double mass, double[] adjointDerivatives);

    /**
     * {@inheritDoc}
     */
    @Override
    public native double getHamiltonianContribution(double[] adjointVariables, double mass);
}