package org.orekit.attitudes;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.geometry.euclidean.threed.FieldVector3D;
import org.orekit.bodies.OneAxisEllipsoid;
import org.orekit.frames.Frame;
import org.orekit.utils.ExtendedPositionProvider;
import org.orekit.utils.TimeStampedFieldPVCoordinates;

/**
 * Python implementation of the TargetProvider interface.
 * This class is part of the JCC Python interface and exposes all methods natively.
 */
public class PythonTargetProvider implements TargetProvider {

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

    /**
     * {@inheritDoc}
     */
    @Override
    public native <T extends CalculusFieldElement<T>> FieldVector3D<T> getTargetDirection(
            ExtendedPositionProvider sun, OneAxisEllipsoid earth, TimeStampedFieldPVCoordinates<T> pv, Frame frame);
}