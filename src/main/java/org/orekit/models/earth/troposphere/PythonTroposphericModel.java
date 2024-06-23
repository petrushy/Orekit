package org.orekit.models.earth.troposphere;

import org.hipparchus.CalculusFieldElement;
import org.orekit.bodies.FieldGeodeticPoint;
import org.orekit.bodies.GeodeticPoint;
import org.orekit.models.earth.weather.FieldPressureTemperatureHumidity;
import org.orekit.models.earth.weather.PressureTemperatureHumidity;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.FieldAbsoluteDate;
import org.orekit.utils.FieldTrackingCoordinates;
import org.orekit.utils.ParameterDriver;
import org.orekit.utils.TrackingCoordinates;

import java.util.List;

public class PythonTroposphericModel implements TroposphericModel {

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
    public native TroposphericDelay pathDelay(TrackingCoordinates trackingCoordinates, GeodeticPoint point, PressureTemperatureHumidity weather, double[] parameters, AbsoluteDate date);

    /** {@inheritDoc} */
    @Override
    public native <T extends CalculusFieldElement<T>> FieldTroposphericDelay<T> pathDelay(FieldTrackingCoordinates<T> trackingCoordinates, FieldGeodeticPoint<T> point, FieldPressureTemperatureHumidity<T> weather, T[] parameters, FieldAbsoluteDate<T> date);

    /** {@inheritDoc} */
    @Override
    public native List<ParameterDriver> getParametersDrivers();
}
