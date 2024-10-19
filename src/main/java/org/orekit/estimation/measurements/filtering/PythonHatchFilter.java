package org.orekit.estimation.measurements.filtering;

public class PythonHatchFilter extends HatchFilter {

    /** Part of JCC Python interface to object */
    protected long pythonObject;

    /** Part of JCC Python interface to object */
    public void pythonExtension(long pythonObject) {
        this.pythonObject = pythonObject;
    }

    /** Part of JCC Python interface to object */
    public long pythonExtension() {
        return this.pythonObject;
    }

    /** Part of JCC Python interface to object */
    public void finalize() throws Throwable {
        pythonDecRef();
    }

    /** Part of JCC Python interface to object */
    public native void pythonDecRef();

    /**
     * Constructor for the Abstract Hatch Filter.
     * <p>
     * Initialize the variables and set the initial pseudo-range state.
     * </p>
     *
     * @param threshold threshold for loss of lock detection
     *                  (it represents the maximum difference between smoothed
     *                  and measured values for loss of lock detection)
     * @param N         window size of the Hatch Filter
     */
    PythonHatchFilter(double threshold, int N) {
        super(threshold, N);
    }
}
