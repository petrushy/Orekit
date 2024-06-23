package org.orekit.estimation.measurements.gnss;

import org.hipparchus.analysis.differentiation.Gradient;
import org.orekit.estimation.measurements.*;
import org.orekit.propagation.SpacecraftState;
import org.orekit.time.AbsoluteDate;
import org.orekit.utils.FieldPVCoordinatesProvider;
import org.orekit.utils.PVCoordinatesProvider;

import java.util.List;

public class PythonAbstractOnBoardMeasurement<T extends ObservedMeasurement<T>> extends AbstractOnBoardMeasurement<T> {
    /**
     * Constructor.
     *
     * @param date       date of the measurement
     * @param observed   observed value
     * @param sigma      theoretical standard deviation
     * @param baseWeight base weight
     * @param satellites satellites related to this measurement
     */
    public PythonAbstractOnBoardMeasurement(AbsoluteDate date, double observed, double sigma, double baseWeight, List<ObservableSatellite> satellites) {
        super(date, observed, sigma, baseWeight, satellites);
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

    @Override
    public native QuadraticClockModel getRemoteClock();

    @Override
    public native PVCoordinatesProvider getRemotePV(SpacecraftState[] states);

    @Override
    public native FieldPVCoordinatesProvider<Gradient> getRemotePV(SpacecraftState[] states, int freeParameters);
}
