package org.orekit.estimation.measurements.gnss;

import org.orekit.estimation.measurements.*;
import org.orekit.propagation.SpacecraftState;
import org.orekit.time.AbsoluteDate;
import org.orekit.utils.PVCoordinatesProvider;

public class PythonAbstractOneWayGNSSMeasurement<T extends ObservedMeasurement<T>> extends AbstractOneWayGNSSMeasurement<T> {
    /**
     * Simple constructor.
     *
     * @param remotePV    provider for GNSS satellite which simply emits the signal
     * @param remoteClock clock offset of the GNSS satellite
     * @param date        date of the measurement
     * @param range       observed value
     * @param sigma       theoretical standard deviation
     * @param baseWeight  base weight
     * @param local       satellite which receives the signal and perform the measurement
     */
    public PythonAbstractOneWayGNSSMeasurement(PVCoordinatesProvider remotePV, QuadraticClockModel remoteClock, AbsoluteDate date, double range, double sigma, double baseWeight, ObservableSatellite local) {
        super(remotePV, remoteClock, date, range, sigma, baseWeight, local);
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
