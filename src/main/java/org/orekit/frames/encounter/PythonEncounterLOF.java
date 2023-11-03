package org.orekit.frames.encounter;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.Field;
import org.hipparchus.geometry.euclidean.threed.FieldRotation;
import org.hipparchus.geometry.euclidean.threed.FieldVector3D;
import org.hipparchus.geometry.euclidean.threed.Rotation;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.python.JCCBase;
import org.orekit.utils.FieldPVCoordinates;
import org.orekit.utils.PVCoordinates;

public class PythonEncounterLOF implements EncounterLOF {

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
    public native String getName();

    // TODO Add field adaptors
    @Override
    public native <T extends CalculusFieldElement<T>> FieldRotation<T> rotationFromInertial(Field<T> field, FieldPVCoordinates<T> origin, FieldPVCoordinates<T> other);

    @Override
    public native Rotation rotationFromInertial(PVCoordinates origin, PVCoordinates other);

    @Override
    public native <T extends CalculusFieldElement<T>> FieldVector3D<T> getAxisNormalToCollisionPlane(Field<T> field);

    @Override
    public native <T extends CalculusFieldElement<T>> FieldPVCoordinates<T> getFieldOther(Field<T> field);

    @Override
    public native Vector3D getAxisNormalToCollisionPlane();

    @Override
    public native PVCoordinates getOther();
}
