package org.orekit.propagation.integration;

import org.hipparchus.analysis.differentiation.Gradient;
import org.hipparchus.geometry.euclidean.threed.FieldRotation;
import org.hipparchus.geometry.euclidean.threed.FieldVector3D;

public class PythonAbstractGradientConverter extends AbstractGradientConverter {
    /**
     * Simple constructor.
     *
     * @param freeStateParameters number of free parameters
     */
    public PythonAbstractGradientConverter(int freeStateParameters) {
        super(freeStateParameters);
    }


    /** {@inheritDoc} */
    public native int getFreeStateParameters();

    /** {@inheritDoc} */
    public native Gradient extend(final Gradient original, final int freeParameters);

    /** {@inheritDoc} */
    public native FieldVector3D<Gradient> extend(final FieldVector3D<Gradient> original, final int freeParameters);

    /** {@inheritDoc} */
    public native FieldRotation<Gradient> extend(final FieldRotation<Gradient> original, final int freeParameters);

}
