package interpretation.value.impl;

import interpretation.value.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrValue implements Value {
    private final Map<Integer, Value> value;

    public ArrValue() {
        this.value = new HashMap<>();
    }

    public ArrValue(List<ArrElement> elements) {
        this();
        for (ArrElement element : elements) {
            this.value.put(element.getKey(), element.getValue());
        }
    }

    @Override
    public Value clone() {
        List<ArrElement> arrElements = value.entrySet().stream()
                .map(entry -> new ArrElement(entry.getKey(), entry.getValue().clone()))
                .toList();
        return new ArrValue(arrElements);
    }

    @Override
    public String toString() {
        List<ArrElement> arrElements = value.entrySet().stream()
                .map(entry -> new ArrElement(entry.getKey(), entry.getValue()))
                .toList();
        return "{" + arrElements.stream().map(ArrElement::toString).toList() + "}";
    }

    public void update(int key, Value value) {
        this.value.put(key, value);
    }

    // Getters for value if needed
    public Map<Integer, Value> getValue() {
        return value;
    }
}