package org.orekit.propagation;

import org.orekit.propagation.events.EventDetector;
import org.orekit.time.AbsoluteDate;

import java.util.Collection;

public class PythonAbstractPropagator extends AbstractPropagator {

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



    @Override
    public native EphemerisGenerator getEphemerisGenerator();


    @Override
    public native <T extends EventDetector> void addEventDetector(T detector);


    @Override
    public native void clearEventsDetectors();


    @Override
    public native SpacecraftState propagate(AbsoluteDate start, AbsoluteDate target);

    
    @Override
    public native Collection<EventDetector> getEventDetectors();

}
