package org.orekit.forces.radiation;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.Field;
import org.hipparchus.geometry.euclidean.threed.FieldVector3D;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.propagation.events.EventDetector;
import org.orekit.propagation.events.FieldEventDetector;
import org.orekit.utils.ExtendedPositionProvider;

import java.util.List;

public class PythonAbstractLightFluxModel extends AbstractLightFluxModel {
    /**
     * Constructor.
     *
     * @param occultedBody position provider for light source
     */
    public PythonAbstractLightFluxModel(ExtendedPositionProvider occultedBody) {
        super(occultedBody);
    }
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



    @Override
    public native Vector3D getUnoccultedFluxVector(Vector3D relativePosition);

    @Override
    public native <T extends CalculusFieldElement<T>> FieldVector3D<T> getUnoccultedFluxVector(FieldVector3D<T> relativePosition);

    @Override
    public native double getLightingRatio(Vector3D position, Vector3D occultedBodyPosition);

    @Override
    public native <T extends CalculusFieldElement<T>> T getLightingRatio(FieldVector3D<T> position, FieldVector3D<T> occultedBodyPosition);

    @Override
    public native List<EventDetector> getEclipseConditionsDetector();

    @Override
    public native <T extends CalculusFieldElement<T>> List<FieldEventDetector<T>> getFieldEclipseConditionsDetector(Field<T> field);
}
