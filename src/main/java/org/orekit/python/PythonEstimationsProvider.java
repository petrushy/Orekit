package org.orekit.python;

import org.orekit.estimation.measurements.EstimatedMeasurement;
import org.orekit.estimation.measurements.EstimationsProvider;

public class PythonEstimationsProvider implements EstimationsProvider {
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
