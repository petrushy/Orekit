
package org.orekit.propagation;

import org.orekit.time.AbsoluteDate;

/**
 * Python implementation of the AdditionalDataProvider interface.
 * This class is part of the JCC Python interface and exposes all methods natively.
 */
public class PythonAdditionalDataProvider<T> implements AdditionalDataProvider<T> {

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

    /** {@inheritDoc} */
    @Override
    public native String getName();

    /** {@inheritDoc} */
    @Override
    public native T getAdditionalData(SpacecraftState state);

}