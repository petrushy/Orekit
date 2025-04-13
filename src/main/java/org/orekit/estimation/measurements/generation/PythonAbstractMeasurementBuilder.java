package org.orekit.estimation.measurements.generation;

import org.hipparchus.random.CorrelatedRandomVectorGenerator;
import org.orekit.estimation.measurements.ObservableSatellite;
import org.orekit.estimation.measurements.ObservedMeasurement;
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

    /** {@inheritDoc} */
    @Override
    public native AbsoluteDate getStart();

    /** {@inheritDoc} */
    @Override
    public native AbsoluteDate getEnd();

    /** {@inheritDoc} */
    @Override
    public native double[] getNoise();

    /** {@inheritDoc} */
    @Override
    public native double[] getTheoreticalStandardDeviation();

    /** {@inheritDoc} */
    @Override
    public native double[] getBaseWeight();

    /** {@inheritDoc} */
    @Override
    public native ObservableSatellite[] getSatellites();

    /** {@inheritDoc} */
    @Override
    public native T buildObserved(AbsoluteDate date, Map<ObservableSatellite, OrekitStepInterpolator> interpolators);
}