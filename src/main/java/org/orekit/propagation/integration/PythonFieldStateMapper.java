package org.orekit.propagation.integration;

import org.hipparchus.CalculusFieldElement;
import org.orekit.attitudes.AttitudeProvider;
import org.orekit.frames.Frame;
import org.orekit.orbits.OrbitType;
import org.orekit.orbits.PositionAngle;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.PropagationType;
import org.orekit.time.FieldAbsoluteDate;

public class PythonFieldStateMapper<T extends CalculusFieldElement<T>> extends FieldStateMapper<T> {
    /**
     * Simple constructor.
     * <p>
     * The position parameter type is meaningful only if {@link
     * #getOrbitType() propagation orbit type}
     * support it. As an example, it is not meaningful for propagation
     * in {@link  OrbitType#CARTESIAN Cartesian} parameters.
     * </p>
     *
     * @param referenceDate     reference date
     * @param mu                central attraction coefficient (m³/s²)
     * @param orbitType         orbit type to use for mapping
     * @param positionAngleType angle type to use for propagation
     * @param attitudeProvider  attitude provider
     * @param frame             inertial frame
     */
    public PythonFieldStateMapper(FieldAbsoluteDate<T> referenceDate, T mu, OrbitType orbitType, PositionAngle positionAngleType, AttitudeProvider attitudeProvider, Frame frame) {
        super(referenceDate, mu, orbitType, positionAngleType, attitudeProvider, frame);
    }

    static final long serialVersionUID = 1L;

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
     * Map the raw double components to a spacecraft state.
     *
     * @param date of the state components
     * @param y    state components
     * @param yDot state derivative components
     * @param type type of the elements used to build the state (mean or osculating).
     * @return spacecraft state
     */
    @Override
    public native FieldSpacecraftState<T> mapArrayToState(FieldAbsoluteDate<T> date, T[] y, T[] yDot, PropagationType type);

    /**
     * Map a spacecraft state to raw double components.
     *
     * @param state state to map
     * @param y     placeholder where to put the components
     * @param yDot  placeholder where to put the components derivatives
     */
    @Override
    public native void mapStateToArray(FieldSpacecraftState<T> state, T[] y, T[] yDot);
}
