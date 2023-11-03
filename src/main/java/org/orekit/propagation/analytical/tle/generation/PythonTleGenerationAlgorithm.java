package org.orekit.propagation.analytical.tle.generation;

import org.hipparchus.CalculusFieldElement;
import org.orekit.propagation.FieldSpacecraftState;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.analytical.tle.FieldTLE;
import org.orekit.propagation.analytical.tle.TLE;
 ;

public class PythonTleGenerationAlgorithm implements TleGenerationAlgorithm {

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
    public native TLE generate(SpacecraftState state, TLE templateTLE);

    @Override
    public <T extends CalculusFieldElement<T>> FieldTLE<T> generate(FieldSpacecraftState<T> state, FieldTLE<T> templateTLE) {
        return this.generate_FF(state, templateTLE);
    }

    public native <T extends CalculusFieldElement<T>> FieldTLE<T> generate_FF(FieldSpacecraftState<T> state, FieldTLE<T> templateTLE);
}
