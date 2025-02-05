package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    private Integer size;
    private Map<String, BaseSchema<?>> shape;
    private boolean required = false;

    public MapSchema required() {
        this.required = true;
        return this;
    }

    public boolean isRequired() {
        return required;
    }

    public MapSchema sizeof(Integer newSize) {
        this.size = newSize;
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> newShape) {
        this.shape = (Map<String, BaseSchema<?>>) (Map<?, ?>) newShape;
        return this;
    }

    @Override
    public boolean isValid(Map<?, ?> value) {
        if (value == null) {
            return !isRequired();
        }
        if (size != null && value.size() != size) {
            return false;
        }
        if (shape != null) {
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
        }
        return true;
    }

    private <V> boolean isValid(BaseSchema<V> schema, Object value) {
        if (value == null) {
            return schema.isValid(null);
        }
        return schema.isValid((V) value);
    }
}
