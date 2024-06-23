package org.orekit.estimation.measurements.modifiers;

import org.orekit.estimation.measurements.EstimatedMeasurementBase;
import org.orekit.estimation.measurements.ObservedMeasurement;

public class PythonAbstractRelativisticClockOnBoardRangeRateModifier<T extends ObservedMeasurement<T>> extends AbstractRelativisticClockOnBoardRangeRateModifier<T> {
    /**
     * Simple constructor.
     *
     * @param gm gravitational constant for main body in signal path vicinity.
     */
    public PythonAbstractRelativisticClockOnBoardRangeRateModifier(double gm) {
        super(gm);
    }

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

    @Override
    public native void modifyWithoutDerivatives(EstimatedMeasurementBase<T> estimated);
}
