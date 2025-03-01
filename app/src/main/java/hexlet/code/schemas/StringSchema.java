package hexlet.code.schemas;

import hexlet.code.CheckList;

public final class StringSchema extends BaseSchema<String> {
    private int minLength = 0;

    public StringSchema required() {
        isRequired = true;
        addCheck(CheckList.REQUIRED, x -> x != null && !x.isEmpty());
        return this;
    }

    public StringSchema minLength(int limit) {
        this.minLength = limit;
        addCheck(CheckList.MIN_LENGTH, x -> minLength > 0 && x.length() > minLength);

        return this;
    }

    public StringSchema contains(String text) {
        addCheck(CheckList.CONTAINS, x -> x.contains(text));
        return this;
    }
}
