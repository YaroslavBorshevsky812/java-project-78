package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    private int minLength = 0;
    private String contains;

    private boolean required = false;

    public StringSchema required() {
        this.required = true;
        return this;
    }

    public boolean isRequired() {
        return required;
    }

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
