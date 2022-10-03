package org.orekit.utils;

import java.util.List;

public class PythonParametersDriversProvider implements ParametersDriversProvider {
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

    /**
     * Get the drivers for parameters.
     *
     * @return drivers for parameters
     */
    @Override
    public native List<ParameterDriver> getParametersDrivers();
}
