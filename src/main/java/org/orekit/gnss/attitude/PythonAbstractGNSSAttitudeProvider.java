package org.orekit.gnss.attitude;

import org.hipparchus.CalculusFieldElement;
import org.orekit.frames.Frame;
import org.orekit.time.AbsoluteDate;
import org.orekit.utils.ExtendedPVCoordinatesProvider;
import org.orekit.utils.TimeStampedAngularCoordinates;
import org.orekit.utils.TimeStampedFieldAngularCoordinates;

public class PythonAbstractGNSSAttitudeProvider extends AbstractGNSSAttitudeProvider {
    /**
     * Simple constructor.
     *
     * @param validityStart start of validity for this provider
     * @param validityEnd   end of validity for this provider
     * @param sun           provider for Sun position
     * @param inertialFrame inertial frame where velocity are computed
     */
    public PythonAbstractGNSSAttitudeProvider(AbsoluteDate validityStart, AbsoluteDate validityEnd, ExtendedPVCoordinatesProvider sun, Frame inertialFrame) {
        super(validityStart, validityEnd, sun, inertialFrame);
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
    public native TimeStampedAngularCoordinates correctedYaw(GNSSAttitudeContext context);

    /** {@inheritDoc} */
    @Override
    public native <T extends CalculusFieldElement<T>> TimeStampedFieldAngularCoordinates<T> correctedYaw(GNSSFieldAttitudeContext<T> context);
}
