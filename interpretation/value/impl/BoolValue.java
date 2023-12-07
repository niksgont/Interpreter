package interpretation.value.impl;

import interpretation.value.Value;

public final class BoolValue implements Value {
    private final boolean value;

    public BoolValue(boolean value) {
        this.value = value;
    }

    @Override
    public Value clone() {
        return new BoolValue(value);
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }

    public Object getValue() {
        return value;
    }
}

