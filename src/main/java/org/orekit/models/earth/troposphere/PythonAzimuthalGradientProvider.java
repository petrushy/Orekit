package org.orekit.models.earth.troposphere;

import org.hipparchus.CalculusFieldElement;
import org.orekit.bodies.FieldGeodeticPoint;
import org.orekit.bodies.GeodeticPoint;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.FieldAbsoluteDate;

public class PythonAzimuthalGradientProvider implements AzimuthalGradientProvider {

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
    public native AzimuthalGradientCoefficients getGradientCoefficients(GeodeticPoint location, AbsoluteDate date);

    /** {@inheritDoc} */
    @Override
    public native <T extends CalculusFieldElement<T>> FieldAzimuthalGradientCoefficients<T> getGradientCoefficients(FieldGeodeticPoint<T> location, FieldAbsoluteDate<T> date);
}
