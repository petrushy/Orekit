
// this file was created by SSC 2025 and is largely a derived work from the
// original java class

package org.orekit.propagation.events.handlers;

import org.hipparchus.ode.events.Action;

public class PythonAbstractGenericCountingHandler extends AbstractGenericCountingHandler {

    /** Part of JCC Python interface to object */
    private long pythonObject;

    /** Constructor. */
    public PythonAbstractGenericCountingHandler(int startingCount, Action action) {
        super(startingCount, action);
    }

    /** Part of JCC Python interface to object */
    public void pythonExtension(long pythonObject) {
        this.pythonObject = pythonObject;
    }

    /** Part of JCC Python interface to object */
    public long pythonExtension() {
        return this.pythonObject;
    }

    /** Part of JCC Python interface to object */
    public void finalize()
            throws Throwable {
        pythonDecRef();
    }

    /** Part of JCC Python interface to object */
    public native void pythonDecRef();

    /** Getter for count. */
    @Override
    public native int getCount();

    /** Reset count. */
    @Override
    public native void reset();

    /** Getter for action. */
    @Override
    public native Action getAction();

    /** Setter for action. */
    @Override
    public native void setAction(Action action);

    /** Increment count. */
    @Override
    public native void increment();
}
