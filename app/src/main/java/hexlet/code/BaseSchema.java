package hexlet.code;

abstract class BaseSchema<T, V> {
    private boolean required = false;

    public T required() {
        this.required = true;
        return (T) this;
    }

    public boolean isRequired() {
        return required;
    }

    public abstract boolean isValid(V value);
}
