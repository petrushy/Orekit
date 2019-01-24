package org.orekit.python;

import org.hipparchus.Field;
import org.hipparchus.RealFieldElement;
import org.hipparchus.geometry.euclidean.threed.FieldVector3D;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.forces.AbstractForceModel;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.events.EventDetector;
import org.orekit.propagation.events.FieldEventDetector;
import org.orekit.utils.ParameterDriver;

import java.util.stream.Stream;

public class PythonAbstractForceModel extends AbstractForceModel {

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
     * Check if force models depends on position only.
     *
     * @return true if force model depends on position only, false
     * if it depends on velocity, either directly or due to a dependency
     * on attitude
     * @since 9.0
     */
    @Override
    public native boolean dependsOnPositionOnly();

    /**
     * Compute acceleration.
     *
     * @param s          current state information: date, kinematics, attitude
     * @param parameters values of the force model parameters
     * @return acceleration in same frame as state
     * @since 9.0
     */
    @Override
    public native Vector3D acceleration(SpacecraftState s, double[] parameters);

    /**
     * Compute acceleration. Automatically directs to the Python extension point
     *
     * @param s          current state information: date, kinematics, attitude
     * @param parameters values of the force model parameters
     * @return acceleration in same frame as state
     * @since 9.0
     */
    @Override

    public <T extends RealFieldElement<T>> FieldVector3D<T> acceleration(FieldSpacecraftState<T> s, T[] parameters) {
        return this.Fieldacceleration(s,parameters);
    }
    /**
     * Compute acceleration, Python interface point
     *
     * @param s          current state information: date, kinematics, attitude
     * @param parameters values of the force model parameters
     * @return acceleration in same frame as state
     * @since 9.0
     */
    public native <T extends RealFieldElement<T>> FieldVector3D<T> Fieldacceleration(FieldSpacecraftState<T> s, T[] parameters);

    /**
     * Get the discrete events related to the model.
     *
     * @return stream of events detectors
     */
    @Override
    public native Stream<EventDetector> getEventsDetectors();

    /**
     * Get the discrete events related to the model.
     *
     * @param field field to which the state belongs
     * @return stream of events detectors
     */
    @Override
    public native <T extends RealFieldElement<T>> Stream<FieldEventDetector<T>> getFieldEventsDetectors(Field<T> field);

    /**
     * Get the drivers for force model parameters.
     *
     * @return drivers for force model parameters
     * @since 8.0
     */
    @Override
    public native ParameterDriver[] getParametersDrivers();

    }