package org.orekit.forces.radiation;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.geometry.euclidean.threed.FieldVector3D;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.bodies.OneAxisEllipsoid;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.events.EventDetectionSettings;
import org.orekit.time.AbsoluteDate;
import org.orekit.utils.ExtendedPositionProvider;
import org.orekit.utils.ParameterDriver;

import java.util.List;

public class PythonAbstractRadiationForceModel extends AbstractRadiationForceModel {

    public PythonAbstractRadiationForceModel(final ExtendedPositionProvider sun, final OneAxisEllipsoid centralBody,
                                             final EventDetectionSettings eclipseDetectionSettings) {
        super(sun, centralBody, eclipseDetectionSettings);
    }

    /** {@inheritDoc} */
    @Override
    public native void init(SpacecraftState initialState, AbsoluteDate target);

    /** {@inheritDoc} */
    @Override
    public native Vector3D acceleration(SpacecraftState s, double[] parameters);

    /** {@inheritDoc} */
    @Override
    public native <T extends CalculusFieldElement<T>> FieldVector3D<T> acceleration(FieldSpacecraftState<T> s, T[] parameters);

    /** {@inheritDoc} */
    @Override
    public native List<ParameterDriver> getParametersDrivers();

}
