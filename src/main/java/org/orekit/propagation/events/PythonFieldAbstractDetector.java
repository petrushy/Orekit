package org.orekit.propagation.events;

import org.hipparchus.CalculusFieldElement;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.events.handlers.FieldEventHandler;

public class PythonFieldAbstractDetector<D extends FieldAbstractDetector<D, T>, T extends CalculusFieldElement<T>> extends FieldAbstractDetector<D, T> {

    /** Part of JCC Python interface to object */
    protected long pythonObject;

    /** Part of JCC Python interface to object */
    public void pythonExtension(long pythonObject) {
        this.pythonObject = pythonObject;
    }

    /** Part of JCC Python interface to object */
    public long pythonExtension() {
        return this.pythonObject;
    }

    /** Part of JCC Python interface to object */
    public void finalize() throws Throwable {
        pythonDecRef();
    }

    /** Part of JCC Python interface to object */
    public native void pythonDecRef();

    /**
     * Build a new instance.
     *
     * @param maxCheck  maximum checking interval
     * @param threshold convergence threshold (s)
     * @param maxIter   maximum number of iterations in the event time search
     * @param handler   event handler to call at event occurrences
     */
    public PythonFieldAbstractDetector(FieldAdaptableInterval<T> maxCheck, T threshold, int maxIter, FieldEventHandler<T> handler) {
        super(maxCheck, threshold, maxIter, handler);
    }

    @Override
    public native T g(FieldSpacecraftState<T> s);

    @Override
    public native D create(FieldAdaptableInterval<T> newMaxCheck, T newThreshold, int newMaxIter, FieldEventHandler<T> newHandler);
}
