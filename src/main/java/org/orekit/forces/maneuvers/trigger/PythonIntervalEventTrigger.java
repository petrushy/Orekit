package org.orekit.forces.maneuvers.trigger;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.Field;
import org.orekit.propagation.FieldPropagator;
import org.orekit.propagation.events.AbstractDetector;
import org.orekit.propagation.events.FieldAbstractDetector;
import org.orekit.propagation.events.FieldEventDetector;
import org.orekit.propagation.events.handlers.FieldEventHandler;
import org.orekit.utils.ParameterDriver;

import java.util.List;

public class PythonIntervalEventTrigger<T extends AbstractDetector<T>> extends IntervalEventTrigger<T> {

    /** Part of JCC Python interface to object */
    private long pythonObject;

    public PythonIntervalEventTrigger(T prototypeFiringIntervalDetector) {
        super(prototypeFiringIntervalDetector);
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

    @Override
    public native  <D extends FieldAbstractDetector<D, S>, S extends CalculusFieldElement<S>> FieldAbstractDetector<D, S> convertIntervalDetector(Field<S> field, T detector);

    @Override
    public native List<ParameterDriver> getParametersDrivers();

    /* TODO: What should be exposed of this class? */
}
