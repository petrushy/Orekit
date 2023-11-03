package org.orekit.estimation.measurements.generation;

import org.orekit.estimation.measurements.ObservedMeasurement;
import org.orekit.python.JCCBase;
import org.orekit.time.AbsoluteDate;

public class PythonGeneratedMeasurementSubscriber implements GeneratedMeasurementSubscriber {
    /** Part of JCC Python interface to object */
    protected long pythonObject;
    public void pythonExtension(long pythonObject) {
        this.pythonObject = pythonObject;
    }
    public long pythonExtension() {
        return this.pythonObject;
    }
    public void finalize() throws Throwable { pythonDecRef(); }
    public native void pythonDecRef();

    @Override
    public native void init(AbsoluteDate start, AbsoluteDate end);

    @Override
    public native void handleGeneratedMeasurement(ObservedMeasurement<?> measurement);
}
