package org.orekit.python;

import org.orekit.attitudes.AttitudeProvider;
import org.orekit.orbits.Orbit;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.analytical.AbstractAnalyticalPropagator;
import org.orekit.time.AbsoluteDate;

public class PythonAbstractAnalyticalPropagator extends AbstractAnalyticalPropagator {
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
     * Build a new instance.
     *
     * @param attitudeProvider provider for attitude computation
     */
    public PythonAbstractAnalyticalPropagator(AttitudeProvider attitudeProvider) {
        super(attitudeProvider);
    }

    /**
     * Get the mass.
     *
     * @param date target date for the orbit
     * @return mass mass
     */
    @Override
    public native double getMass(AbsoluteDate date);

    /**
     * Reset an intermediate state.
     *
     * @param state   new intermediate state to consider
     * @param forward if true, the intermediate state is valid for
     */
    @Override
    public native void resetIntermediateState(SpacecraftState state, boolean forward);

    /**
     * Extrapolate an orbit up to a specific target date.
     *
     * @param date target date for the orbit
     * @return extrapolated parameters
     */
    @Override
    public native Orbit propagateOrbit(AbsoluteDate date);
}
