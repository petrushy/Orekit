package org.orekit.forces.radiation;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.geometry.euclidean.threed.FieldVector3D;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.SpacecraftState;
import org.orekit.time.AbsoluteDate;
import org.orekit.utils.ExtendedPVCoordinatesProvider;
import org.orekit.utils.ParameterDriver;

import java.util.List;

public class PythonAbstractRadiationForceModel extends AbstractRadiationForceModel {
    /**
     * Constructor.
     *
     * @param sun              Sun model
     * @param equatorialRadius spherical shape model (for umbra/penumbra computation)
     */
    protected PythonAbstractRadiationForceModel(ExtendedPVCoordinatesProvider sun, double equatorialRadius) {
        super(sun, equatorialRadius);
    }

    /**
     * Initialize the force model at the start of propagation. This method will be called
     * before any calls to {@link #addContribution(SpacecraftState, TimeDerivativesEquations)},
     * {@link #addContribution(FieldSpacecraftState, FieldTimeDerivativesEquations)},
     * {@link #acceleration(SpacecraftState, double[])} or {@link #acceleration(FieldSpacecraftState, CalculusFieldElement[])}
     *
     * <p> The default implementation of this method does nothing.</p>
     *
     * @param initialState spacecraft state at the start of propagation.
     * @param target       date of propagation. Not equal to {@code initialState.getDate()}.
     */
    @Override
    public native void init(SpacecraftState initialState, AbsoluteDate target);

    /**
     * Compute acceleration.
     * Extension point for Python.
     * @param s          current state information: date, kinematics, attitude
     * @param parameters values of the force model parameters
     * @return acceleration in same frame as state
     * @since 9.0
     */
    @Override
    public native Vector3D acceleration(SpacecraftState s, double[] parameters);

    /**
     * Compute acceleration. Automatically directs to the Python extension point acceleration_FT
     *
     * @param s          current state information: date, kinematics, attitude
     * @param parameters values of the force model parameters
     * @return acceleration in same frame as state
     * @since 9.0
     */
    @Override
    public <T extends CalculusFieldElement<T>> FieldVector3D<T> acceleration(FieldSpacecraftState<T> s, T[] parameters) {
        return this.acceleration_FT(s,parameters);
    }

    /**
     * Compute acceleration, Alternative python interface point for the acceleration method.
     * Extension point for Python.
     *
     * @param s          current state information: date, kinematics, attitude
     * @param parameters values of the force model parameters
     * @return acceleration in same frame as state
     * @since 9.0
     */
    public native <T extends CalculusFieldElement<T>> FieldVector3D<T> acceleration_FT(FieldSpacecraftState<T> s, T[] parameters);


    /**
     * Get the drivers for force model parameters.
     * Extension point for Python.
     *
     * @return drivers for force model parameters
     * @since 8.0
     */
    @Override
    public native List<ParameterDriver> getParametersDrivers();

}
