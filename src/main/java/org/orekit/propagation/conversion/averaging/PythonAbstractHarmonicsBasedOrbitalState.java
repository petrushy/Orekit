package org.orekit.propagation.conversion.averaging;

import org.orekit.forces.gravity.potential.UnnormalizedSphericalHarmonicsProvider;
import org.orekit.frames.Frame;
import org.orekit.orbits.Orbit;
import org.orekit.orbits.OrbitType;
import org.orekit.orbits.PositionAngleType;
import org.orekit.propagation.conversion.averaging.elements.AveragedOrbitalElements;
import org.orekit.time.AbsoluteDate;

public class PythonAbstractHarmonicsBasedOrbitalState extends AbstractHarmonicsBasedOrbitalState {
    /**
     * Protected constructor.
     *
     * @param date              epoch
     * @param frame             reference frame
     * @param harmonicsProvider spherical harmonics provider
     */
    public PythonAbstractHarmonicsBasedOrbitalState(AbsoluteDate date, Frame frame, UnnormalizedSphericalHarmonicsProvider harmonicsProvider) {
        super(date, frame, harmonicsProvider);
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
    public native OrbitType getOrbitType();

    @Override
    public native PositionAngleType getPositionAngleType();

    @Override
    public native Orbit toOsculatingOrbit();
}
