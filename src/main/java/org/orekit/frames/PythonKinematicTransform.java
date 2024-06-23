package org.orekit.frames;

import org.hipparchus.geometry.euclidean.threed.Rotation;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.time.AbsoluteDate;

public class PythonKinematicTransform implements KinematicTransform {

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
    public native Vector3D getVelocity();

    /** {@inheritDoc} */
    @Override
    public native Vector3D getRotationRate();

    /** {@inheritDoc} */
    @Override
    public native Vector3D getTranslation();

    /** {@inheritDoc} */
    @Override
    public native Rotation getRotation();

    /** {@inheritDoc} */
    @Override
    public native KinematicTransform getInverse();

    /** {@inheritDoc} */
    @Override
    public native AbsoluteDate getDate();
}
