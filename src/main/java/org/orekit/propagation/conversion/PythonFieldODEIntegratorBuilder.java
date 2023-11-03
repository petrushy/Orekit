package org.orekit.propagation.conversion;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.Field;
import org.hipparchus.ode.AbstractFieldIntegrator;
import org.orekit.orbits.FieldOrbit;
import org.orekit.orbits.Orbit;
import org.orekit.orbits.OrbitType;
 ;

public class PythonFieldODEIntegratorBuilder<T extends CalculusFieldElement<T>> implements FieldODEIntegratorBuilder<T> {

    /** Part of JCC Python interface to object */
    protected long pythonObject;
    public void pythonExtension(long pythonObject) {
        this.pythonObject = pythonObject;
    }
    public long pythonExtension() {
        return this.pythonObject;
    }
    public void finalize() throws Throwable { pythonDecRef(); }
    public native void pythonDecRef();

    @Override
    public native AbstractFieldIntegrator<T> buildIntegrator(Field<T> field, Orbit orbit, OrbitType orbitType);

    @Override
    public native AbstractFieldIntegrator<T> buildIntegrator(FieldOrbit<T> orbit, OrbitType orbitType);
}
