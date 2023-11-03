package org.orekit.propagation.analytical.tle;

import org.hipparchus.CalculusFieldElement;
import org.orekit.attitudes.AttitudeProvider;
import org.orekit.frames.Frame;

public class PythonFieldSDP4<T extends CalculusFieldElement<T>> extends FieldSDP4<T> {

    /** Part of JCC Python interface to object */
    private long pythonObject;

    /**
     * Constructor for a unique initial TLE.
     *
     * @param initialTLE       the TLE to propagate.
     * @param attitudeProvider provider for attitude computation
     * @param mass             spacecraft mass (kg)
     * @param teme             the TEME frame to use for propagation.
     * @param parameters       SGP4 and SDP4 model parameters
     */
    public PythonFieldSDP4(FieldTLE<T> initialTLE, AttitudeProvider attitudeProvider, T mass, Frame teme, T[] parameters) {
        super(initialTLE, attitudeProvider, mass, teme, parameters);
    }

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


    /* TODO: Is this a reasonable exposure of the abstract class?

     */
    /** Computes luni - solar terms from initial coordinates and epoch.
     */
    public native void luniSolarTermsComputation();

    /** Computes secular terms from current coordinates and epoch.
     * @param t offset from initial epoch (min)
     */
    public native void deepSecularEffects(T t);

    /** Computes periodic terms from current coordinates and epoch.
     * @param t offset from initial epoch (min)
     */
    public native void deepPeriodicEffects(T t);
}
