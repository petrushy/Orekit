package org.orekit.models.earth.troposphere;

import org.hipparchus.CalculusFieldElement;
import org.orekit.bodies.FieldGeodeticPoint;
import org.orekit.bodies.GeodeticPoint;
import org.orekit.models.earth.weather.FieldPressureTemperatureHumidity;
import org.orekit.models.earth.weather.PressureTemperatureHumidity;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.FieldAbsoluteDate;
import org.orekit.time.TimeScale;
import org.orekit.utils.FieldTrackingCoordinates;
import org.orekit.utils.TrackingCoordinates;

public class PythonAbstractVienna extends AbstractVienna {
    /**
     * Build a new instance.
     *
     * @param aProvider           provider for a<sub>h</sub> and a<sub>w</sub> coefficients
     * @param gProvider           provider for {@link AzimuthalGradientCoefficients} and {@link FieldAzimuthalGradientCoefficients}
     * @param zenithDelayProvider provider for zenith delays
     * @param utc                 UTC time scale
     */
    public PythonAbstractVienna(ViennaAProvider aProvider, AzimuthalGradientProvider gProvider, TroposphericModel zenithDelayProvider, TimeScale utc) {
        super(aProvider, gProvider, zenithDelayProvider, utc);
    }

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
    public native double[] mappingFactors(TrackingCoordinates trackingCoordinates, GeodeticPoint point, PressureTemperatureHumidity weather, AbsoluteDate date);

    /** {@inheritDoc} */
    @Override
    public native <T extends CalculusFieldElement<T>> T[] mappingFactors(FieldTrackingCoordinates<T> trackingCoordinates, FieldGeodeticPoint<T> point, FieldPressureTemperatureHumidity<T> weather, FieldAbsoluteDate<T> date);
}
