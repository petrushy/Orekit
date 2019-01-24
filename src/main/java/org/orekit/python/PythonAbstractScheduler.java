package org.orekit.python;

import org.orekit.estimation.measurements.ObservedMeasurement;
import org.orekit.estimation.measurements.generation.AbstractScheduler;
import org.orekit.estimation.measurements.generation.MeasurementBuilder;
import org.orekit.propagation.sampling.OrekitStepInterpolator;
import org.orekit.time.DatesSelector;

import java.util.List;
import java.util.SortedSet;

public class PythonAbstractScheduler<T extends ObservedMeasurement<T>> extends AbstractScheduler<T> {
    /** Part of JCC Python interface to object */
    private long pythonObject;

    /**
     * Simple constructor.
     *
     * @param builder  builder for individual measurements
     * @param selector selector for dates
     */
    protected PythonAbstractScheduler(MeasurementBuilder<T> builder, DatesSelector selector) {
        super(builder, selector);
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
     * Generate a sequence of measurements.
     *
     * @param interpolators interpolators for spacecraft states
     * @return generated measurements
     */
    @Override
    public native SortedSet<T> generate(List<OrekitStepInterpolator> interpolators);
}
