package org.orekit.attitudes;

import org.orekit.frames.Frame;

// TODO What to extend?

public class PythonGroundPointingAttitudeModifier extends GroundPointingAttitudeModifier {
    /**
     * Constructor.
     *
     * @param inertialFrame     frame in which orbital velocities are computed
     * @param bodyFrame         the frame that rotates with the body
     * @param groundPointingLaw underlying ground pointing attitude law
     */
    public PythonGroundPointingAttitudeModifier(Frame inertialFrame, Frame bodyFrame, GroundPointing groundPointingLaw) {
        super(inertialFrame, bodyFrame, groundPointingLaw);
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

    // TODO What to expose here?

}
