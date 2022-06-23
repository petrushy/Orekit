package org.orekit.estimation.measurements.modifiers;

import org.orekit.estimation.measurements.GroundStation;
import org.orekit.propagation.SpacecraftState;

public class PythonParametricModelEffect implements ParametricModelEffect {
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
     * Evaluate the parametric model effect.
     *
     * @param station station
     * @param state   spacecraft state
     * @return the measurement error due to parametric model
     */
    @Override
    public native double evaluate(GroundStation station, SpacecraftState state);
}
