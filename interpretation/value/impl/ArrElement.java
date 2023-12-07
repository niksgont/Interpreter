package interpretation.value.impl;

import interpretation.value.Value;

public class ArrElement implements Value {
    private final int key;
    private final Value value;

    public ArrElement(int key, Value value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Value clone() {
        return new ArrElement(key, value.clone());
    }

    @Override
    public String toString() {
        return key + " = " + value;
    }

    // Getters for key and value if needed
    public int getKey() {
        return key;
    }

    public Value getValue() {
        return value;
    }
}
