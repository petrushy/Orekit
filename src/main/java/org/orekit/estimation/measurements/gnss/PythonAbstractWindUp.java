package org.orekit.estimation.measurements.gnss;

import org.hipparchus.geometry.euclidean.threed.Rotation;
import org.orekit.estimation.measurements.EstimatedMeasurementBase;
import org.orekit.estimation.measurements.ObservedMeasurement;

public class PythonAbstractWindUp<T extends ObservedMeasurement<T>> extends AbstractWindUp<T> {
    /**
     * Simple constructor.
     *
     * @param emitter  emitter dipole
     * @param receiver receiver dipole
     */
    public PythonAbstractWindUp(Dipole emitter, Dipole receiver) {
        super(emitter, receiver);
    }

    /** Part of JCC Python interface to object */
    protected long pythonObject;

    /** Part of JCC Python interface to object */
    public void pythonExtension(long pythonObject) {
        this.pythonObject = pythonObject;
    }

    /** Part of JCC Python interface to object */
    public long pythonExtension() {
        return this.pythonObject;
    }

    /** Part of JCC Python interface to object */
    public void finalize() throws Throwable {
        pythonDecRef();
    }

    /** Part of JCC Python interface to object */
    public native void pythonDecRef();
    @Override
    public native Rotation emitterToInert(EstimatedMeasurementBase<T> estimated);

    @Override
    public native Rotation receiverToInert(EstimatedMeasurementBase<T> estimated);
}
