package org.orekit.frames;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.Field;
import org.hipparchus.geometry.euclidean.threed.FieldRotation;
import org.hipparchus.geometry.euclidean.threed.Rotation;

import org.orekit.time.AbsoluteDate;
import org.orekit.time.FieldAbsoluteDate;
import org.orekit.utils.FieldPVCoordinates;
import org.orekit.utils.PVCoordinates;

public class PythonLOF implements LOF {

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
    public native <T extends CalculusFieldElement<T>> FieldRotation<T> rotationFromInertial(Field<T> field, FieldAbsoluteDate<T> date, FieldPVCoordinates<T> pv);

    @Override
    public native Rotation rotationFromInertial(AbsoluteDate date, PVCoordinates pv);

    @Override
    public native String getName();
}
