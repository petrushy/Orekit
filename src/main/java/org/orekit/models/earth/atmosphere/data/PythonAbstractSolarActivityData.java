package org.orekit.models.earth.atmosphere.data;

import org.orekit.data.DataProvidersManager;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.TimeScale;

public class PythonAbstractSolarActivityData<L extends AbstractSolarActivityDataLoader.LineParameters, D extends AbstractSolarActivityDataLoader<L>> extends AbstractSolarActivityData<L, D> {
    public PythonAbstractSolarActivityData(String supportedNames, D loader, DataProvidersManager dataProvidersManager, TimeScale utc, int maxSlots, double maxSpan, double maxInterval, double minimumStep) {
        super(supportedNames, loader, dataProvidersManager, utc, maxSlots, maxSpan, maxInterval, minimumStep);
    }

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

    @Override
    public native double getInstantFlux(AbsoluteDate date);

    @Override
    public native double getMeanFlux(AbsoluteDate date);

    @Override
    public native double getThreeHourlyKP(AbsoluteDate date);

    @Override
    public native double get24HoursKp(AbsoluteDate date);

    @Override
    public native double getDailyFlux(AbsoluteDate date);

    @Override
    public native double getAverageFlux(AbsoluteDate date);

    @Override
    public native double[] getAp(AbsoluteDate date);
}
