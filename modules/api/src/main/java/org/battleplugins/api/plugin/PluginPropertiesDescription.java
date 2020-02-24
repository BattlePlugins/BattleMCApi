package org.battleplugins.api.plugin;

import mc.euro.version.Version;

import java.util.Optional;

/**
 * Description and information about the plugin defined
 * in {@link PluginProperties}
 */
public class PluginPropertiesDescription implements PluginDescription {

    private PluginProperties properties;

    protected PluginPropertiesDescription(PluginProperties properties) {
        this.properties = properties;
    }

    /**
     * The id of the plugin
     *
     * @return the id of the plugin
     */
    @Override
    public String getId() {
        return properties.id();
    }

    /**
     * The name of the plugin
     *
     * @return the name of the plugin
     */
    @Override
    public String getName() {
        return properties.name();
    }

    /**
     * The {@link Version} of the plugin
     *
     * @return the version of the plugin
     */
    @Override
    public Version<Plugin> getVersion() {
        return new Version<>(properties.version());
    }

    /**
     * The description of the plugin
     *
     * @return the description of the plugin
     */
    @Override
    public Optional<String> getDescription() {
        return properties.description().isEmpty() ? Optional.empty() : Optional.of(properties.description());
    }

    /**
     * The URL of the plugin
     *
     * @return the URL of the plugin
     */
    @Override
    public Optional<String> getURL() {
        return properties.url().isEmpty() ? Optional.empty() : Optional.of(properties.url());
    }

    /**
     * The authors of the plugin
     *
     * @return the authors of the plugin
     */
    @Override
    public Optional<String[]> getAuthors() {
        return properties.authors().length == 0 ? Optional.empty() : Optional.of(properties.authors());
    }
}
