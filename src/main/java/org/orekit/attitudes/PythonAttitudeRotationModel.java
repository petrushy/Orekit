package org.orekit.attitudes;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.geometry.euclidean.threed.FieldRotation;
import org.hipparchus.geometry.euclidean.threed.Rotation;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.SpacecraftState;
import org.orekit.utils.ParameterDriver;

import java.util.List;

/**
 * Python implementation of the AttitudeRotationModel interface.
 * This class is part of the JCC Python interface and exposes all methods natively.
 */
public class PythonAttitudeRotationModel implements AttitudeRotationModel {

    /** Part of JCC Python interface to object */
    private long pythonObject;

    /**
     * Part of JCC Python interface to object
     */
    public void pythonExtension(long pythonObject) {
        this.pythonObject = pythonObject;
    }

    /**
     * Part of JCC Python interface to object
     */
    public long pythonExtension() {
        return this.pythonObject;
    }

    /**
     * Part of JCC Python interface to object
     */
    public void finalize() throws Throwable {
        pythonDecRef();
    }

    /**
     * Part of JCC Python interface to object
     */
    public native void pythonDecRef();

    /**
     * {@inheritDoc}
     */
    @Override
    public native Rotation getAttitudeRotation(SpacecraftState state, double[] parameters);

    /**
     * {@inheritDoc}
     */
    @Override
    public native <T extends CalculusFieldElement<T>> FieldRotation<T> getAttitudeRotation(FieldSpacecraftState<T> state, T[] parameters);

    /**
     * {@inheritDoc}
     */
    @Override
    public native List<ParameterDriver> getParametersDrivers();
}