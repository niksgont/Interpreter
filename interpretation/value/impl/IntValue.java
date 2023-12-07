package interpretation.value.impl;

import interpretation.value.NumericValue;
import interpretation.value.Value;

import java.math.BigInteger;

public class IntValue implements Value, NumericValue {
    private final BigInteger value;

    public IntValue(BigInteger value) {
        this.value = value;
    }

    @Override
    public Value clone() {
        return new IntValue(BigInteger.valueOf(value.longValue()));
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public BigInteger getValue() {
        return value;
    }
}
