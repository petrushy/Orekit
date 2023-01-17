package org.orekit.propagation.analytical;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.Field;
import org.orekit.attitudes.AttitudeProvider;
import org.orekit.orbits.FieldOrbit;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.time.FieldAbsoluteDate;
import org.orekit.utils.ParameterDriver;

import java.util.List;

public class PythonFieldAbstractAnalyticalPropagator<T extends CalculusFieldElement<T>> extends FieldAbstractAnalyticalPropagator<T> {
    /** Part of JCC Python interface to object */
    private long pythonObject;

    /**
     * Build a new instance.
     *
     * @param field            field used as default
     * @param attitudeProvider provider for attitude computation
     */
    public PythonFieldAbstractAnalyticalPropagator(Field<T> field, AttitudeProvider attitudeProvider) {
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
     * @param date       target date for the orbit
     * @param parameters model parameters
     * @return extrapolated parameters
     */
    @Override
    public native FieldOrbit<T> propagateOrbit(FieldAbsoluteDate<T> date, T[] parameters);

    /**
     * Get the parameters driver for propagation model.
     *
     * @return drivers for propagation model
     */
    @Override
    public native List<ParameterDriver> getParametersDrivers();


}
