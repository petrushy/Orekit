package org.orekit.python;

import org.hipparchus.Field;
import org.hipparchus.RealFieldElement;
import org.orekit.attitudes.AttitudeProvider;
import org.orekit.orbits.FieldOrbit;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.analytical.FieldAbstractAnalyticalPropagator;
import org.orekit.time.FieldAbsoluteDate;

public class PythonFieldAbstractAnalyticalPropagator<T extends RealFieldElement<T>> extends FieldAbstractAnalyticalPropagator<T> {
    /** Part of JCC Python interface to object */
    private long pythonObject;

    /**
     * Build a new instance.
     *
     * @param field            field used as default
     * @param attitudeProvider provider for attitude computation
     */
    protected PythonFieldAbstractAnalyticalPropagator(Field<T> field, AttitudeProvider attitudeProvider) {
        super(field, attitudeProvider);
    }

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
     * Get the mass.
     *
     * @param date target date for the orbit
     * @return mass mass
     */
    @Override
    public native T getMass(FieldAbsoluteDate<T> date);

    /**
     * Reset an intermediate state.
     *
     * @param state   new intermediate state to consider
     * @param forward if true, the intermediate state is valid for
     */
    @Override
    public native void resetIntermediateState(FieldSpacecraftState<T> state, boolean forward);

    /**
     * Extrapolate an orbit up to a specific target date.
     *
     * @param date target date for the orbit
     * @return extrapolated parameters
     */
    @Override
    public native FieldOrbit<T> propagateOrbit(FieldAbsoluteDate<T> date);
}
