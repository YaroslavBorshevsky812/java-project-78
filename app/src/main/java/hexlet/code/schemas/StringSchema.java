package hexlet.code.schemas;

public class StringSchema extends BaseSchema<StringSchema> {
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
    public boolean isValid(Object value) {
        if (value == null) {
            return !isRequired();
        }

        if (!(value instanceof String)) {
            return false;
        }

        String stringValue = (String) value;

        if (isRequired() && stringValue.isEmpty()) {
            return false;
        }

        if (minLength > 0 && stringValue.length() < minLength) {
            return false;
        }

        if (contains != null && !stringValue.contains(contains)) {
            return false;
        }

        return true;
    }
}
