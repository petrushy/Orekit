package org.orekit.propagation.conversion.averaging;

import org.orekit.frames.Frame;
import org.orekit.orbits.Orbit;
import org.orekit.orbits.OrbitType;
import org.orekit.orbits.PositionAngleType;
import org.orekit.propagation.conversion.averaging.elements.AveragedOrbitalElements;
import org.orekit.time.AbsoluteDate;

public class PythonAbstractAveragedOrbitalState extends AbstractAveragedOrbitalState {
    /**
     * Protected constructor.
     *
     * @param date  epoch
     * @param frame reference frame
     */
    public PythonAbstractAveragedOrbitalState(AbsoluteDate date, Frame frame) {
        super(date, frame);
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


    @Override
    public native AveragedOrbitalElements getAveragedElements();

    @Override
    public native double getMu();

    @Override
    public native OrbitType getOrbitType();

    @Override
    public native PositionAngleType getPositionAngleType();

    @Override
    public native Orbit toOsculatingOrbit();
}
