package mc.alk.mc.plugin;

public class PluginDescription {

    private PluginProperties properties;

    protected PluginDescription(PluginProperties properties) {
        this.properties = properties;
    }

    public String getID() {
        return properties.id();
    }

    public String getName() {
        return properties.name();
    }

    public String getVersion() {
        return properties.version();
    }

    public String getDescription() {
        return properties.description();
    }

    public String getURL() {
        return properties.url();
    }

    public String[] getAuthors() {
        return properties.authors();
    }
}
