package org.orekit.python;

import org.orekit.estimation.measurements.*;
import org.orekit.propagation.SpacecraftState;
import org.orekit.time.AbsoluteDate;

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

    /**
     * Estimate the theoretical value.
     * Extension point for Python.
     * <p>
     * The theoretical value does not have <em>any</em> modifiers applied.
     * </p>
     *
     * @param iteration  iteration number
     * @param evaluation evaluation number
     * @param states     orbital states at measurement date
     * @return theoretical value
     * @see #estimate(int, int, SpacecraftState[])
     */
    @Override
    public native EstimatedMeasurement<T> theoreticalEvaluation(int iteration, int evaluation, SpacecraftState[] states);

}
