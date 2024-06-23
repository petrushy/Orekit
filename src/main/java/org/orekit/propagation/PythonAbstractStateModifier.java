package org.orekit.propagation;

public class PythonAbstractStateModifier extends AbstractStateModifier {

    @Override
    public native SpacecraftState change(SpacecraftState state);
}
