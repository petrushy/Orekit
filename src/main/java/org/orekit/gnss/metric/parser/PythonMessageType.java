package org.orekit.gnss.metric.parser;

import org.orekit.gnss.metric.messages.ParsedMessage;
import org.orekit.time.TimeScales;

public class PythonMessageType implements MessageType {

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

    /**
     * {@inheritDoc}
     */
    @Override
    public native ParsedMessage parse(EncodedMessage encodedMessage, int messageNumber, TimeScales timeScales);
}
