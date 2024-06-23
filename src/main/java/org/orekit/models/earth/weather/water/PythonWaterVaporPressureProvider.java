package org.orekit.models.earth.weather.water;

import org.hipparchus.CalculusFieldElement;

public class PythonWaterVaporPressureProvider implements WaterVaporPressureProvider {

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
    public native double waterVaporPressure(double p, double t, double rh);

    /** {@inheritDoc} */
    @Override
    public native <T extends CalculusFieldElement<T>> T waterVaporPressure(T p, T t, T rh);
}
