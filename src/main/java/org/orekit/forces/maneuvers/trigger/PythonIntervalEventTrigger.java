package org.orekit.forces.maneuvers.trigger;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.Field;
import org.orekit.propagation.FieldPropagator;
import org.orekit.propagation.events.AbstractDetector;
import org.orekit.propagation.events.FieldAbstractDetector;
import org.orekit.propagation.events.FieldEventDetector;
import org.orekit.propagation.events.handlers.FieldEventHandler;

public class PythonIntervalEventTrigger<T extends AbstractDetector<T>> extends IntervalEventTrigger<T> {

    /** Part of JCC Python interface to object */
    private long pythonObject;

    /**
     * Simple constructor.
     * <p>
     * Note that the {@code intervalDetector} passed as an argument is used only
     * as a <em>prototype</em> from which a new detector will be built using its
     * {@link AbstractDetector#withHandler(EventHandler) withHandler} method to
     * set up an internal handler. The original event handler from the prototype
     * will be <em>ignored</em> and never called.
     * </p>
     * <p>
     * If the trigger is used in a {@link FieldPropagator field-based propagation},
     * the detector will be automatically converted to a field equivalent. Beware however that the
     * {@link FieldEventHandler#eventOccurred(FieldSpacecraftState, FieldEventDetector, boolean) eventOccurred}
     * of the converted propagator <em>will</em> call the method with the same name in the prototype
     * detector, in order to get the correct return value.
     * </p>
     *
     * @param prototypeFiringIntervalDetector prototype detector for firing interval
     */
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

    /**
     * Convert a primitive firing intervals detector into a field firing intervals detector.
     * <p>
     * There is not need to set up {@link FieldAbstractDetector#withMaxCheck(CalculusFieldElement) withMaxCheck},
     * {@link FieldAbstractDetector#withThreshold(CalculusFieldElement) withThreshold}, or
     * {@link FieldAbstractDetector#withHandler(FieldEventHandler) withHandler}
     * in the converted detector, this will be done by caller.
     * </p>
     * <p>
     * A skeleton implementation of this method to convert some {@code XyzDetector} into {@code FieldXyzDetector},
     * considering these detectors are created from a date and a number parameter is:
     * </p>
     * <pre>{@code
     *     protected <D extends FieldEventDetector<S>, S extends CalculusFieldElement<S>>
     *         FieldAbstractDetector<D, S> convertIntervalDetector(final Field<S> field, final XyzDetector detector) {
     *
     *         final FieldAbsoluteDate<S> date  = new FieldAbsoluteDate<>(field, detector.getDate());
     *         final S                    param = field.getZero().newInstance(detector.getParam());
     *
     *         final FieldAbstractDetector<D, S> converted = (FieldAbstractDetector<D, S>) new FieldXyzDetector<>(date, param);
     *         return converted;
     *
     *     }
     * }
     * </pre>
     *
     * @param field    field to which the state belongs
     * @param detector primitive firing intervals detector to convert
     * @return converted firing intervals detector
     */
    @Override
    public native  <D extends FieldEventDetector<S>, S extends CalculusFieldElement<S>> FieldAbstractDetector<D, S> convertIntervalDetector(Field<S> field, T detector);

    /* TODO: What should be exposed of this class? */
}
