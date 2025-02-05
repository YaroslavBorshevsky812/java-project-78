package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<NumberSchema, Number> {
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

    public boolean isValid(Number value) {
        if (value == null) {
            return !isRequired();
        }
        if (positive && value.doubleValue() <= 0) {
            return false;
        }
        if (min != null && value.intValue() < min) {
            return false;
        }
        if (max != null && value.intValue() > max) {
            return false;
        }

        return true;
    }
}
