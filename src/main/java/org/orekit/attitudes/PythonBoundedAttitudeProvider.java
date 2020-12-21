package org.orekit.attitudes;

import org.hipparchus.RealFieldElement;
import org.orekit.frames.Frame;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.FieldAbsoluteDate;
import org.orekit.utils.FieldPVCoordinatesProvider;
import org.orekit.utils.PVCoordinatesProvider;

/* TODO: PHY IMPLEMENT */

public class PythonBoundedAttitudeProvider implements BoundedAttitudeProvider {
    /**
     * Compute the attitude corresponding to an orbital state.
     *
     * @param pvProv local position-velocity provider around current date
     * @param date   current date
     * @param frame  reference frame from which attitude is computed
     * @return attitude attitude on the specified date and position-velocity state
     */
    @Override
    public Attitude getAttitude(PVCoordinatesProvider pvProv, AbsoluteDate date, Frame frame) {
        return null;
    }

    /**
     * Compute the attitude corresponding to an orbital state.
     *
     * @param pvProv local position-velocity provider around current date
     * @param date   current date
     * @param frame  reference frame from which attitude is computed
     * @return attitude attitude on the specified date and position-velocity state
     * @since 9.0
     */
    @Override
    public <T extends RealFieldElement<T>> FieldAttitude<T> getAttitude(FieldPVCoordinatesProvider<T> pvProv, FieldAbsoluteDate<T> date, Frame frame) {
        return null;
    }

    /**
     * Get the first date of the range.
     *
     * @return the first date of the range
     */
    @Override
    public AbsoluteDate getMinDate() {
        return null;
    }

    /**
     * Get the last date of the range.
     *
     * @return the last date of the range
     */
    @Override
    public AbsoluteDate getMaxDate() {
        return null;
    }
}
