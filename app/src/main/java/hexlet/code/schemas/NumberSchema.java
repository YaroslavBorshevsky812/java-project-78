package hexlet.code.schemas;

import hexlet.code.CheckList;

public final class NumberSchema extends BaseSchema<Number> {

    public NumberSchema required() {
        isRequired = true;
        addCheck(CheckList.REQUIRED, x -> x != null);

        return this;
    }

    public NumberSchema positive() {
        addCheck(CheckList.POSITIVE, x -> x.doubleValue() > 0);
        return this;
    }

    public NumberSchema range(Integer minValue, Integer maxValue) {
        addCheck(CheckList.RANGE, x -> x.doubleValue() >= minValue && x.doubleValue() <= maxValue);
        return this;
    }
}
