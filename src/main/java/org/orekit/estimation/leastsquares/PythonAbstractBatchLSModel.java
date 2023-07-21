package org.orekit.estimation.leastsquares;

import org.hipparchus.linear.RealMatrix;
import org.hipparchus.linear.RealVector;
import org.hipparchus.util.Incrementor;
import org.hipparchus.util.Pair;
import org.orekit.estimation.measurements.EstimatedMeasurement;
import org.orekit.estimation.measurements.ObservedMeasurement;
import org.orekit.orbits.Orbit;
import org.orekit.propagation.MatricesHarvester;
import org.orekit.propagation.Propagator;
import org.orekit.propagation.conversion.OrbitDeterminationPropagatorBuilder;
import org.orekit.propagation.integration.AbstractIntegratedPropagator;
import org.orekit.propagation.integration.AbstractJacobiansMapper;
import org.orekit.utils.ParameterDriversList;

import java.util.List;

public class PythonAbstractBatchLSModel extends AbstractBatchLSModel {

    /** Part of JCC Python interface to object */
    private long pythonObject;

    /**
     * Constructor.
     *
     * @param propagatorBuilders              builders to use for propagation
     * @param measurements                    measurements
     * @param estimatedMeasurementsParameters estimated measurements parameters
     * @param mappers                         jacobian mappers
     * @param observer                        observer to be notified at model calls
     */
    public PythonAbstractBatchLSModel(OrbitDeterminationPropagatorBuilder[] propagatorBuilders, List<ObservedMeasurement<?>> measurements, ParameterDriversList estimatedMeasurementsParameters, AbstractJacobiansMapper[] mappers, ModelObserver observer) {
        super(propagatorBuilders, measurements, estimatedMeasurementsParameters, mappers, observer);
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
    public native ParameterDriversList getSelectedPropagationDriversForBuilder(int iBuilder);

    /** {@inheritDoc} */
    @Override
    public native AbstractIntegratedPropagator[] createPropagators(RealVector point);

    /** {@inheritDoc} */
    @Override
    public native void fetchEvaluatedMeasurement(int index, EstimatedMeasurement<?> evaluation);

    /** {@inheritDoc} */
    @Override
    public native void setEvaluationsCounter(Incrementor evaluationsCounter);

    /** {@inheritDoc} */
    @Override
    public native void setIterationsCounter(Incrementor iterationsCounter);

    /** {@inheritDoc} */
    @Override
    public native int getIterationsCount();

    /** {@inheritDoc} */
    @Override
    public native int getEvaluationsCount();

    /** {@inheritDoc} */
    @Override
    public native boolean isForwardPropagation();

    /** {@inheritDoc} */
    @Override
    public native AbstractJacobiansMapper configureDerivatives(Propagator propagators);

    /** {@inheritDoc} */
    @Override
    public native Pair<RealVector, RealMatrix> value(RealVector realVector);

    /** {@inheritDoc} */
    @Override
    public native Orbit configureOrbits(MatricesHarvester harvester, Propagator propagator);
}
