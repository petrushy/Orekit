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

// this file was created by SSC 2021 and is largely a derived work from the
// original java class


package org.orekit.gnss.metric.ntrip;

import org.orekit.gnss.metric.messages.ParsedMessage;

import java.util.concurrent.atomic.AtomicReference;

public class PythonMessageObserver implements MessageObserver {

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
     * Notify that an encoded message is available.
     * <p>
     * Beware that this method <em>will</em> be called from an internal
     * dedicated stream-reading thread. Implementations <em>must</em>
     * take to:
     * </p>
     * <ul>
     *   <li>not perform long processing there to avoid blocking the stream-reading thread</li>
     *   <li>take care of thread-safety when extracting data from the message</li>
     * </ul>
     * <p>
     * The only filtering that can be specified when {@link
     * NtripClient#addObserver(int, String, MessageObserver) adding} an observer to a
     * {@link NtripClient} is based on message type and mount point. If additional filtering
     * is needed (for example on message content like satellites ids, it must be performed
     * by the observer itself when notified (see example below).
     * </p>
     * <p>
     * The recommended way to implement this method is to simply build a domain object
     * from the message fields (for example a gnss propagator) and to store it in the
     * observer class as an instance field using a {@link AtomicReference
     * AtomicReference} as follows:
     * </p>
     * <pre>
     * public class GPSProvider implements PVCoordinatesProvider, RTCMMessageObserver {
     *
     *     private final int                                filteringId;
     *     private final AtomicReference&lt;GPSPropagator&gt; propagator;
     *
     *     public void messageAvailable(String mountPoint, ParsedMessage message) {
     *         MessageXXX msg = (MessageXXX) message;
     *         GPSPropagator oldPropagator = propagator.get();
     *         if (msg.getSatId() == filteringId) {
     *             GPSPropagator newPropagator = new GPSPropagator(msg.get...(),
     *                                                             msg.get...(),
     *                                                             msg.get...());
     *             // only set propagator if no other observer was notified
     *             // while we were asleep
     *             propagator.compareAndSet(oldPropagator, newPropagator);
     *         }
     *     }
     *
     *     public TimeStampedPVCoordinates getPVCoordinates(AbsoluteDate date, Frame frame) {
     *         GPSPropagator lastAvailablePropagator = propagator.get();
     *         // use the retrieved propagator to compute position-velocity
     *     }
     *
     * }
     * </pre>
     *
     * @param mountPoint mount point from which the message comes
     * @param message    last available message
     */
    @Override
    public native void messageAvailable(String mountPoint, ParsedMessage message);
}
