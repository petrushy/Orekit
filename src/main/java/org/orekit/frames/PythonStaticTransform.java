// Expanded in 2026 to expose every default method declared on
// StaticTransform as native, so Python subclasses can override
// transformPosition / transformVector / transformLine / getStaticInverse
// rather than dispatching through the interface defaults that only call
// the abstract translation/rotation primitives.

package org.orekit.frames;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.geometry.euclidean.threed.FieldVector3D;
import org.hipparchus.geometry.euclidean.threed.Line;
import org.hipparchus.geometry.euclidean.threed.Rotation;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.time.AbsoluteDate;

public class PythonStaticTransform implements StaticTransform {
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
     * Get the underlying elementary translation.
     * <p>A transform can be uniquely represented as an elementary
     * translation followed by an elementary rotation. This method returns this
     * unique elementary translation.</p>
     *
     * @return underlying elementary translation
     */
    @Override
    public native Vector3D getTranslation();

    /**
     * Get the underlying elementary rotation.
     * <p>A transform can be uniquely represented as an elementary
     * translation followed by an elementary rotation. This method returns this
     * unique elementary rotation.</p>
     *
     * @return underlying elementary rotation
     */
    @Override
    public native Rotation getRotation();

    /**
     * Get the inverse transform of the instance.
     *
     * @return inverse transform of the instance
     */
    @Override
    public native StaticTransform getInverse();

    /**
     * Get the date.
     *
     * @return date attached to the object
     */
    @Override
    public native AbsoluteDate getDate();

    /** {@inheritDoc} */
    @Override
    public native Vector3D transformPosition(Vector3D position);

    /** {@inheritDoc} */
    @Override
    public native <T extends CalculusFieldElement<T>> FieldVector3D<T> transformPosition(FieldVector3D<T> position);

    /** {@inheritDoc} */
    @Override
    public native Vector3D transformVector(Vector3D vector);

    /** {@inheritDoc} */
    @Override
    public native <T extends CalculusFieldElement<T>> FieldVector3D<T> transformVector(FieldVector3D<T> vector);

    /** {@inheritDoc} */
    @Override
    public native Line transformLine(Line line);

    /** {@inheritDoc} */
    @Override
    public native StaticTransform getStaticInverse();
}
