package org.orekit.data;

import java.io.IOException;
import java.io.InputStream;

public class PythonStreamOpener implements DataSource.StreamOpener {
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
     * Open the stream once.
     * <p>
     * Beware that this interface is only intended for <em>lazy</em> opening a
     * stream, i.e. to delay this opening (or not open the stream at all).
     * It is <em>not</em> intended to open the stream several times. Some
     * implementations may fail if an attempt to open a stream several
     * times is made. This is particularly true for network-based streams.
     * </p>
     *
     * @return opened stream
     * @throws IOException if stream cannot be opened
     */
    @Override
    public native InputStream openOnce() throws IOException;
}
