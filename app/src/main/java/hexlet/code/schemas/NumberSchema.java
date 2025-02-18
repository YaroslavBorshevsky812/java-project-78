package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Number> {
    private boolean required = false;

    public NumberSchema required() {
        this.required = true;

        return this;
    }

    public boolean isRequired() {
        return required;
    }

    public NumberSchema positive() {
        addCheck(x -> x.doubleValue() > 0);
        return this;
    }

    public NumberSchema range(Integer minValue, Integer maxValue) {
        addCheck(x -> x.doubleValue() >= minValue && x.doubleValue() <= maxValue);
        return this;
    }

    public boolean isValid(Number value) {
        if (value == null) {
            return !isRequired();
        }
        return validate(value);
    }
}
