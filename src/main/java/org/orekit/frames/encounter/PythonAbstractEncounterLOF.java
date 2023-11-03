package org.orekit.frames.encounter;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.Field;
import org.hipparchus.geometry.euclidean.threed.FieldRotation;
import org.hipparchus.geometry.euclidean.threed.FieldVector3D;
import org.hipparchus.geometry.euclidean.threed.Rotation;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.utils.FieldPVCoordinates;
import org.orekit.utils.PVCoordinates;

public class PythonAbstractEncounterLOF extends AbstractEncounterLOF {
    public PythonAbstractEncounterLOF(PVCoordinates other) {
        super(other);
    }
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
    public native String getName();

    //TODO ADD FIELD STUFF
    /** {@inheritDoc} */
    @Override
    public native <T extends CalculusFieldElement<T>> FieldRotation<T> rotationFromInertial(Field<T> field, FieldPVCoordinates<T> origin, FieldPVCoordinates<T> other);

    /** {@inheritDoc} */
    @Override
    public native Rotation rotationFromInertial(PVCoordinates origin, PVCoordinates other);

    /** {@inheritDoc} */
    @Override
    public native <T extends CalculusFieldElement<T>> FieldVector3D<T> getAxisNormalToCollisionPlane(Field<T> field);

    /** {@inheritDoc} */
    @Override
    public native Vector3D getAxisNormalToCollisionPlane();
}
