package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    private int minLength = 0;

    public StringSchema required() {
        isRequired = true;
        requiredRule = x -> x != null && !x.isEmpty();
        return this;
    }

    public StringSchema minLength(int limit) {
        this.minLength = limit;
        addCheck("MIN_LENGTH", x -> minLength > 0 && x.length() > minLength);

        return this;
    }

    public StringSchema contains(String text) {
        addCheck("CONTAINS", x -> x.contains(text));
        return this;
    }
}
