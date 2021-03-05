/* Copyright 2002-2021 CS GROUP
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
package org.orekit.files.ccsds.ndm.tdm;

import org.orekit.files.ccsds.utils.lexical.ParseToken;
import org.orekit.files.ccsds.utils.parsing.ParsingContext;


/** Keys for {@link TdmMetadata TDM container} entries.
 * @author Maxime Journot
 * @since 11.0
 */
public enum TdmMetadataKey {

    /** Start time entry. */
    START_TIME((token, context, container) -> token.processAsDate(container::setStartTime, context)),

    /** Stop time entry. */
    STOP_TIME((token, context, container) -> token.processAsDate(container::setStopTime, context)),

    /** First participant entry. */
    PARTICIPANT_1((token, context, container) -> token.processAsIndexedFreeTextString(1, container::addParticipant)),

    /** Second participant entry. */
    PARTICIPANT_2((token, context, container) -> token.processAsIndexedFreeTextString(2, container::addParticipant)),

    /** Third participant entry. */
    PARTICIPANT_3((token, context, container) -> token.processAsIndexedFreeTextString(3, container::addParticipant)),

    /** Fourth participant entry. */
    PARTICIPANT_4((token, context, container) -> token.processAsIndexedFreeTextString(4, container::addParticipant)),

    /** Fifth participant entry. */
    PARTICIPANT_5((token, context, container) -> token.processAsIndexedFreeTextString(5, container::addParticipant)),

    /** Mode entry. */
    MODE((token, context, container) -> token.processAsNormalizedString(container::setMode)),

    /** Path entry. */
    PATH((token, context, container) -> token.processAsNormalizedString(container::setPath)),

    /** Path 1 entry. */
    PATH_1((token, context, container) -> token.processAsNormalizedString(container::setPath1)),

    /** Path 2 entry. */
    PATH_2((token, context, container) -> token.processAsNormalizedString(container::setPath2)),

    /** Transmit band entry. */
    TRANSMIT_BAND((token, context, container) -> token.processAsNormalizedString(container::setTransmitBand)),

    /** Receive band entry. */
    RECEIVE_BAND((token, context, container) -> token.processAsNormalizedString(container::setReceiveBand)),

    /** Turnaround numerator entry. */
    TURNAROUND_NUMERATOR((token, context, container) -> token.processAsInteger(container::setTurnaroundNumerator)),

    /** turnaround denominator entry. */
    TURNAROUND_DENOMINATOR((token, context, container) -> token.processAsInteger(container::setTurnaroundDenominator)),

    /** Timetag referene entry. */
    TIMETAG_REF((token, context, container) -> token.processAsNormalizedString(container::setTimetagRef)),

    /** Integration interval entry. */
    INTEGRATION_INTERVAL((token, context, container) -> token.processAsDouble(1.0, container::setIntegrationInterval)),

    /** Integration reference entry. */
    INTEGRATION_REF((token, context, container) -> token.processAsNormalizedString(container::setIntegrationRef)),

    /** Frequency offset entry. */
    FREQ_OFFSET((token, context, container) -> token.processAsDouble(1.0, container::setFreqOffset)),

    /** Range mode entry. */
    RANGE_MODE((token, context, container) -> token.processAsNormalizedString(container::setRangeMode)),

    /** Range modulus entry. */
    RANGE_MODULUS((token, context, container) -> token.processAsDouble(1.0, container::setRangeModulus)),

    /** Range units entry. */
    RANGE_UNITS((token, context, container) -> token.processAsNormalizedString(container::setRangeUnits)),

    /** Angle type entry. */
    ANGLE_TYPE((token, context, container) -> token.processAsNormalizedString(container::setAngleType)),

    /** reference frame entry. */
    REFERENCE_FRAME((token, context, container) -> token.processAsFrame(container::setReferenceFrame, context, true, false, false)),

    /** First transmit delay entry. */
    TRANSMIT_DELAY_1((token, context, container) -> token.processAsIndexedDouble(1, 1.0, container::addTransmitDelay)),

    /** Second transmit delay entry. */
    TRANSMIT_DELAY_2((token, context, container) -> token.processAsIndexedDouble(2, 1.0, container::addTransmitDelay)),

    /** Third transmit delay entry. */
    TRANSMIT_DELAY_3((token, context, container) -> token.processAsIndexedDouble(3, 1.0, container::addTransmitDelay)),

    /** Fourth transmit delay entry. */
    TRANSMIT_DELAY_4((token, context, container) -> token.processAsIndexedDouble(4, 1.0, container::addTransmitDelay)),

    /** Fifth transmit delay entry. */
    TRANSMIT_DELAY_5((token, context, container) -> token.processAsIndexedDouble(5, 1.0, container::addTransmitDelay)),

    /** First receive delay entry. */
    RECEIVE_DELAY_1((token, context, container) -> token.processAsIndexedDouble(1, 1.0, container::addReceiveDelay)),

    /** Second receive delay entry. */
    RECEIVE_DELAY_2((token, context, container) -> token.processAsIndexedDouble(2, 1.0, container::addReceiveDelay)),

    /** Third receive delay entry. */
    RECEIVE_DELAY_3((token, context, container) -> token.processAsIndexedDouble(3, 1.0, container::addReceiveDelay)),

    /** Fourth receive delay entry. */
    RECEIVE_DELAY_4((token, context, container) -> token.processAsIndexedDouble(4, 1.0, container::addReceiveDelay)),

    /** Fifth receive delay entry. */
    RECEIVE_DELAY_5((token, context, container) -> token.processAsIndexedDouble(5, 1.0, container::addReceiveDelay)),

    /** data quality entry. */
    DATA_QUALITY((token, context, container) -> token.processAsNormalizedString(container::setDataQuality)),

    /** Angle 1 correction entry. */
    CORRECTION_ANGLE_1((token, context, container) -> token.processAsDouble(1.0, container::setCorrectionAngle1)),

    /** Angle 2 correction entry. */
    CORRECTION_ANGLE_2((token, context, container) -> token.processAsDouble(1.0, container::setCorrectionAngle2)),

    /** Doppler correction entry. */
    CORRECTION_DOPPLER((token, context, container) -> token.processAsDouble(1.0, container::setCorrectionDoppler)),

    /** Range correction entry. */
    CORRECTION_RANGE((token, context, container) -> token.processAsDouble(1.0, container::setCorrectionRange)),

    /** Recive correction entry. */
    CORRECTION_RECEIVE((token, context, container) -> token.processAsDouble(1.0, container::setCorrectionReceive)),

    /** Transmit correction entry. */
    CORRECTION_TRANSMIT((token, context, container) -> token.processAsDouble(1.0, container::setCorrectionTransmit)),

    /** Applied correction entry. */
    CORRECTIONS_APPLIED((token, context, container) -> token.processAsNormalizedString(container::setCorrectionsApplied));

    /** Processing method. */
    private final TokenProcessor processor;

    /** Simple constructor.
     * @param processor processing method
     */
    TdmMetadataKey(final TokenProcessor processor) {
        this.processor = processor;
    }

    /** Process an token.
     * @param token token to process
     * @param context parsing context
     * @param container container to fill
     * @return true if token was accepted
     */
    public boolean process(final ParseToken token, final ParsingContext context, final TdmMetadata container) {
        return processor.process(token, context, container);
    }

    /** Interface for processing one token. */
    interface TokenProcessor {
        /** Process one token.
         * @param token token to process
         * @param context parsing context
         * @param container container to fill
     * @return true if token was accepted
         */
        boolean process(ParseToken token, ParsingContext context, TdmMetadata container);
    }

}
