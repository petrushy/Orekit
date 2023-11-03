package org.orekit.files.ccsds.utils.parsing;

import org.orekit.files.ccsds.utils.lexical.ParseToken;
import org.orekit.python.JCCBase;

public class PythonProcessingState implements ProcessingState {

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
    public native boolean processToken(ParseToken token);
}
