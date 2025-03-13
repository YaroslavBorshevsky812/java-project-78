package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema<Number> {

    public NumberSchema required() {
        isRequired = true;
        requiredRule = Objects::nonNull;

        return this;
    }

    public NumberSchema positive() {
        addCheck("POSITIVE", x -> x.doubleValue() > 0);
        return this;
    }

    public NumberSchema range(Integer minValue, Integer maxValue) {
        addCheck("RANGE", x -> x.doubleValue() >= minValue && x.doubleValue() <= maxValue);
        return this;
    }
}
