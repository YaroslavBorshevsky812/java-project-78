package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    public MapSchema required() {
        isRequired = true;
        requiredRule = Objects::nonNull;
        return this;
    }

    public MapSchema sizeof(Integer newSize) {
        addCheck("SIZE_OF", value -> newSize == null || value.size() == newSize);
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> newShape) {
        addCheck("SHAPE", value -> {
            if (newShape == null) {
                return true;
            }
            return newShape.entrySet().stream()
                           .allMatch(entry -> {
                               String key = entry.getKey();
                               if (!value.containsKey(key)) {
                                   return false;
                               }
                               Object mapValue = value.get(key);
                               BaseSchema<T> schema = entry.getValue();
                               return schema.isValid((T) mapValue);
                           });
        });
        return this;
    }
}
