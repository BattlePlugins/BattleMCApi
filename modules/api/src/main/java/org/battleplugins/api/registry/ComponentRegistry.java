package org.battleplugins.api.registry;

import org.battleplugins.api.component.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * A registry containing {@link Component}s.
 *
 * @param <T> the component value
 */
public class ComponentRegistry<T extends Component<?, ?>> implements BaseRegistry {

    private Map<Class<? extends T>, T> components = new HashMap<>();
    private boolean closed;

    /**
     * Gets a component instance from
     * the given component class
     *
     * @param componentClass the component class
     * @return a component instance from the given class
     */
    public <U extends T> U getComponent(Class<U> componentClass) {
        if (!components.containsKey(componentClass)) {
            throw new IllegalArgumentException("Component class " + componentClass + " was not registered for " + this.getClass().getSimpleName() + "!");
        }
        return componentClass.cast(components.get(componentClass));
    }

    /**
     * Registers a component into the registry
     *
     * @param componentClass the component class
     * @param component the component
     */
    public <V extends U, U extends T> void registerComponent(Class<U> componentClass, V component) {
        components.put(componentClass, component);
    }

    @Override
    public boolean isClosed() {
        return this.closed;
    }

    @Override
    public void close() {
        this.closed = true;
    }
}
