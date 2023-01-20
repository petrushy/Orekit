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

    /**
     * Check if this provider should yield so another provider has an opportunity to add missing parts.
     * <p>
     * Decision to yield is often based on an additional state being {@link SpacecraftState#hasAdditionalState(String)
     * already available} in the provided {@code state} (but it could theoretically also depend on
     * an additional state derivative being {@link SpacecraftState#hasAdditionalStateDerivative(String)
     * already available}, or any other criterion). If for example a provider needs the state transition
     * matrix, it could implement this method as:
     * </p>
     * <pre>{@code
     * public boolean yield(final SpacecraftState state) {
     *     return !state.getAdditionalStates().containsKey("STM");
     * }
     * }</pre>
     * <p>
     * The default implementation returns {@code false}, meaning that derivative data can be
     * {@link #combinedDerivatives(SpacecraftState) computed} immediately.
     * </p>
     *
     * @param state state to handle
     * @return true if this provider should yield so another provider has an opportunity to add missing parts
     * as the state is incrementally built up
     */
    @Override
    public native boolean yield_(SpacecraftState state);

    /**
     * Compute the derivatives related to the additional state parameters.
     *
     * @param s current state information: date, kinematics, attitude, and
     *          additional states this equations depend on (according to the
     *          {@link #yield(SpacecraftState) yield} method)
     * @return computed derivatives
     * @deprecated as of 11.2, replaced by {@link #combinedDerivatives(SpacecraftState)}
     */
    @Override
    public native double[] derivatives(SpacecraftState s);

    /**
     * Compute the derivatives related to the additional state (and optionally main state increments).
     * <p>
     * As of 11.2, there is a default implementation that calls the deprecated
     * {@link #derivatives(SpacecraftState)} method. This has been done for
     * backward compatibility only and will be removed in 12.0.
     * </p>
     *
     * @param s current state information: date, kinematics, attitude, and
     *          additional states this equations depend on (according to the
     *          {@link #yield(SpacecraftState) yield} method)
     * @return computed combined derivatives, which may include some incremental
     * coupling effect to add to main state derivatives
     * @since 11.2
     */
    @Override
    public native CombinedDerivatives combinedDerivatives(SpacecraftState s);
}
