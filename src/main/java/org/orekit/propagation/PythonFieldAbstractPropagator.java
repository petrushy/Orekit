package org.orekit.propagation;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.Field;
import org.orekit.propagation.events.FieldEventDetector;
import org.orekit.time.FieldAbsoluteDate;

import java.util.Collection;

public class PythonFieldAbstractPropagator<T extends CalculusFieldElement<T>> extends FieldAbstractPropagator<T> {
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
     * Set up an ephemeris generator that will monitor the propagation for building
     * an ephemeris from it once completed.
     *
     * <p>
     * This generator can be used when the user needs fast random access to the orbit
     * state at any time between the initial and target times. A typical example is the
     * implementation of search and iterative algorithms that may navigate forward and
     * backward inside the propagation range before finding their result even if the
     * propagator used is integration-based and only goes from one initial time to one
     * target time.
     * </p>
     * <p>
     * Beware that when used with integration-based propagators, the generator will
     * store <strong>all</strong> intermediate results. It is therefore memory intensive
     * for long integration-based ranges and high precision/short time steps. When
     * used with analytical propagators, the generator only stores start/stop time
     * and a reference to the analytical propagator itself to call it back as needed,
     * so it is less memory intensive.
     * </p>
     * <p>
     * The returned ephemeris generator will be initially empty, it will be filled
     * with propagation data when a subsequent call to either {@link #propagate(FieldAbsoluteDate)
     * propagate(target)} or {@link #propagate(FieldAbsoluteDate, FieldAbsoluteDate)
     * propagate(start, target)} is called. The proper way to use this method is
     * therefore to do:
     * </p>
     * <pre>
     *   FieldEphemerisGenerator&lt;T&gt; generator = propagator.getEphemerisGenerator();
     *   propagator.propagate(target);
     *   FieldBoundedPropagator&lt;T&gt; ephemeris = generator.getGeneratedEphemeris();
     * </pre>
     *
     * @return ephemeris generator
     */
    @Override
    public native FieldEphemerisGenerator<T> getEphemerisGenerator();

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
