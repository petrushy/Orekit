package org.orekit.propagation.semianalytical.dsst.forces;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.Field;

import org.orekit.forces.ForceModel;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.events.EventDetector;
import org.orekit.propagation.events.FieldEventDetector;
import org.orekit.propagation.semianalytical.dsst.forces.AbstractGaussianContribution;
import org.orekit.propagation.semianalytical.dsst.utilities.AuxiliaryElements;
import org.orekit.propagation.semianalytical.dsst.utilities.FieldAuxiliaryElements;
import org.orekit.utils.ParameterDriver;

import java.util.List;

public class PythonAbstractGaussianContribution extends AbstractGaussianContribution {

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
     * @param coefficientsKeyPrefix prefix for coefficients keys
     * @param threshold             tolerance for the choice of the Gauss quadrature order
     * @param contribution          the {@link ForceModel} to be numerically averaged
     */
    public PythonAbstractGaussianContribution(String coefficientsKeyPrefix, double threshold, ForceModel contribution, double mu) {
        super(coefficientsKeyPrefix, threshold, contribution, mu);
    }

    /** {@inheritDoc} */
    @Override
    public native List<ParameterDriver> getParametersDriversWithoutMu();

    /** {@inheritDoc} */
    @Override
    public native double[] getLLimits(SpacecraftState state, AuxiliaryElements auxiliaryElements);

    /** {@inheritDoc} */
    @Override
    public <T extends CalculusFieldElement<T>> T[] getLLimits(FieldSpacecraftState<T> state, FieldAuxiliaryElements<T> auxiliaryElements) {
        return this.getLLimits_FF(state, auxiliaryElements);
    }

    public native <T extends CalculusFieldElement<T>> T[] getLLimits_FF(FieldSpacecraftState<T> state, FieldAuxiliaryElements<T> auxiliaryElements);

}
