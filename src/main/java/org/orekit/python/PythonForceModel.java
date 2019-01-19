package org.orekit.python;

import org.hipparchus.Field;
import org.hipparchus.RealFieldElement;
import org.hipparchus.geometry.euclidean.threed.FieldVector3D;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.forces.ForceModel;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.events.EventDetector;
import org.orekit.propagation.events.FieldEventDetector;
import org.orekit.propagation.numerical.FieldTimeDerivativesEquations;
import org.orekit.propagation.numerical.TimeDerivativesEquations;
import org.orekit.time.AbsoluteDate;
import org.orekit.utils.ParameterDriver;

import java.util.stream.Stream;

// TODO: CHECK HOW TO DEAL WITH METHODS OF SAME NAME!!

public class PythonForceModel implements ForceModel {
    /**
     * Initialize the force model at the start of propagation. This method will be called
     * before any calls to {@link #addContribution(SpacecraftState, TimeDerivativesEquations)},
     * {@link #addContribution(FieldSpacecraftState, FieldTimeDerivativesEquations)},
     * {@link #acceleration(SpacecraftState, double[])} or {@link #acceleration(FieldSpacecraftState, RealFieldElement[])}
     *
     * <p> The default implementation of this method does nothing.</p>
     *
     * @param initialState spacecraft state at the start of propagation.
     * @param target       date of propagation. Not equal to {@code initialState.getDate()}.
     */
    @Override
    public void init(SpacecraftState initialState, AbsoluteDate target) {

    }

    /**
     * Compute the contribution of the force model to the perturbing
     * acceleration.
     * <p>
     * The default implementation simply adds the {@link #acceleration(SpacecraftState, double[]) acceleration}
     * as a non-Keplerian acceleration.
     * </p>
     *
     * @param s     current state information: date, kinematics, attitude
     * @param adder object where the contribution should be added
     */
    @Override
    public void addContribution(SpacecraftState s, TimeDerivativesEquations adder) {

    }

    /**
     * Compute the contribution of the force model to the perturbing
     * acceleration.
     *
     * @param s     current state information: date, kinematics, attitude
     * @param adder object where the contribution should be added
     */
    @Override
    public <T extends RealFieldElement<T>> void addContribution(FieldSpacecraftState<T> s, FieldTimeDerivativesEquations<T> adder) {

    }

    /**
     * Get force model parameters.
     *
     * @return force model parameters
     * @since 9.0
     */
    @Override
    public double[] getParameters() {
        return new double[0];
    }

    /**
     * Get force model parameters.
     *
     * @param field field to which the elements belong
     * @return force model parameters
     * @since 9.0
     */
    @Override
    public <T extends RealFieldElement<T>> T[] getParameters(Field<T> field) {
        return null;
    }

    /**
     * Check if force models depends on position only.
     *
     * @return true if force model depends on position only, false
     * if it depends on velocity, either directly or due to a dependency
     * on attitude
     * @since 9.0
     */
    @Override
    public boolean dependsOnPositionOnly() {
        return false;
    }

    /**
     * Compute acceleration.
     *
     * @param s          current state information: date, kinematics, attitude
     * @param parameters values of the force model parameters
     * @return acceleration in same frame as state
     * @since 9.0
     */
    @Override
    public Vector3D acceleration(SpacecraftState s, double[] parameters) {
        return null;
    }

    /**
     * Compute acceleration.
     *
     * @param s          current state information: date, kinematics, attitude
     * @param parameters values of the force model parameters
     * @return acceleration in same frame as state
     * @since 9.0
     */
    @Override
    public <T extends RealFieldElement<T>> FieldVector3D<T> acceleration(FieldSpacecraftState<T> s, T[] parameters) {
        return null;
    }

    /**
     * Get the discrete events related to the model.
     *
     * @return stream of events detectors
     */
    @Override
    public Stream<EventDetector> getEventsDetectors() {
        return null;
    }

    /**
     * Get the discrete events related to the model.
     *
     * @param field field to which the state belongs
     * @return stream of events detectors
     */
    @Override
    public <T extends RealFieldElement<T>> Stream<FieldEventDetector<T>> getFieldEventsDetectors(Field<T> field) {
        return null;
    }

    /**
     * Get the drivers for force model parameters.
     *
     * @return drivers for force model parameters
     * @since 8.0
     */
    @Override
    public ParameterDriver[] getParametersDrivers() {
        return new ParameterDriver[0];
    }

    /**
     * Get parameter value from its name.
     *
     * @param name parameter name
     * @return parameter value
     * @since 8.0
     */
    @Override
    public ParameterDriver getParameterDriver(String name) {
        return null;
    }

    /**
     * Check if a parameter is supported.
     * <p>Supported parameters are those listed by {@link #getParametersDrivers()}.</p>
     *
     * @param name parameter name to check
     * @return true if the parameter is supported
     * @see #getParametersDrivers()
     */
    @Override
    public native boolean isSupported(String name);
}
