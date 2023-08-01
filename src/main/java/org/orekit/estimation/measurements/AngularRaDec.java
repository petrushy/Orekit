/* Copyright 2002-2023 CS GROUP
 * Licensed to CS GROUP (CS) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * CS licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.orekit.estimation.measurements;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.hipparchus.analysis.differentiation.Gradient;
import org.hipparchus.analysis.differentiation.GradientField;
import org.hipparchus.geometry.euclidean.threed.FieldVector3D;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.hipparchus.util.MathUtils;
import org.orekit.annotation.DefaultDataContext;
import org.orekit.frames.FieldTransform;
import org.orekit.frames.Frame;
import org.orekit.frames.FramesFactory;
import org.orekit.propagation.Propagator;
import org.orekit.propagation.SpacecraftState;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.FieldAbsoluteDate;
import org.orekit.utils.AbsolutePVCoordinates;
import org.orekit.utils.ParameterDriver;
import org.orekit.utils.TimeSpanMap.Span;
import org.orekit.utils.TimeStampedFieldPVCoordinates;
import org.orekit.utils.TimeStampedPVCoordinates;

/** Class modeling an Right Ascension - Declination measurement from a ground point (station, telescope).
 * The angles are given in an inertial reference frame.
 * The motion of the spacecraft during the signal flight time is taken into
 * account. The date of the measurement corresponds to the reception on
 * ground of the reflected signal.
 *
 * @author Thierry Ceolin
 * @author Maxime Journot
 * @since 9.0
 */
public class AngularRaDec extends GroundReceiverMeasurement<AngularRaDec> {

    /** Type of the measurement. */
    public static final String MEASUREMENT_TYPE = "AngularRaDec";

    /** Reference frame in which the right ascension - declination angles are given. */
    private final Frame referenceFrame;

    /** Simple constructor.
     * @param station ground station from which measurement is performed
     * @param referenceFrame Reference frame in which the right ascension - declination angles are given
     * @param date date of the measurement
     * @param angular observed value
     * @param sigma theoretical standard deviation
     * @param baseWeight base weight
     * @param satellite satellite related to this measurement
     * @since 9.3
     */
    public AngularRaDec(final GroundStation station, final Frame referenceFrame, final AbsoluteDate date,
                        final double[] angular, final double[] sigma, final double[] baseWeight,
                        final ObservableSatellite satellite) {
        super(station, false, date, angular, sigma, baseWeight, satellite);
        this.referenceFrame = referenceFrame;
    }

    /** Get the reference frame in which the right ascension - declination angles are given.
     * @return reference frame in which the right ascension - declination angles are given
     */
    public Frame getReferenceFrame() {
        return referenceFrame;
    }

    /** {@inheritDoc} */
    @Override
    protected EstimatedMeasurement<AngularRaDec> theoreticalEvaluation(final int iteration, final int evaluation,
                                                                       final SpacecraftState[] states) {

        final SpacecraftState state = states[0];

        // Right Ascension/elevation (in reference frame )derivatives are computed with respect to spacecraft state in inertial frame
        // and station parameters
        // ----------------------
        //
        // Parameters:
        //  - 0..2 - Position of the spacecraft in inertial frame
        //  - 3..5 - Velocity of the spacecraft in inertial frame
        //  - 6..n - station parameters (clock offset, station offsets, pole, prime meridian...)

        // Get the number of parameters used for derivation
        // Place the selected drivers into a map
        int nbParams = 6;
        final Map<String, Integer> indices = new HashMap<>();
        for (ParameterDriver driver : getParametersDrivers()) {
            if (driver.isSelected()) {
                for (Span<String> span = driver.getNamesSpanMap().getFirstSpan(); span != null; span = span.next()) {

                    if (!indices.containsKey(span.getData())) {
                        indices.put(span.getData(), nbParams++);
                    }
                }
            }
        }
        final FieldVector3D<Gradient> zero = FieldVector3D.getZero(GradientField.getField(nbParams));

        // Coordinates of the spacecraft expressed as a gradient
        final TimeStampedFieldPVCoordinates<Gradient> pvaDS = getCoordinates(state, 0, nbParams);

        // Transform between station and inertial frame, expressed as a gradient
        // The components of station's position in offset frame are the 3 last derivative parameters
        final FieldTransform<Gradient> offsetToInertialDownlink =
                        getStation().getOffsetToInertial(state.getFrame(), getDate(), nbParams, indices);
        final FieldAbsoluteDate<Gradient> downlinkDateDS =
                        offsetToInertialDownlink.getFieldDate();

        // Station position/velocity in inertial frame at end of the downlink leg
        final TimeStampedFieldPVCoordinates<Gradient> stationDownlink =
                        offsetToInertialDownlink.transformPVCoordinates(new TimeStampedFieldPVCoordinates<>(downlinkDateDS,
                                                                                                            zero, zero, zero));

        // Compute propagation times
        // (if state has already been set up to pre-compensate propagation delay,
        //  we will have delta == tauD and transitState will be the same as state)

        // Downlink delay
        final Gradient tauD = signalTimeOfFlight(pvaDS, stationDownlink.getPosition(), downlinkDateDS);

        // Transit state
        final Gradient        delta        = downlinkDateDS.durationFrom(state.getDate());
        final Gradient        deltaMTauD   = tauD.negate().add(delta);
        final SpacecraftState transitState = state.shiftedBy(deltaMTauD.getValue());

        // Transit state (re)computed with gradients
        final TimeStampedFieldPVCoordinates<Gradient> transitStateDS = pvaDS.shiftedBy(deltaMTauD);

        // Station-satellite vector expressed in inertial frame
        final FieldVector3D<Gradient> staSatInertial = transitStateDS.getPosition().subtract(stationDownlink.getPosition());

        // Field transform from inertial to reference frame at station's reception date
        final FieldTransform<Gradient> inertialToReferenceDownlink =
                        state.getFrame().getTransformTo(referenceFrame, downlinkDateDS);

        // Station-satellite vector in reference frame
        final FieldVector3D<Gradient> staSatReference = inertialToReferenceDownlink.transformVector(staSatInertial);

        // Compute right ascension and declination
        final Gradient baseRightAscension = staSatReference.getAlpha();
        final double   twoPiWrap          = MathUtils.normalizeAngle(baseRightAscension.getReal(),
                                                                                getObservedValue()[0]) - baseRightAscension.getReal();
        final Gradient rightAscension     = baseRightAscension.add(twoPiWrap);
        final Gradient declination        = staSatReference.getDelta();

        // Prepare the estimation
        final EstimatedMeasurement<AngularRaDec> estimated =
                        new EstimatedMeasurement<>(this, iteration, evaluation,
                                                   new SpacecraftState[] {
                                                       transitState
                                                   }, new TimeStampedPVCoordinates[] {
                                                       transitStateDS.toTimeStampedPVCoordinates(),
                                                       stationDownlink.toTimeStampedPVCoordinates()
                                                   });

        // azimuth - elevation values
        estimated.setEstimatedValue(rightAscension.getValue(), declination.getValue());

        // Partial derivatives of right ascension/declination in reference frame with respect to state
        // (beware element at index 0 is the value, not a derivative)
        final double[] raDerivatives  = rightAscension.getGradient();
        final double[] decDerivatives = declination.getGradient();
        estimated.setStateDerivatives(0,
                                      Arrays.copyOfRange(raDerivatives, 0, 6), Arrays.copyOfRange(decDerivatives, 0, 6));

        // Partial derivatives with respect to parameters
        // (beware element at index 0 is the value, not a derivative)
        for (final ParameterDriver driver : getParametersDrivers()) {
            for (Span<String> span = driver.getNamesSpanMap().getFirstSpan(); span != null; span = span.next()) {
                final Integer index = indices.get(span.getData());
                if (index != null) {
                    estimated.setParameterDerivatives(driver, span.getStart(), raDerivatives[index], decDerivatives[index]);
                }
            }
        }

        return estimated;
    }

    /** Calculate the Line Of Sight of the given Radec.
     * @return Vector3D the line of Sight of the Radec
     */
    public Vector3D getLineOfSight() {
        return new Vector3D(this.getObservedValue()[0], this.getObservedValue()[1]);
    }

    /** Calculate the estimated Line Of Sight of the Radec at a given date.
     *
     * @param prop the propagator for the estimation
     * @param date the AbsoluteDate to use for the propagation
     *
     * @return Vector3D the estimate line of Sight of the Radec at the propagate date.
     */
    @DefaultDataContext
    public Vector3D getEstimatedLOS(final Propagator prop, final AbsoluteDate date) {
        final Frame                    gcrf        = FramesFactory.getGCRF();
        final TimeStampedPVCoordinates satPV       = prop.getPVCoordinates(date, gcrf);
        final AbsolutePVCoordinates    satPVInGCRF = new AbsolutePVCoordinates(gcrf, satPV);
        final SpacecraftState[]        satState    = new SpacecraftState[] { new SpacecraftState(satPVInGCRF) };
        final double[]                 angular     = this.estimate(0, 0, satState).getEstimatedValue();

        final double ra = angular[0];
        final double dec = angular[1];

        return new Vector3D(ra, dec);
    }

}
