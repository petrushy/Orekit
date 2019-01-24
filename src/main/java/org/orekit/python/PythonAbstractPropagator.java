package org.orekit.python;

import org.orekit.propagation.AbstractPropagator;
import org.orekit.propagation.BoundedPropagator;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.events.EventDetector;
import org.orekit.time.AbsoluteDate;

import java.util.Collection;

public class PythonAbstractPropagator extends AbstractPropagator {

    /** Part of JCC Python interface to object */
    private long pythonObject;

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
    public native BoundedPropagator getGeneratedEphemeris();

    /**
     * {@inheritDoc}
     *
     * @param detector
     */
    @Override
    public native <T extends EventDetector> void addEventDetector(T detector);

    /**
     * {@inheritDoc}
     */
    @Override
    public native Collection<EventDetector> getEventsDetectors();

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
    public native SpacecraftState propagate(AbsoluteDate start, AbsoluteDate target);
}
