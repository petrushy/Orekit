package org.orekit.models.earth.weather;

import org.hipparchus.CalculusFieldElement;
import org.orekit.bodies.FieldGeodeticPoint;
import org.orekit.bodies.GeodeticPoint;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.FieldAbsoluteDate;

public class PythonPressureTemperatureHumidityProvider implements PressureTemperatureHumidityProvider {

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
    public native PressureTemperatureHumidity getWeatherParamerers(GeodeticPoint location, AbsoluteDate date);

    /** {@inheritDoc} */
    @Override
    public native <T extends CalculusFieldElement<T>> FieldPressureTemperatureHumidity<T> getWeatherParamerers(FieldGeodeticPoint<T> location, FieldAbsoluteDate<T> date);
}
