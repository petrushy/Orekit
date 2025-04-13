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
    
    /** Build a new instance.
     * @param detectionSettings event detection settings
     * @param handler event handler to call at event occurrences
     * @since 12.2
     */
    public PythonFieldAbstractDetector(final FieldEventDetectionSettings<T> detectionSettings,
                                       final FieldEventHandler<T> handler) {
        super(detectionSettings, handler);
    }

    /** {@inheritDoc} */
    @Override
    public native T g(FieldSpacecraftState<T> s);

    /** {@inheritDoc} */
    @Override
    protected native D create(FieldEventDetectionSettings<T> detectionSettings, FieldEventHandler<T> newHandler);
}
