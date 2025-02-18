package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected List<Predicate<T>> checkList = new ArrayList<>();

    public abstract boolean isValid(T value);

    protected void addCheck(Predicate<T> predicate) {
        checkList.add(predicate);
    }

    protected boolean validate(T value) {
        return checkList.stream().allMatch(predicate -> predicate.test(value));
    }
}

