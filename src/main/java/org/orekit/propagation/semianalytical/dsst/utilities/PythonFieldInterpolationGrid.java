package org.orekit.propagation.semianalytical.dsst.utilities;

import org.hipparchus.CalculusFieldElement;

public class PythonFieldInterpolationGrid<T extends CalculusFieldElement<T>> implements FieldInterpolationGrid<T> {

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
    public native T[] getGridPoints(T stepStart, T stepEnd);
}
