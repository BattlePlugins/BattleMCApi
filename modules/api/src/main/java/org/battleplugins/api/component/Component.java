package org.battleplugins.api.component;

import java.util.Optional;

/**
 * Represents a component that can be applied
 * to various different objects.
 */
public interface Component<K, V> {

    /**
     * Applies the component with the given value
     * to the key
     *
     * @param key the key to apply the component to
     * @param value the value
     */
    void applyComponent(K key, V value);

    /**
     * Gets the value of this component, empty
     * if it is unable to be applied to the key
     * or a proper value could not be found
     *
     * @param key the key to get the value for
     * @return the value of this component
     */
    Optional<V> getValue(K key);

    /**
     * Gets if this component is able to be
     * applied to the given key
     *
     * @param key the key to check
     * @return if this component is appliable to the key
     */
    boolean isAppliable(K key);
}
