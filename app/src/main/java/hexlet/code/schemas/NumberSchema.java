package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<NumberSchema> {
    private boolean positive = false;
    private Integer min;
    private Integer max;

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public NumberSchema range(Integer minValue, Integer maxValue) {
        this.min = minValue;
        this.max = maxValue;
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return !isRequired();
        }

        if (!(value instanceof Number)) {
            return false;
        }

        Number numberValue = (Number) value;

        if (positive && numberValue.doubleValue() <= 0) {
            return false;
        }
        if (min != null && numberValue.intValue() < min) {
            return false;
        }
        if (max != null && numberValue.intValue() > max) {
            return false;
        }

        return true;
    }
}
