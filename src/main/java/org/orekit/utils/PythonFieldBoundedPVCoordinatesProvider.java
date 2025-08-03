package org.orekit.utils;

import org.hipparchus.CalculusFieldElement;
import org.orekit.frames.Frame;
import org.orekit.time.FieldAbsoluteDate;

public class PythonFieldBoundedPVCoordinatesProvider<T extends CalculusFieldElement<T>> implements FieldBoundedPVCoordinatesProvider<T> {

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


    @Override
    public native FieldAbsoluteDate<T> getMinDate();

    @Override
    public native FieldAbsoluteDate<T> getMaxDate();

    @Override
    public native TimeStampedFieldPVCoordinates<T> getPVCoordinates(FieldAbsoluteDate<T> date, Frame frame);
}
