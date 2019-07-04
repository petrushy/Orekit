package org.orekit.propagation.semianalytical.dsst.forces;

import org.hipparchus.Field;
import org.hipparchus.RealFieldElement;
import org.orekit.forces.ForceModel;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.events.EventDetector;
import org.orekit.propagation.events.FieldEventDetector;
import org.orekit.propagation.semianalytical.dsst.forces.AbstractGaussianContribution;
import org.orekit.propagation.semianalytical.dsst.utilities.AuxiliaryElements;
import org.orekit.propagation.semianalytical.dsst.utilities.FieldAuxiliaryElements;
import org.orekit.utils.ParameterDriver;

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

    /**
     * Get the drivers for force model parameters except the one for the central attraction coefficient.
     * <p>
     * The driver for central attraction coefficient is automatically
     * added at the last element of the {@link ParameterDriver} array
     * into {@link #getParametersDrivers()} method.
     * </p>
     *
     * @return drivers for force model parameters
     */
    @Override
    public native ParameterDriver[] getParametersDriversWithoutMu();

    /**
     * Compute the limits in L, the true longitude, for integration.
     *
     * @param state   current state information: date, kinematics, attitude
     * @param auxiliaryElements auxiliary elements related to the current orbit
     * @return the integration limits in L
     */
    @Override
    public native double[] getLLimits(SpacecraftState state, AuxiliaryElements auxiliaryElements);

    /**
     * Compute the limits in L, the true longitude, for integration.
     *
     * @param state   current state information: date, kinematics, attitude
     * @param auxiliaryElements auxiliary elements related to the current orbit
     * @return the integration limits in L
     */
    @Override
    public <T extends RealFieldElement<T>> T[] getLLimits(FieldSpacecraftState<T> state, FieldAuxiliaryElements<T> auxiliaryElements) {
        return this.getLLimits_FF(state, auxiliaryElements);
    }

    public native <T extends RealFieldElement<T>> T[] getLLimits_FF(FieldSpacecraftState<T> state, FieldAuxiliaryElements<T> auxiliaryElements);


    /**
     * Get the discrete events related to the model.
     * Extension point for Python.
     *
     * @return array of events detectors or null if the model is not
     * related to any discrete events
     */
    @Override
    public native EventDetector[] getEventsDetectors();

    /**
     * Get the discrete events related to the model.
     *
     * @param field field used by default
     * @return array of events detectors or null if the model is not
     * related to any discrete events
     */
    @Override
    public native <T extends RealFieldElement<T>> FieldEventDetector<T>[] getFieldEventsDetectors(Field<T> field);
}
