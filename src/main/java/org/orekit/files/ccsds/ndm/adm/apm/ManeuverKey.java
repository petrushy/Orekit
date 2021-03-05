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
package org.orekit.files.ccsds.ndm.adm.apm;

import org.orekit.files.ccsds.utils.lexical.ParseToken;
import org.orekit.files.ccsds.utils.lexical.TokenType;
import org.orekit.files.ccsds.utils.parsing.ParsingContext;

/** Keys for {@link Maneuver APM maneuver} entries.
 * @author Bryan Cazabonne
 * @author Luc Maisonobe
 * @since 11.0
 */
public enum ManeuverKey {

    /** Block wrapping element in XML files. */
    maneuverParameters((token, context, container) -> true),

    /** Comment entry. */
    COMMENT((token, context, container) ->
            token.getType() == TokenType.ENTRY ? container.addComment(token.getContent()) : true),

    /** Epoch start entry. */
    MAN_EPOCH_START((token, context, container) -> token.processAsDate(container::setEpochStart, context)),

    /** Duration entry. */
    MAN_DURATION((token, context, container) -> token.processAsDouble(1.0, container::setDuration)),

    /** Reference frame entry. */
    MAN_REF_FRAME((token, context, container) -> token.processAsNormalizedString(container::setRefFrameString)),

    /** First torque vector component entry. */
    MAN_TOR_1((token, context, container) -> token.processAsIndexedDouble(0, 1.0, container::setTorque)),

    /** Second torque vector component entry. */
    MAN_TOR_2((token, context, container) -> token.processAsIndexedDouble(1, 1.0, container::setTorque)),

    /** Third torque vector component entry. */
    MAN_TOR_3((token, context, container) -> token.processAsIndexedDouble(2, 1.0, container::setTorque));

    /** Processing method. */
    private final TokenProcessor processor;

    /** Simple constructor.
     * @param processor processing method
     */
    ManeuverKey(final TokenProcessor processor) {
        this.processor = processor;
    }

    /** Process one token.
     * @param token token to process
     * @param context parsing context
     * @param container container to fill
     * @return true of token was accepted
     */
    public boolean process(final ParseToken token, final ParsingContext context, final Maneuver container) {
        return processor.process(token, context, container);
    }

    /** Interface for processing one token. */
    interface TokenProcessor {
        /** Process one token.
         * @param token token to process
         * @param context parsing context
         * @param container container to fill
         * @return true of token was accepted
         */
        boolean process(ParseToken token, ParsingContext context, Maneuver container);
    }

}
