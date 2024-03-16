/* Copyright SSC 2023
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

// This file was created by SSC and updated by SSC in 2023 and is largely a derived work from the
// original java class/interface that it inherits/implements

package org.orekit.propagation.events.handlers;

import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.events.EventDetector;
import org.hipparchus.ode.events.Action;
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

