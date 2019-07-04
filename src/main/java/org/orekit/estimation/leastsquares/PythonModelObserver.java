package org.orekit.estimation.leastsquares;

import org.orekit.estimation.leastsquares.ModelObserver;
import org.orekit.estimation.measurements.EstimatedMeasurement;
import org.orekit.estimation.measurements.ObservedMeasurement;
import org.orekit.orbits.Orbit;

import java.util.Map;

public class PythonModelObserver implements ModelObserver {

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
     * Notification callback for orbit changes.
     *
     * @param orbits      current estimated orbits
     * @param estimations map of measurements estimations resulting from
     *                    the current estimated orbit (this is an unmodifiable view of the
     */
    @Override
    public native void modelCalled(Orbit[] orbits, Map<ObservedMeasurement<?>, EstimatedMeasurement<?>> estimations);
}
