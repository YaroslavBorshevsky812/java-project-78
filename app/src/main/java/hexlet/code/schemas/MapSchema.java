package hexlet.code.schemas;

import hexlet.code.CheckList;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    private Integer size;
    private Map<String, BaseSchema<?>> shape;

    public MapSchema required() {
        isRequired = true;
        requiredRule = Objects::nonNull;
        return this;
    }

    public MapSchema sizeof(Integer newSize) {
        this.size = newSize;
        addCheck(CheckList.SIZE_OF, value -> size == null || value.size() == size);
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> newShape) {
        this.shape = (Map<String, BaseSchema<?>>) (Map<?, ?>) newShape;
        addCheck(CheckList.SHAPE, value -> {
            if (shape == null) {
                return true;
            }
            return shape.entrySet().stream()
                        .allMatch(entry -> {
                            String key = entry.getKey();
                            BaseSchema<?> schema = entry.getValue();
                            if (!value.containsKey(key)) {
                                return false;
                            }
                            Object mapValue = value.get(key);
                            return isValid(schema, mapValue);
                        });
        });
        return this;
    }

    private boolean isValid(BaseSchema<?> schema, Object value) {
        if (value == null) {
            return schema.isValid(null);
        }
        try {
            var isValidMethod = schema.getClass().getMethod("isValid", Object.class);
            return (boolean) isValidMethod.invoke(schema, value);
        } catch (Exception e) {
            return false;
        }
    }
}
