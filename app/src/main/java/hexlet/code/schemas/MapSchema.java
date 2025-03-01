package hexlet.code.schemas;

import hexlet.code.CheckList;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    private Integer size;
    private Map<String, BaseSchema<?>> shape;

    public MapSchema required() {
        addCheck(CheckList.REQUIRED, value -> value != null);
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
            for (Map.Entry<String, BaseSchema<?>> entry : shape.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                if (!value.containsKey(key)) {
                    return false;
                }
                Object mapValue = value.get(key);
                if (!isValid(schema, mapValue)) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }

    @Override
    public boolean isValid(Map<?, ?> value) {
        if (value == null && !checkMap.containsKey(CheckList.REQUIRED)) {
            return true;
        }
        return checkMap.values().stream().allMatch(predicate -> predicate.test(value));
    }

    private <V> boolean isValid(BaseSchema<V> schema, Object value) {
        if (value == null) {
            return schema.isValid(null);
        }
        return schema.isValid((V) value);
    }
}
