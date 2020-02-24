package org.battleplugins.api.plugin;

import mc.euro.version.Version;

import java.util.Optional;

/**
 * Description and information about the plugin
 */
public interface PluginDescription {

    /**
     * The id of the plugin
     *
     * @return the id of the plugin
     */
    String getId();

    /**
     * The name of the plugin
     *
     * @return the name of the plugin
     */
    String getName();

    /**
     * The {@link Version} of the plugin
     *
     * @return the version of the plugin
     */
    Version<Plugin> getVersion();

    /**
     * The description of the plugin
     *
     * @return the description of the plugin
     */
    Optional<String> getDescription();

    /**
     * The URL of the plugin
     *
     * @return the URL of the plugin
     */
    Optional<String> getURL();

    /**
     * The authors of the plugin
     *
     * @return the authors of the plugin
     */
    Optional<String[]> getAuthors();
}
