package org.orekit.propagation;


import org.hipparchus.geometry.euclidean.threed.Vector3D;


/**
 * Python implementation of the CartesianToleranceProvider interface.
 * This class is part of the JCC Python interface and exposes all methods natively.
 */
public class PythonCartesianToleranceProvider implements CartesianToleranceProvider {

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

    //** {@inheritDoc} */
    @Override
    public native double[][] getTolerances(Vector3D position, Vector3D velocity);

  }