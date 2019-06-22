/** Copyright 2014 SSC and 2002-2014 CS Systèmes d'Information
 * Licensed to CS SystÃ¨mes d'Information (CS) under one or more
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

// this file was created by SCC and is largely a derived work from the
// original file EventDetector.java created by CS Systèmes d'Information

package org.orekit.python;

import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.events.EventDetector;
import org.hipparchus.ode.events.Action;
import org.orekit.time.AbsoluteDate;

/** This interface represents space-dynamics aware events detectors.
*
* <p>It mirrors the {@link org.apache.commons.math3.ode.events.EventHandler
* EventHandler} interface from <a href="http://commons.apache.org/math/">
* Apache Commons Math</a> but provides a space-dynamics interface to the
* methods.</p>
*
* <p>Events detectors are a useful solution to meet the requirements
* of propagators concerning discrete conditions. The state of each
* event detector is queried by the integrator at each step. When the
* sign of the underlying g switching function changes, the step is rejected
* and reduced, in order to make sure the sign changes occur only at steps
* boundaries.</p>
*
* <p>When step ends exactly at a switching function sign change, the corresponding
* event is triggered, by calling the {@link #eventOccurred(SpacecraftState, boolean)}
* method. The method can do whatever it needs with the event (logging it, performing
* some processing, ignore it ...). The return value of the method will be used by
* the propagator to stop or resume propagation, possibly changing the state vector.<p>
*
* @author Luc Maisonobe
* @author V&eacute;ronique Pommier-Maurussane
*/
public class PythonEventDetector implements EventDetector
{

	static final long serialVersionUID = 1L;

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

	/** {@inheritDoc} */
	@Override
	public native void init(SpacecraftState s0, AbsoluteDate t);

	/** {@inheritDoc} */
	@Override
	public native double g(SpacecraftState s);

	/** {@inheritDoc} */
	@Override
	public native double getThreshold();

	/** {@inheritDoc} */
	@Override
	public native double getMaxCheckInterval();

	/** {@inheritDoc} */
	@Override
	public native int getMaxIterationCount();

	/** {@inheritDoc} */
	@Override
	public native Action eventOccurred(SpacecraftState s, boolean increasing);

	/** {@inheritDoc} */
	@Override
	public native SpacecraftState resetState(SpacecraftState oldState);

}

