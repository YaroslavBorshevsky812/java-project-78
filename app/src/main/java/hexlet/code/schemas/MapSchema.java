package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<MapSchema, Map<?, ?>> {
    private Integer size;
    private Map<String, BaseSchema<?, ?>> shape;

    public MapSchema sizeof(Integer newSize) {
        this.size = newSize;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<?, ?>> newShape) {
        this.shape = newShape;
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
            for (Map.Entry<String, BaseSchema<?, ?>> entry : shape.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?, ?> schema = entry.getValue();

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

    private <V> boolean isValid(BaseSchema<?, V> schema, Object value) {
        if (value == null) {
            return schema.isValid(null);
        }
        return schema.isValid((V) value);
    }
}
