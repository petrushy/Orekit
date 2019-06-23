package org.orekit.python;

import org.hipparchus.RealFieldElement;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.attitudes.AttitudeProvider;
import org.orekit.forces.AbstractParametricAcceleration;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.Propagator;
import org.orekit.propagation.SpacecraftState;
import org.orekit.utils.ParameterDriver;

public class PythonAbstractParametricAcceleration extends AbstractParametricAcceleration {
    /**
     * Simple constructor.
     *
     * @param direction        acceleration direction in overridden spacecraft frame
     * @param isInertial       if true, direction is defined in the same inertial
     *                         frame used for propagation (i.e. {@link SpacecraftState#getFrame()}),
     *                         otherwise direction is defined in spacecraft frame (i.e. using the
     *                         propagation {@link
     *                         Propagator#setAttitudeProvider(AttitudeProvider)
     *                         attitude law})
     * @param attitudeOverride provider for attitude used to compute acceleration
     */
    public PythonAbstractParametricAcceleration(Vector3D direction, boolean isInertial, AttitudeProvider attitudeOverride) {
        super(direction, isInertial, attitudeOverride);
    }

    /**
     * Compute the signed amplitude of the acceleration.
     * Extension point for Python.
     * <p>
     * The acceleration is the direction multiplied by the signed amplitude. So if
     * signed amplitude is negative, the acceleratin is towards the opposite of the
     * direction specified at construction.
     * </p>
     *
     * @param state      current state information: date, kinematics, attitude
     * @param parameters values of the force model parameters
     * @return norm of the acceleration
     */
    @Override
    public native double signedAmplitude(SpacecraftState state, double[] parameters);

    /**
     * Compute the signed amplitude of the acceleration.
     * <p>
     * The acceleration is the direction multiplied by the signed amplitude. So if
     * signed amplitude is negative, the acceleratin is towards the opposite of the
     * direction specified at construction.
     * </p>
     *
     * @param state      current state information: date, kinematics, attitude
     * @param parameters values of the force model parameters
     * @return norm of the acceleration
     */
    @Override
    public <T extends RealFieldElement<T>> T signedAmplitude(FieldSpacecraftState<T> state, T[] parameters) {
        return this.signedAmplitude_F(state, parameters);
    }



    /**
     * Compute the signed amplitude of the acceleration.
     * Extension point for Python linked to the signedAmplitude method.
     * <p>
     * The acceleration is the direction multiplied by the signed amplitude. So if
     * signed amplitude is negative, the acceleratin is towards the opposite of the
     * direction specified at construction.
     * </p>
     *
     * @param state      current state information: date, kinematics, attitude
     * @param parameters values of the force model parameters
     * @return norm of the acceleration
     */

    public native <T extends RealFieldElement<T>> T signedAmplitude_F(FieldSpacecraftState<T> state, T[] parameters);

    /**
     * Check if force models depends on position only.
     * Extension point for Python
     *
     * @return true if force model depends on position only, false
     * if it depends on velocity, either directly or due to a dependency
     * on attitude
     * @since 9.0
     */
    @Override
    public native boolean dependsOnPositionOnly();

    /**
     * Get the drivers for force model parameters.
     * Extension Point for Python
     *
     * @return drivers for force model parameters
     * @since 8.0
     */
    @Override
    public native ParameterDriver[] getParametersDrivers();
}
