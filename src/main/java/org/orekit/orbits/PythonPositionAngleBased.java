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

    @Override
    public native boolean hasNonKeplerianRates();

    @Override
    public native Object withKeplerianRates();

    @Override
    public native Object withCachedPositionAngleType(PositionAngleType positionAngleType);
}
