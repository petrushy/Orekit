
// this file was created by SSC 2025 and is largely a derived work from the
// original java class

package org.orekit.utils;

public class PythonFormatter implements Formatter {

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
    public native String toString(double value);

    /** {@inheritDoc} */
    @Override
    public native String toString(int year, int month, int day, int hour, int minute, double seconds);
}
