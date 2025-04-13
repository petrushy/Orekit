package org.orekit.estimation.measurements.generation;

import org.orekit.estimation.measurements.EstimatedMeasurementBase;
import org.orekit.estimation.measurements.ObservedMeasurement;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.DatesSelector;

import java.util.function.Predicate;

public class PythonAbstractScheduler<T extends ObservedMeasurement<T>> extends AbstractScheduler<T> {
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


        /** Simple constructor.
         * @param builder builder for individual measurements
         * @param selector selector for dates
         * @param filter predicate for a posteriori filtering of generated measurements
         *               (measurements are accepted if the predicates evaluates to {@code true})
         * @since 13.0
         */
        public PythonAbstractScheduler(final MeasurementBuilder<T> builder,
                                    final DatesSelector selector,
                                    final Predicate<EstimatedMeasurementBase<T>> filter) {
            super(builder, selector, filter);
        }


    @Override
    public native boolean measurementIsFeasible(AbsoluteDate date);

}
