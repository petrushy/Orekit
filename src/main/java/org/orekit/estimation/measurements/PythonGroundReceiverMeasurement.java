package org.orekit.estimation.measurements;

import org.orekit.propagation.SpacecraftState;
import org.orekit.time.AbsoluteDate;

public class PythonGroundReceiverMeasurement<T extends GroundReceiverMeasurement<T>> extends GroundReceiverMeasurement<T> {
    public PythonGroundReceiverMeasurement(GroundStation station, boolean twoWay, AbsoluteDate date, double observed, double sigma, double baseWeight, ObservableSatellite satellite) {
        super(station, twoWay, date, observed, sigma, baseWeight, satellite);
    }

    /** Part of JCC Python interface to object */
    protected long pythonObject;

    /** Part of JCC Python interface to object */
    public void pythonExtension(long pythonObject) {
        this.pythonObject = pythonObject;
    }

    /** Part of JCC Python interface to object */
    public long pythonExtension() {
        return this.pythonObject;
    }

    /** Part of JCC Python interface to object */
    public void finalize() throws Throwable {
        pythonDecRef();
    }

    /** Part of JCC Python interface to object */
    public native void pythonDecRef();

    @Override
    public native EstimatedMeasurementBase<T> theoreticalEvaluationWithoutDerivatives(int iteration, int evaluation, SpacecraftState[] states);

    @Override
    public native EstimatedMeasurement<T> theoreticalEvaluation(int iteration, int evaluation, SpacecraftState[] states);
}
