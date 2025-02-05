package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<MapSchema> {
    private Integer size;
    private Map<String, BaseSchema<?>> shape;

    public MapSchema sizeof(Integer newSize) {
        this.size = newSize;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<?>> newShape) {
        this.shape = newShape;
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return !isRequired();
        }

        if (!(value instanceof Map)) {
            return false;
        }

        Map<?, ?> mapValue = (Map<?, ?>) value;

        if (size != null && mapValue.size() != size) {
            return false;
        }

        if (shape != null) {
            for (Map.Entry<String, BaseSchema<?>> entry : shape.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();

                if (!mapValue.containsKey(key)) {
                    return false;
                }
                Object mapEntryValue = mapValue.get(key);
                if (!isValid(schema, mapEntryValue)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isValid(BaseSchema<?> schema, Object value) {
        if (value == null) {
            return schema.isValid(null);
        }
        return schema.isValid(value);
    }
}
