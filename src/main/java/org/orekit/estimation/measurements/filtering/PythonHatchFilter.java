package org.orekit.estimation.measurements.filtering;

public class PythonHatchFilter extends HatchFilter {
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
