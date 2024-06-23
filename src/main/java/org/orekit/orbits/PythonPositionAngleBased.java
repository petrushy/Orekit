package org.orekit.orbits;

public class PythonPositionAngleBased implements PositionAngleBased {

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
    public native PositionAngleType getCachedPositionAngleType();

    /** {@inheritDoc} */
    @Override
    public native boolean hasRates();

    /** {@inheritDoc} */
    @Override
    public native PositionAngleBased removeRates();
}
