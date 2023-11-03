package org.orekit.python;

import org.orekit.python.JCCBase;

import java.util.function.Function;

public class PythonFunction<T, R> implements Function<T, R> {

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
    public native R apply(T t);
}
