package interpretation.value.impl;

import interpretation.value.Value;

public class TupleElement implements Value {
    private final String key;
    private final Value value;

    public TupleElement(String key, Value value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Value clone() {
        return new TupleElement(key, value.clone());
    }

    @Override
    public String toString() {
        return key + " = " + value.toString();
    }

    public String getKey() {
        return key;
    }

    public Value getValue() {
        return value;
    }
}
