package org.orekit.python;

import org.hipparchus.ode.ODEIntegrator;
import org.orekit.attitudes.AttitudeProvider;
import org.orekit.frames.Frame;
import org.orekit.orbits.OrbitType;
import org.orekit.orbits.PositionAngle;
import org.orekit.propagation.integration.AbstractIntegratedPropagator;
import org.orekit.propagation.integration.StateMapper;
import org.orekit.time.AbsoluteDate;

public class PythonAbstractIntegratedPropagator extends AbstractIntegratedPropagator {
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
     * @param integrator numerical integrator to use for propagation.
     * @param meanOrbit  output only the mean orbit.
     */
    protected PythonAbstractIntegratedPropagator(ODEIntegrator integrator, boolean meanOrbit) {
        super(integrator, meanOrbit);
    }

    /**
     * Create a mapper between raw double components and spacecraft state.
     * /** Simple constructor.
     *  Extension point for Python.
     * <p>
     * The position parameter type is meaningful only if {@link
     * #getOrbitType() propagation orbit type}
     * support it. As an example, it is not meaningful for propagation
     * in {@link OrbitType#CARTESIAN Cartesian} parameters.
     * </p>
     *
     * @param referenceDate     reference date
     * @param mu                central attraction coefficient (m³/s²)
     * @param orbitType         orbit type to use for mapping
     * @param positionAngleType angle type to use for propagation
     * @param attitudeProvider  attitude provider
     * @param frame             inertial frame
     * @return new mapper
     */
    @Override
    public native StateMapper createMapper(AbsoluteDate referenceDate, double mu, OrbitType orbitType, PositionAngle positionAngleType, AttitudeProvider attitudeProvider, Frame frame);

    /**
     * Get the differential equations to integrate (for main state only).
     * Extension point for Python.
     *
     * @param integ numerical integrator to use for propagation.
     * @return differential equations for main state
     */
    @Override
    public native MainStateEquations getMainStateEquations(ODEIntegrator integ);
}
