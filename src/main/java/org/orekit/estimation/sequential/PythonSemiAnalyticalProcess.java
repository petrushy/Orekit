package org.orekit.estimation.sequential;

import org.hipparchus.filtering.kalman.ProcessEstimate;
import org.orekit.estimation.measurements.ObservedMeasurement;
import org.orekit.propagation.SpacecraftState;

public class PythonSemiAnalyticalProcess implements SemiAnalyticalProcess {
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
    public native KalmanObserver getObserver();

    @Override
    public native void initializeShortPeriodicTerms(SpacecraftState meanState);

    @Override
    public native void updateShortPeriods(SpacecraftState state);

    @Override
    public native void updateNominalSpacecraftState(SpacecraftState nominal);

    @Override
    public native void finalizeEstimation(ObservedMeasurement<?> observedMeasurement, ProcessEstimate estimate);

    @Override
    public native void finalizeOperationsObservationGrid();
}
