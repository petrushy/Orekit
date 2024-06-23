package org.orekit.time;

import org.hipparchus.CalculusFieldElement;

public class PythonClockModel implements ClockModel {

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
    public native AbsoluteDate getValidityStart();

    /** {@inheritDoc} */
    @Override
    public native AbsoluteDate getValidityEnd();

    /** {@inheritDoc} */
    @Override
    public native ClockOffset getOffset(AbsoluteDate date);

    /** {@inheritDoc} */
    @Override
    public native <T extends CalculusFieldElement<T>> FieldClockOffset<T> getOffset(FieldAbsoluteDate<T> date);
}
