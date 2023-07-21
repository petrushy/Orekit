package org.orekit.forces.radiation;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.geometry.euclidean.threed.FieldVector3D;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.numerical.FieldTimeDerivativesEquations;
import org.orekit.propagation.numerical.TimeDerivativesEquations;
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

    /** {@inheritDoc} */
    @Override
    public native void init(SpacecraftState initialState, AbsoluteDate target);

    /** {@inheritDoc} */
    @Override
    public native Vector3D acceleration(SpacecraftState s, double[] parameters);

    /** {@inheritDoc} */
    @Override
    public <T extends CalculusFieldElement<T>> FieldVector3D<T> acceleration(FieldSpacecraftState<T> s, T[] parameters) {
        return this.acceleration_FT(s,parameters);
    }

    /** {@inheritDoc} */
    public native <T extends CalculusFieldElement<T>> FieldVector3D<T> acceleration_FT(FieldSpacecraftState<T> s, T[] parameters);


    /** {@inheritDoc} */
    @Override
    public native List<ParameterDriver> getParametersDrivers();

}
