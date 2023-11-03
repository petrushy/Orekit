package org.orekit.estimation.measurements.generation;

import org.hipparchus.random.CorrelatedRandomVectorGenerator;
import org.orekit.estimation.measurements.ObservableSatellite;
import org.orekit.estimation.measurements.ObservedMeasurement;
import org.orekit.estimation.measurements.generation.AbstractMeasurementBuilder;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.sampling.OrekitStepInterpolator;
import org.orekit.time.AbsoluteDate;

import java.util.Map;

public class PythonAbstractMeasurementBuilder<T extends ObservedMeasurement<T>> extends AbstractMeasurementBuilder<T> {
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
     * Simple constructor.
     *
     * @param noiseSource noise source, may be null for generating perfect measurements
     * @param sigma       theoretical standard deviation
     * @param baseWeight  base weight
     * @param satellites  satellites related to this builder
     */
    public PythonAbstractMeasurementBuilder(CorrelatedRandomVectorGenerator noiseSource, double sigma, double baseWeight, ObservableSatellite... satellites) {
        super(noiseSource, sigma, baseWeight, satellites);
    }

    /**
     * Simple constructor.
     *
     * @param noiseSource noise source, may be null for generating perfect measurements
     * @param sigma       theoretical standard deviation
     * @param baseWeight  base weight
     * @param satellites  satellites related to this builder
     */
    public PythonAbstractMeasurementBuilder(CorrelatedRandomVectorGenerator noiseSource, double[] sigma, double[] baseWeight, ObservableSatellite... satellites) {
        super(noiseSource, sigma, baseWeight, satellites);
    }

    /**
     * Get the start of the measurements time span.
     *
     * @return start of the measurements time span
     */
    @Override
    public AbsoluteDate getStart() {
        return super.getStart();
    }

    /**
     * Get the end of the measurements time span.
     *
     * @return end of the measurements time span
     */
    @Override
    public AbsoluteDate getEnd() {
        return super.getEnd();
    }

    /**
     * Generate a noise vector.
     *
     * @return noise vector (null if we generate perfect measurements)
     */
    @Override
    public double[] getNoise() {
        return super.getNoise();
    }

    /**
     * Get the theoretical standard deviation.
     * <p>
     * The theoretical standard deviation is a theoretical value
     * used for normalizing the residuals. It acts as a weighting
     * factor to mix appropriately measurements with different units
     * and different accuracy. The value has the same dimension as
     * the measurement itself (i.e. when a residual is divided by
     * this value, it becomes dimensionless).
     * </p>
     *
     * @return expected standard deviation
     * @see #getBaseWeight()
     */
    @Override
    public double[] getTheoreticalStandardDeviation() {
        return super.getTheoreticalStandardDeviation();
    }

    /**
     * Get the base weight associated with the measurement
     * <p>
     * The base weight is used on residuals already normalized thanks to
     * {@link #getTheoreticalStandardDeviation()} to increase or
     * decrease relative effect of some measurements with respect to
     * other measurements. It is a dimensionless value, typically between
     * 0 and 1 (but it can really have any non-negative value).
     * </p>
     *
     * @return base weight
     * @see #getTheoreticalStandardDeviation()
     */
    @Override
    public double[] getBaseWeight() {
        return super.getBaseWeight();
    }

    /**
     * Get the satellites related to this measurement.
     *
     * @return satellites related to this measurement
     */
    @Override
    public ObservableSatellite[] getSatellites() {
        return super.getSatellites();
    }

    @Override
    public native T build(AbsoluteDate date, Map<ObservableSatellite, OrekitStepInterpolator> interpolators);

}
