package interpretation.value.impl;

import interpretation.value.NumericValue;
import interpretation.value.Value;

import java.math.BigDecimal;

public class DecValue implements Value, NumericValue {
    private final BigDecimal value;

    public DecValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public Value clone() {
        return new DecValue(BigDecimal.valueOf(this.value.doubleValue()));
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public BigDecimal getValue() {
        return value;
    }
}
