package org.orekit.estimation.measurements.gnss;

import org.orekit.estimation.measurements.EstimatedMeasurement;
import org.orekit.estimation.measurements.EstimatedMeasurementBase;
import org.orekit.estimation.measurements.ObservableSatellite;
import org.orekit.estimation.measurements.ObservedMeasurement;
import org.orekit.propagation.SpacecraftState;
import org.orekit.time.AbsoluteDate;

public class PythonAbstractInterSatellitesMeasurement<T extends ObservedMeasurement<T>> extends AbstractInterSatellitesMeasurement<T> {
    /**
     * Constructor.
     *
     * @param date       date of the measurement
     * @param observed   observed value
     * @param sigma      theoretical standard deviation
     * @param baseWeight base weight
     * @param local      satellite which receives the signal and performs the measurement
     * @param remote     remote satellite which simply emits the signal
     */
    public PythonAbstractInterSatellitesMeasurement(AbsoluteDate date, double observed, double sigma, double baseWeight, ObservableSatellite local, ObservableSatellite remote) {
        super(date, observed, sigma, baseWeight, local, remote);
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
    public native EstimatedMeasurementBase<T> theoreticalEvaluationWithoutDerivatives(int iteration, int evaluation, SpacecraftState[] states);

    @Override
    public native EstimatedMeasurement<T> theoreticalEvaluation(int iteration, int evaluation, SpacecraftState[] states);
}
