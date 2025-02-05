package hexlet.code;

public class NumberSchema extends BaseSchema<NumberSchema, Number> {
    private boolean positive = false;
    private Integer min;
    private Integer max;

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public NumberSchema range(Integer min, Integer max) {
        this.min = min;
        this.max = max;
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
