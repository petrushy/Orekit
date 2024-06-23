package org.orekit.data;

import java.io.IOException;
import java.io.Reader;

public class PythonLineOrientedFilteringReader extends LineOrientedFilteringReader {
    /**
     * Simple constructor.
     *
     * @param name  file name
     * @param input underlying raw stream
     * @throws IOException if first lines cannot be read
     */
    public PythonLineOrientedFilteringReader(String name, Reader input) throws IOException {
        super(name, input);
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

    /** {@inheritDoc} */
    @Override
    public native CharSequence filterLine(int lineNumber, String originalLine) throws IOException;
}
