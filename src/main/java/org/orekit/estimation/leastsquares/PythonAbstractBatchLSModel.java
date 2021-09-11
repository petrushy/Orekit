package org.orekit.estimation.leastsquares;

import org.hipparchus.linear.RealMatrix;
import org.hipparchus.linear.RealVector;
import org.hipparchus.util.Incrementor;
import org.hipparchus.util.Pair;
import org.orekit.estimation.measurements.EstimatedMeasurement;
import org.orekit.estimation.measurements.ObservedMeasurement;
import org.orekit.orbits.Orbit;
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

    /**
     * Get the selected propagation drivers for a propagatorBuilder.
     *
     * @param iBuilder index of the builder in the builders' array
     * @return the list of selected propagation drivers for propagatorBuilder of index iBuilder
     */
    @Override
    public native ParameterDriversList getSelectedPropagationDriversForBuilder(int iBuilder);

    /**
     * Create the propagators and parameters corresponding to an evaluation point.
     *
     * @param point evaluation point
     * @return an array of new propagators
     */
    @Override
    public native AbstractIntegratedPropagator[] createPropagators(RealVector point);

    /**
     * Fetch a measurement that was evaluated during propagation.
     *
     * @param index      index of the measurement first component
     * @param evaluation measurement evaluation
     */
    @Override
    public native void fetchEvaluatedMeasurement(int index, EstimatedMeasurement<?> evaluation);

    /**
     * Set the counter for evaluations.
     *
     * @param evaluationsCounter counter for evaluations
     */
    @Override
    public native void setEvaluationsCounter(Incrementor evaluationsCounter);

    /**
     * Set the counter for iterations.
     *
     * @param iterationsCounter counter for iterations
     */
    @Override
    public native void setIterationsCounter(Incrementor iterationsCounter);

    /**
     * Get the iterations count.
     *
     * @return iterations count
     */
    @Override
    public native int getIterationsCount();

    /**
     * Get the evaluations count.
     *
     * @return evaluations count
     */
    @Override
    public native int getEvaluationsCount();

    /**
     * Return the forward propagation flag.
     *
     * @return the forward propagation flag
     */
    @Override
    public native boolean isForwardPropagation();

    /**
     * Configure the propagator to compute derivatives.
     *
     * @param propagators {@link Propagator} to configure
     * @return mapper for this propagator
     */
    @Override
    public native AbstractJacobiansMapper configureDerivatives(Propagator propagators);

    /**
     * Configure the current estimated orbits.
     * <p>
     * For DSST orbit determination, short period derivatives are also calculated.
     * </p>
     *
     * @param mapper     Jacobian mapper
     * @param propagator the orbit propagator
     * @return the current estimated orbits
     */
    @Override
    public native Orbit configureOrbits(AbstractJacobiansMapper mapper, Propagator propagator);

    @Override
    public native Pair<RealVector, RealMatrix> value(RealVector realVector);
}
