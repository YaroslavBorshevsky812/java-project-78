package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    private boolean required = false;

    public T required() {
        this.required = true;
        return (T) this;
    }

    public boolean isRequired() {
        return required;
    }

    public abstract boolean isValid(Object value);
}
