package org.orekit.propagation.conversion.averaging.converters;

import org.orekit.orbits.Orbit;
import org.orekit.propagation.conversion.averaging.AveragedOrbitalState;

public class PythonFixedPointOsculatingToAveragedConverter<T extends AveragedOrbitalState> extends FixedPointOsculatingToAveragedConverter<T> {
    /**
     * Protected constructor.
     *
     * @param epsilon       tolerance for convergence
     * @param maxIterations maximum number of iterations
     */
    public PythonFixedPointOsculatingToAveragedConverter(double epsilon, int maxIterations) {
        super(epsilon, maxIterations);
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
    public native T convertToAveraged(Orbit osculatingOrbit);
}
