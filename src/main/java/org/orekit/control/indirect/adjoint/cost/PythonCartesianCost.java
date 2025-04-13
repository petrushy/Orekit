package org.orekit.control.indirect.adjoint.cost;

import org.hipparchus.geometry.euclidean.threed.Vector3D;

/**
 * Python implementation of the CartesianCost interface.
 * This class is part of the JCC Python interface and exposes all methods natively.
 */
public class PythonCartesianCost implements CartesianCost {

    /** Part of JCC Python interface to object */
    private long pythonObject;

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
    public native String getAdjointName();

    /**
     * {@inheritDoc}
     */
    @Override
    public native int getAdjointDimension();

    /**
     * {@inheritDoc}
     */
    @Override
    public native double getMassFlowRateFactor();

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