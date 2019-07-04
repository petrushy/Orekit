package org.orekit.estimation.leastsquares;

import org.hipparchus.linear.RealMatrix;
import org.hipparchus.linear.RealVector;
import org.hipparchus.util.Incrementor;
import org.hipparchus.util.Pair;
import org.orekit.estimation.leastsquares.BatchLSODModel;
import org.orekit.estimation.measurements.EstimatedMeasurement;
import org.orekit.propagation.integration.AbstractIntegratedPropagator;
import org.orekit.utils.ParameterDriversList;

public class PythonBatchLSODModel implements BatchLSODModel {

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

    @Override
    public native Pair<RealVector, RealMatrix> value(RealVector realVector);
}
