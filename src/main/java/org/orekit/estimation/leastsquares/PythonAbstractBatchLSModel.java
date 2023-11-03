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
import org.orekit.propagation.conversion.PropagatorBuilder;
import org.orekit.propagation.integration.AbstractIntegratedPropagator;
import org.orekit.utils.ParameterDriversList;

import java.util.List;

public class PythonAbstractBatchLSModel extends AbstractBatchLSModel {

    /** Part of JCC Python interface to object */
    private long pythonObject;

    public PythonAbstractBatchLSModel(PropagatorBuilder[] propagatorBuilders, List<ObservedMeasurement<?>> measurements, ParameterDriversList estimatedMeasurementsParameters,  ModelObserver observer) {
        super(propagatorBuilders, measurements, estimatedMeasurementsParameters, observer);
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
    public native MatricesHarvester configureHarvester(Propagator propagator);

    /** {@inheritDoc} */
    @Override
    public native Pair<RealVector, RealMatrix> value(RealVector realVector);

    /** {@inheritDoc} */
    @Override
    public native Orbit configureOrbits(MatricesHarvester harvester, Propagator propagator);
}
