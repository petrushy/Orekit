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
// original file EventHandler.java created by CS Systèmes d'Information

package org.orekit.propagation.events.handlers;

import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.events.EventDetector;
import org.hipparchus.ode.events.Action;
 ;
import org.orekit.time.AbsoluteDate;


public class PythonEventHandler implements EventHandler
{
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
	public native void init(SpacecraftState initialState, AbsoluteDate target, EventDetector detector);

	@Override
	public native Action eventOccurred(SpacecraftState s, EventDetector detector, boolean increasing);

	@Override
	public native SpacecraftState resetState(EventDetector detector, SpacecraftState oldState);
}

