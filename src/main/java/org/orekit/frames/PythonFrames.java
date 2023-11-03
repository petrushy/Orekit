package org.orekit.frames;

 ;
import org.orekit.time.UT1Scale;
import org.orekit.utils.IERSConventions;

public class PythonFrames implements Frames {

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

    @Override
    public native EOPHistory getEOPHistory(IERSConventions conventions, boolean simpleEOP);

    @Override
    public native Frame getFrame(Predefined factoryKey);

    @Override
    public native Frame getGCRF();

    @Override
    public native Frame getICRF();

    @Override
    public native Frame getEcliptic(IERSConventions conventions);

    @Override
    public native FactoryManagedFrame getEME2000();

    @Override
    public native FactoryManagedFrame getITRF(IERSConventions conventions, boolean simpleEOP);

    @Override
    public native FactoryManagedFrame getTIRF(IERSConventions conventions);

    @Override
    public native VersionedITRF getITRF(ITRFVersion version, IERSConventions conventions, boolean simpleEOP);

    @Override
    public native Frame buildUncachedITRF(UT1Scale ut1);

    @Override
    public native FactoryManagedFrame getTIRF(IERSConventions conventions, boolean simpleEOP);

    @Override
    public native FactoryManagedFrame getCIRF(IERSConventions conventions, boolean simpleEOP);

    @Override
    public native FactoryManagedFrame getVeis1950();

    @Override
    public native FactoryManagedFrame getITRFEquinox(IERSConventions conventions, boolean simpleEOP);

    @Override
    public native FactoryManagedFrame getGTOD(boolean applyEOPCorr);

    @Override
    public native FactoryManagedFrame getGTOD(IERSConventions conventions, boolean simpleEOP);

    @Override
    public native FactoryManagedFrame getTOD(boolean applyEOPCorr);

    @Override
    public native FactoryManagedFrame getTOD(IERSConventions conventions, boolean simpleEOP);

    @Override
    public native FactoryManagedFrame getMOD(boolean applyEOPCorr);

    @Override
    public native FactoryManagedFrame getMOD(IERSConventions conventions);

    @Override
    public native FactoryManagedFrame getTEME();

    @Override
    public native FactoryManagedFrame getPZ9011(IERSConventions convention, boolean simpleEOP);
}
