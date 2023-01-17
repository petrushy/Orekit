/* Copyright 2002-2022 CS GROUP
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

// this file was created by SSC 2022 and is largely a derived work from the
// original java class/interface

/* TODO: Check what is reasonable implementation of this class

 */
package org.orekit.forces.maneuvers.trigger;

import org.hipparchus.CalculusFieldElement;
import org.hipparchus.Field;
import org.orekit.propagation.FieldPropagator;
import org.orekit.propagation.events.AbstractDetector;
import org.orekit.propagation.events.FieldAbstractDetector;
import org.orekit.propagation.events.FieldEventDetector;
import org.orekit.propagation.events.handlers.FieldEventHandler;

public class PythonStartStopEventsTrigger<A extends AbstractDetector<A>, O extends AbstractDetector<O>> extends StartStopEventsTrigger<A, O> {
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
     * Simple constructor.
     * <p>
     * Note that the {@code startDetector} and {@code stopDetector} passed as an argument are used only
     * as a <em>prototypes</em> from which new detectors will be built using their
     * {@link AbstractDetector#withHandler(EventHandler) withHandler} methods to
     * set up internal handlers. The original event handlers from the prototype
     * will be <em>ignored</em> and never called.
     * </p>
     * <p>
     * If the trigger is used in a {@link FieldPropagator field-based propagation},
     * the detector will be automatically converted to a field equivalent. Beware however that the
     * {@link FieldEventHandler#eventOccurred(FieldSpacecraftState, FieldEventDetector, boolean) eventOccurred}
     * of the converted propagator <em>will</em> call the method with the same name in the prototype
     * detector, in order to get the correct return value.
     * </p>
     *
     * @param prototypeStartDetector prototype detector for firing start
     * @param prototypeStopDetector  prototype detector for firing stop
     */
    public PythonStartStopEventsTrigger(A prototypeStartDetector, O prototypeStopDetector) {
        super(prototypeStartDetector, prototypeStopDetector);
    }

    /**
     * Convert a primitive firing start detector into a field firing start detector.
     * <p>
     * There is not need to set up {@link FieldAbstractDetector#withMaxCheck(CalculusFieldElement) withMaxCheck},
     * {@link FieldAbstractDetector#withThreshold(CalculusFieldElement) withThreshold}, or
     * {@link FieldAbstractDetector#withHandler(FieldEventHandler) withHandler}
     * in the converted detector, this will be done by caller.
     * </p>
     * <p>
     * A skeleton implementation of this method to convert some {@code XyzDetector} into {@code FieldXyzDetector},
     * considering these detectors are created from a date and a number parameter is:
     * </p>
     * <pre>{@code
     *     protected <D extends FieldEventDetector<S>, S extends CalculusFieldElement<S>>
     *         FieldAbstractDetector<D, S> convertStartDetector(final Field<S> field, final XyzDetector detector) {
     *
     *         final FieldAbsoluteDate<S> date  = new FieldAbsoluteDate<>(field, detector.getDate());
     *         final S                    param = field.getZero().newInstance(detector.getParam());
     *
     *         final FieldAbstractDetector<D, S> converted = (FieldAbstractDetector<D, S>) new FieldXyzDetector<>(date, param);
     *         return converted;
     *
     *     }
     * }
     * </pre>
     *
     * @param field    field to which the state belongs
     * @param detector primitive firing start detector to convert
     * @return converted firing start detector
     */
    @Override
    public native <D extends FieldEventDetector<S>, S extends CalculusFieldElement<S>> FieldAbstractDetector<D, S> convertStartDetector(Field<S> field, A detector);

    /**
     * Convert a primitive firing stop detector into a field firing stop detector.
     * <p>
     * There is not need to set up {@link FieldAbstractDetector#withMaxCheck(CalculusFieldElement) withMaxCheck},
     * {@link FieldAbstractDetector#withThreshold(CalculusFieldElement) withThreshold}, or
     * {@link FieldAbstractDetector#withHandler(FieldEventHandler) withHandler}
     * in the converted detector, this will be done by caller.
     * </p>
     * <p>
     * A skeleton implementation of this method to convert some {@code XyzDetector} into {@code FieldXyzDetector},
     * considering these detectors are created from a date and a number parameter is:
     * </p>
     * <pre>{@code
     *     protected <D extends FieldEventDetector<S>, S extends CalculusFieldElement<S>>
     *         FieldAbstractDetector<D, S> convertStopDetector(final Field<S> field, final XyzDetector detector) {
     *
     *         final FieldAbsoluteDate<S> date  = new FieldAbsoluteDate<>(field, detector.getDate());
     *         final S                    param = field.getZero().newInstance(detector.getParam());
     *
     *         final FieldAbstractDetector<D, S> converted = (FieldAbstractDetector<D, S>) new FieldXyzDetector<>(date, param);
     *         return converted;
     *
     *     }
     * }
     * </pre>
     *
     * @param field    field to which the state belongs
     * @param detector primitive firing stop detector to convert
     * @return converted firing stop detector
     */
    @Override
    public native <D extends FieldEventDetector<S>, S extends CalculusFieldElement<S>> FieldAbstractDetector<D, S> convertStopDetector(Field<S> field, O detector);
}
