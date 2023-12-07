package interpretation.value.impl;

import interpretation.value.Value;

public class StrValue implements Value {
    private final String value;

    public StrValue(String value) {
        this.value = value;
    }

    @Override
    public Value clone() {
        return new StrValue(new String(value)); // Creating a new string object with the same content
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }
}
