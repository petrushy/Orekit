package org.orekit.attitudes;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.geometry.euclidean.threed.FieldRotation;
import org.hipparchus.geometry.euclidean.threed.Rotation;
import org.orekit.frames.Frame;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.SpacecraftState;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.FieldAbsoluteDate;
import org.orekit.utils.FieldPVCoordinatesProvider;
import org.orekit.utils.PVCoordinatesProvider;

/**
 * Python implementation of the AbstractSwitchingAttitudeProvider class.
 * This class is part of the JCC Python interface and exposes abstract methods natively.
 */
public class PythonAbstractSwitchingAttitudeProvider extends AbstractSwitchingAttitudeProvider {

    /** Part of JCC Python interface to object */
    private long pythonObject;

    /**
     * Part of JCC Python interface to object
     */
    public void pythonExtension(long pythonObject) {
        this.pythonObject = pythonObject;
    }

    /**
     * Part of JCC Python interface to object
     */
    public long pythonExtension() {
        return this.pythonObject;
    }

    /**
     * Part of JCC Python interface to object
     */
    public void finalize() throws Throwable {
        pythonDecRef();
    }

    /**
     * Part of JCC Python interface to object
     */
    public native void pythonDecRef();

    /** {@inheritDoc} */
    @Override
    public native Rotation getAttitudeRotation(PVCoordinatesProvider pvProv, AbsoluteDate date, Frame frame);

    /** {@inheritDoc} */
    @Override
    public native <T extends CalculusFieldElement<T>> FieldRotation<T> getAttitudeRotation(
            FieldPVCoordinatesProvider<T> pvProv, FieldAbsoluteDate<T> date, Frame frame);
}