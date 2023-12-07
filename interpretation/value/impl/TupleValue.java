package interpretation.value.impl;

import interpretation.value.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TupleValue implements Value {
    private final Map<String, Value> value;

    public TupleValue() {
        this.value = new HashMap<>();
    }

    public TupleValue(List<TupleElement> elements) {
        this();
        for (TupleElement element : elements) {
            this.value.put(element.getKey(), element.getValue());
        }
    }

    @Override
    public Value clone() {
        List<TupleElement> tupleElements = new ArrayList<>();
        for (Map.Entry<String, Value> entry : value.entrySet()) {
            tupleElements.add(new TupleElement(entry.getKey(), entry.getValue().clone()));
        }
        return new TupleValue(tupleElements);
    }

    @Override
    public String toString() {
        List<TupleElement> elements = new ArrayList<>();
        for (Map.Entry<String, Value> entry : value.entrySet()) {
            elements.add(new TupleElement(entry.getKey(), entry.getValue()));
        }
        return "{" + elements + "}";
    }

    public void update(String key, Value value) {
        this.value.put(key, value);
    }

    @Override
    public Object getValue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getValue'");
    }
}
