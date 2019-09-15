package mc.alk.mc.util;

public class MCWrapper<T> {

    protected T handle;

    protected MCWrapper(T handle) {
        this.handle = handle;
    }

    public T getHandle() {
        return handle;
    }

    @Override
    public int hashCode() {
        return getHandle().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof MCWrapper) {
            return ((MCWrapper) o).getHandle().equals(getHandle());
        }

        return super.equals(o);
    }
}
