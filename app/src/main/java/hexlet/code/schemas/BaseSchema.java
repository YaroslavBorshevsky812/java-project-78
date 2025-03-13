package hexlet.code.schemas;

import hexlet.code.CheckList;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<CheckList, Predicate<T>> checkMap = new HashMap<>();
    protected boolean isRequired = false;
    protected Predicate<T> requiredRule = Objects::isNull;

    /**
     * Проверяет, соответствует ли переданное значение всем условиям схемы.
     *
     * @param value Значение, которое нужно проверить.
     * @return Возвращает true, если значение соответствует всем условиям схемы, иначе false.
     */
    public boolean isValid(T value) {
        var isRequiredRuleValid = requiredRule.test(value);
        if (isRequired != isRequiredRuleValid) {
            return isRequiredRuleValid;
        }
        return checkMap.values().stream().allMatch(predicate -> predicate.test(value));
    };

    final void addCheck(CheckList check, Predicate<T> predicate) {
        checkMap.put(check, predicate);
    }
}
