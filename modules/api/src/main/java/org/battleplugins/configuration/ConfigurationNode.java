package org.battleplugins.configuration;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

/**
 * Represents a node inside of a configuration file.
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class ConfigurationNode {

    /**
     * The value of this configuration node
     *
     * @return the value of this configuration node
     */
    private Object value;

    /**
     * Gets the value of this configuration node with the
     * given class as the return type
     *
     * @param clazz the class
     * @param <T> the class type
     * @return the value of this node with the given return type
     */
    public <T> T getValue(Class<T> clazz) {
        return clazz.cast(value);
    }

    /**
     * Gets the value of this configuration type. Returns
     * the supplied default value if null
     *
     * @param defaultValue the default value
     * @param <T> the value
     * @return the value of this configuration type
     */
    public <T> T getValue(T defaultValue) {
        return (T) (value != null ? value : defaultValue);
    }

    /**
     * Gets the value of this configuration node with a
     * collection as the return type
     *
     * @param keyClazz the key class
     * @param <K> the key class type
     * @return the value of this node with the given return type
     * @throws IllegalArgumentException if the value is not assignable from a list
     */
    public <K> Collection<K> getCollectionValue(Class<K> keyClazz) throws IllegalArgumentException {
        if (Collection.class.isAssignableFrom(value.getClass()))
            return (Collection<K>) value;

        // Get the keySet here
        if (Map.class.isAssignableFrom(value.getClass())) {
            return ((Map<K, ?>) value).keySet();
        }

        throw new IllegalArgumentException(keyClazz + " is not assignable from a list!");
    }

    /**
     * If the value has more nodes associated with it
     *
     * @return if the value has more nodes associated with it
     */
    public boolean hasChildNodes() {
        return value instanceof LinkedHashMap;
    }

    /**
     * Gets the {@link ConfigurationNode} from the
     * given path
     *
     * @param path the configuration path
     * @return the configuration node from the given path
     * @throws IllegalArgumentException if there are no more nodes
     */
    public ConfigurationNode getNode(String path) throws IllegalArgumentException {
        if (value instanceof LinkedHashMap) {
            return new ConfigurationNode(((LinkedHashMap) value).get(path));
        }
        throw new IllegalArgumentException("There are no more nodes at " + path + "!");
    }
}
