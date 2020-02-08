package org.orekit.bodies;

import org.orekit.frames.FramesFactory;

public class PythonCelestialBodies implements CelestialBodies {

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
     * Get the solar system barycenter aggregated body.
     * <p>
     * Both the {@link CelestialBody#getInertiallyOrientedFrame() inertially
     * oriented frame} and {@link CelestialBody#getBodyOrientedFrame() body
     * oriented frame} for this aggregated body are aligned with
     * {@link FramesFactory#getICRF() ICRF} (and therefore also
     * {@link FramesFactory#getGCRF() GCRF})
     * </p>
     *
     * @return solar system barycenter aggregated body
     */
    @Override
    public native CelestialBody getSolarSystemBarycenter();

    /**
     * Get the Sun singleton body.
     *
     * @return Sun body
     */
    @Override
    public native CelestialBody getSun();

    /**
     * Get the Mercury singleton body.
     *
     * @return Sun body
     */
    @Override
    public native CelestialBody getMercury();

    /**
     * Get the Venus singleton body.
     *
     * @return Venus body
     */
    @Override
    public native CelestialBody getVenus();

    /**
     * Get the Earth-Moon barycenter singleton bodies pair.
     * <p>
     * Both the {@link CelestialBody#getInertiallyOrientedFrame() inertially
     * oriented frame} and {@link CelestialBody#getBodyOrientedFrame() body
     * oriented frame} for this bodies pair are aligned with
     * {@link FramesFactory#getICRF() ICRF} (and therefore also
     * {@link FramesFactory#getGCRF() GCRF})
     * </p>
     *
     * @return Earth-Moon barycenter bodies pair
     */
    @Override
    public native CelestialBody getEarthMoonBarycenter();

    /**
     * Get the Earth singleton body.
     *
     * @return Earth body
     */
    @Override
    public native CelestialBody getEarth();

    /**
     * Get the Moon singleton body.
     *
     * @return Moon body
     */
    @Override
    public native CelestialBody getMoon();

    /**
     * Get the Mars singleton body.
     *
     * @return Mars body
     */
    @Override
    public native CelestialBody getMars();

    /**
     * Get the Jupiter singleton body.
     *
     * @return Jupiter body
     */
    @Override
    public native CelestialBody getJupiter();

    /**
     * Get the Saturn singleton body.
     *
     * @return Saturn body
     */
    @Override
    public native CelestialBody getSaturn();

    /**
     * Get the Uranus singleton body.
     *
     * @return Uranus body
     */
    @Override
    public native CelestialBody getUranus();

    /**
     * Get the Neptune singleton body.
     *
     * @return Neptune body
     */
    @Override
    public native CelestialBody getNeptune();

    /**
     * Get the Pluto singleton body.
     *
     * @return Pluto body
     */
    @Override
    public native CelestialBody getPluto();

    /**
     * Get a celestial body. The names of the common bodies are defined as constants in
     * {@link CelestialBodyFactory}.
     *
     * @param name name of the celestial body
     * @return celestial body
     */
    @Override
    public native CelestialBody getBody(String name);
}
