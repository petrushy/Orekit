package org.orekit.frames;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.geometry.euclidean.threed.FieldRotation;
import org.hipparchus.geometry.euclidean.threed.FieldVector3D;
import org.orekit.time.AbsoluteDate;

public class PythonFieldKinematicTransform<T extends CalculusFieldElement<T>> implements FieldKinematicTransform<T> {

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

    /** {@inheritDoc} */
    @Override
    public native FieldVector3D<T> getVelocity();

    /** {@inheritDoc} */
    @Override
    public native FieldVector3D<T> getRotationRate();

    /** {@inheritDoc} */
    @Override
    public native FieldVector3D<T> getTranslation();

    /** {@inheritDoc} */
    @Override
    public native FieldRotation<T> getRotation();

    /** {@inheritDoc} */
    @Override
    public native FieldKinematicTransform<T> getInverse();

    /** {@inheritDoc} */
    @Override
    public native AbsoluteDate getDate();
}
