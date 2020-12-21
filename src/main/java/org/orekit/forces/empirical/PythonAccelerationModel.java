package org.orekit.forces.empirical;

import org.hipparchus.RealFieldElement;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.SpacecraftState;
import org.orekit.utils.ParameterDriver;
 /* TODO PHY IMPLEMENT */

public class PythonAccelerationModel implements AccelerationModel {
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
    public double signedAmplitude(SpacecraftState state, double[] parameters) {
        return 0;
    }

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
        return null;
    }

    /**
     * Get the drivers for acceleration model parameters.
     *
     * @return drivers for acceleration model parameters
     */
    @Override
    public ParameterDriver[] getParametersDrivers() {
        return new ParameterDriver[0];
    }
}
