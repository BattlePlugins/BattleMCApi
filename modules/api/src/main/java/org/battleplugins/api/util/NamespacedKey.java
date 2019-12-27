package org.battleplugins.api.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import org.battleplugins.api.plugin.Plugin;

import java.util.regex.Pattern;

/**
 * Represents a String based key which consists of two components -
 * a namespace and a key.
 *
 * Namespaces may only contain lowercase alphanumeric characters,
 * periods underscores, and hyphens. Keys may only contain lowercase
 * alphanumeric characters, periods, underscores, hyphens, and forward
 * slashes.
 */
@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class NamespacedKey {

    public static final String MINECRAFT = "minecraft";

    private static final Pattern VALID_NAMESPACE = Pattern.compile("[a-z0-9._-]+");
    private static final Pattern VALID_KEY = Pattern.compile("[a-z0-9/._-]+");

    private final String namespace;
    private final String key;

    /**
     * Gets a key in the Minecraft namespace
     *
     * @param key the key to use
     * @return a new key in the Minecraft namespace
     */
    public static NamespacedKey minecraft(String key) {
        return of(MINECRAFT, key);
    }

    /**
     * Creates a namespace with the given plugin
     *
     * @param plugin the plugin to create the namespace with
     * @param key the key
     * @return a namespace with the given plugin
     */
    public static NamespacedKey of(Plugin plugin, String key) {
        return of(plugin.getDescription().getName(), key);
    }

    /**
     * Creates a namespace from the given string.
     * Defaults to {@link #minecraft(String)} if
     * a valid namespace is not provided
     *
     * @param string the string to get the namespace from
     * @return a namespace from the given string
     */
    public static NamespacedKey of(String string) {
        String[] split = string.split(":");
        if (split.length < 2)
            return minecraft(string);

        return NamespacedKey.of(split[0], split[1]);
    }

    /**
     * Creates a namespace with the given namespace
     *
     * @param namespace the namespace
     * @param key the key
     * @return a namespace with the given plugin
     */
    public static NamespacedKey of(String namespace, String key) {
        namespace = namespace.toLowerCase();
        key = key.toLowerCase();

        if (!VALID_NAMESPACE.matcher(namespace).matches())
            throw new IllegalArgumentException("Invalid namespace. Must be [a-z0-9._-]: " + namespace);

        if (!VALID_KEY.matcher(key).matches())
            throw new IllegalArgumentException("Invalid key. Must be [a-z0-9/._-]: " + key);

        return new NamespacedKey(namespace, key);
    }

    @Override
    public String toString() {
        return this.namespace + ":" + this.key;
    }
}
