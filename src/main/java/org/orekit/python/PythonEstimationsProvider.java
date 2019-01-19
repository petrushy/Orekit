package org.orekit.python;

import org.orekit.estimation.measurements.EstimatedMeasurement;
import org.orekit.estimation.measurements.EstimationsProvider;

public class PythonEstimationsProvider implements EstimationsProvider {

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
     * Get the number of evaluations available.
     *
     * @return number of evaluations available
     */
    @Override
    public native int getNumber();

    /**
     * Get one estimated measurement.
     *
     * @param index index of the estimated measurement, must be between 0
     *              and {@link #getNumber() getNumber()} - 1, chronologically
     *              sorted
     * @return estimated measurement at specified index
     */
    @Override
    public native EstimatedMeasurement<?> getEstimatedMeasurement(int index);
}
