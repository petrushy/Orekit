package org.orekit.python;

import org.hipparchus.Field;
import org.hipparchus.RealFieldElement;
import org.orekit.propagation.FieldAbstractPropagator;
import org.orekit.propagation.FieldBoundedPropagator;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.events.FieldEventDetector;
import org.orekit.time.FieldAbsoluteDate;

import java.util.Collection;

public class PythonFieldAbstractPropagator<T extends RealFieldElement<T>> extends FieldAbstractPropagator<T> {
    /** Part of JCC Python interface to object */
    private long pythonObject;

    /**
     * Build a new instance.
     *
     * @param field setting the field
     */
    public PythonFieldAbstractPropagator(Field<T> field) {
        super(field);
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
     */
    @Override
    public native FieldBoundedPropagator<T> getGeneratedEphemeris();

    /**
     * {@inheritDoc}
     *
     * @param detector
     */
    @Override
    public native <D extends FieldEventDetector<T>> void addEventDetector(D detector);

    /**
     * {@inheritDoc}
     */
    @Override
    public native Collection<FieldEventDetector<T>> getEventsDetectors();

    /**
     * {@inheritDoc}
     */
    @Override
    public native void clearEventsDetectors();

    /**
     * Propagate from a start date towards a target date.
     * <p>Those propagators use a start date and a target date to
     * compute the propagated state. For propagators using event detection mechanism,
     * if the provided start date is different from the initial state date, a first,
     * simple propagation is performed, without processing any event computation.
     * Then complete propagation is performed from start date to target date.</p>
     *
     * @param start  start date from which orbit state should be propagated
     * @param target target date to which orbit state should be propagated
     * @return propagated state
     */
    @Override
    public native FieldSpacecraftState<T> propagate(FieldAbsoluteDate<T> start, FieldAbsoluteDate<T> target);
}
