package org.orekit.propagation.integration;

import org.orekit.propagation.SpacecraftState;
import org.orekit.time.AbsoluteDate;

public class PythonAdditionalDerivativesProvider implements AdditionalDerivativesProvider {
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
     * Get the name of the additional derivatives (which will become state once integrated).
     *
     * @return name of the additional state (names containing "orekit"
     * with any case are reserved for the library internal use)
     */
    @Override
    public native String getName();

    /**
     * Get the dimension of the generated derivative.
     *
     * @return dimension of the generated
     */
    @Override
    public native int getDimension();

    /**
     * Initialize the generator at the start of propagation.
     *
     * @param initialState initial state information at the start of propagation
     * @param target       date of propagation
     */
    @Override
    public native void init(SpacecraftState initialState, AbsoluteDate target);

    @Override
    public native boolean yields(SpacecraftState state);

    @Override
    public native CombinedDerivatives combinedDerivatives(SpacecraftState s);
}
