package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    private int minLength = 0;

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
        addCheck(x -> minLength > 0 && x.length() > minLength);

        return this;
    }

    public StringSchema contains(String text) {
        addCheck(x -> x.contains(text));
        return this;
    }

    @Override
    public boolean isValid(String value) {
        if (value == null || value.isEmpty()) {
            return !isRequired();
        }

        return validate(value);
    }
}
