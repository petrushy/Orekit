package org.orekit.python;

import org.hipparchus.RealFieldElement;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.events.FieldAbstractDetector;
import org.orekit.propagation.events.FieldEventDetector;
import org.orekit.propagation.events.handlers.FieldEventHandler;

public class PythonFieldAbstractDetector<D extends FieldEventDetector<T>,
                                            T extends RealFieldElement<T>> extends FieldAbstractDetector<D, T> {
    /** Part of JCC Python interface to object */
    private long pythonObject;

    /**
     * Build a new instance.
     *
     * @param maxCheck  maximum checking interval (s)
     * @param threshold convergence threshold (s)
     * @param maxIter   maximum number of iterations in the event time search
     * @param handler   event handler to call at event occurrences
     */
    protected PythonFieldAbstractDetector(T maxCheck, T threshold, int maxIter, FieldEventHandler<? super D, T> handler) {
        super(maxCheck, threshold, maxIter, handler);
    }

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
     * {@inheritDoc}
     *
     * @param s
     */
    @Override
    public native T g(FieldSpacecraftState<T> s);

    /**
     * Build a new instance.
     *
     * @param newMaxCheck  maximum checking interval (s)
     * @param newThreshold convergence threshold (s)
     * @param newMaxIter   maximum number of iterations in the event time search
     * @param newHandler   event handler to call at event occurrences
     * @return a new instance of the appropriate sub-type
     */
    @Override
    public native D create(T newMaxCheck, T newThreshold, int newMaxIter, FieldEventHandler<? super D, T> newHandler);
}
