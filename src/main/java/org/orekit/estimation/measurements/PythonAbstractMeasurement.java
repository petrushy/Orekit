package org.orekit.estimation.measurements;

import org.orekit.propagation.SpacecraftState;
import org.orekit.time.AbsoluteDate;
import org.orekit.utils.ParameterDriver;

import java.util.List;

public class PythonAbstractMeasurement<T extends ObservedMeasurement<T>> extends AbstractMeasurement<T> {
    /** Part of JCC Python interface to object */
    private long pythonObject;

    /**
     * Simple constructor for mono-dimensional measurements.
     * <p>
     * At construction, a measurement is enabled.
     * </p>
     *
     * @param date       date of the measurement
     * @param observed   observed value
     * @param sigma      theoretical standard deviation
     * @param baseWeight base weight
     * @param satellites satellites related to this measurement
     * @since 9.3
     */
    public PythonAbstractMeasurement(AbsoluteDate date, double observed, double sigma, double baseWeight, List<ObservableSatellite> satellites) {
        super(date, observed, sigma, baseWeight, satellites);
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

    /** {@inheritDoc} */
    @Override
    public native EstimatedMeasurement<T> theoreticalEvaluation(int iteration, int evaluation, SpacecraftState[] states);

    /** {@inheritDoc} */
    @Override
    public void addParameterDriver(ParameterDriver driver) {
        super.addParameterDriver(driver);
    }

    /** {@inheritDoc} */
    @Override
    public native EstimatedMeasurementBase<T> theoreticalEvaluationWithoutDerivatives(int iteration, int evaluation, SpacecraftState[] states);
}
