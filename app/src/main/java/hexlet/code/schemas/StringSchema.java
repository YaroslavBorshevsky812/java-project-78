package hexlet.code.schemas;

public class StringSchema extends BaseSchema<StringSchema, String> {
    private int minLength = 0;
    private String contains;

    public StringSchema minLength(int limit) {
        this.minLength = limit;
        return this;
    }

    public StringSchema contains(String text) {
        this.contains = text;
        return this;
    }

    @Override
    public boolean isValid(String value) {
        if (value == null) {
            return !isRequired();
        }

        if (isRequired() && value.isEmpty()) {
            return false;
        }

        if (minLength > 0 && value.length() < minLength) {
            return false;
        }

        if (contains != null && !value.contains(contains)) {
            return false;
        }

        return true;
    }
}
