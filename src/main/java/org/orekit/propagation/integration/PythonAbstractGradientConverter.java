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


    /** Get the number of free state parameters.
     * @return number of free state parameters
     */
    public native int getFreeStateParameters();

    /** Add zero derivatives.
     * @param original original scalar
     * @param freeParameters total number of free parameters in the gradient
     * @return extended scalar
     */
    public native Gradient extend(final Gradient original, final int freeParameters);

    /** Add zero derivatives.
     * @param original original vector
     * @param freeParameters total number of free parameters in the gradient
     * @return extended vector
     */
    public FieldVector3D<Gradient> extend(final FieldVector3D<Gradient> original, final int freeParameters) {
        return this.extend_FVi(original, freeParameters);
    }

    public native FieldVector3D<Gradient> extend_FVi(final FieldVector3D<Gradient> original, final int freeParameters);


    /** Add zero derivatives.
     * @param original original rotation
     * @param freeParameters total number of free parameters in the gradient
     * @return extended rotation
     */
    public FieldRotation<Gradient> extend(final FieldRotation<Gradient> original, final int freeParameters) {
        return this.extend_FRi(original, freeParameters);
    }

    /** Add zero derivatives.
     * @param original original rotation
     * @param freeParameters total number of free parameters in the gradient
     * @return extended rotation
     */
    public native FieldRotation<Gradient> extend_FRi(final FieldRotation<Gradient> original, final int freeParameters);



}
