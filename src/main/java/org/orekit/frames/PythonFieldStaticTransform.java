package org.orekit.frames;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.geometry.euclidean.threed.FieldRotation;
import org.hipparchus.geometry.euclidean.threed.FieldVector3D;
 ;
import org.orekit.time.AbsoluteDate;

public class PythonFieldStaticTransform<T extends CalculusFieldElement<T>> implements FieldStaticTransform<T> {

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
    public native FieldVector3D<T> getTranslation();

    @Override
    public native FieldRotation<T> getRotation();

    @Override
    public native FieldStaticTransform<T> getInverse();

    @Override
    public native AbsoluteDate getDate();
}
