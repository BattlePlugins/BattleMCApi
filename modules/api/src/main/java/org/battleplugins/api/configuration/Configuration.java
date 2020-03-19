package org.battleplugins.api.configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Represents a file that contains persistable configuration
 * data that lasts through restarts and contains information
 * generally stored on disk.
 */
public class Configuration {

    private Path path;
    private ConfigurationProvider provider;

    private Configuration(Path path, Class<? extends ConfigurationProvider> providerClass) {
        if (Files.notExists(path)) {
            throw new IllegalArgumentException("Could not find file " + path.toString() + "!");
        }
        this.path = path;

        try {
            this.provider = providerClass.newInstance();
            this.provider.load(getReadableContents());
        } catch (InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * If a node is present at the given path
     *
     * @param path the configuration path
     * @return if a node is present at the given path
     */
    public boolean hasNode(String path) {
        return provider.configContents.containsKey(path);
    }

    /**
     * Gets the {@link ConfigurationNode} from the
     * given path
     *
     * @param path the configuration path
     * @return the configuration node from the given path
     */
    public ConfigurationNode getNode(String path) {
        return new ConfigurationNode(provider.configContents.get(path));
    }

    /**
     * Reloads the configuration contents from disk
     *
     * @throws IOException if the configuration could not be reloaded
     */
    public void reload() throws IOException {
        save();
        provider.load(getReadableContents());
    }

    /**
     * Saves the configuration contents
     *
     * @throws IOException if the file could not be saved
     */
    public void save() throws IOException {
        this.provider.save(path);
    }

    private String getReadableContents() {
        try (BufferedReader reader = new BufferedReader(Files.newBufferedReader(path))) {
            String temp;
            StringBuilder stringBuilder = new StringBuilder();
            temp = reader.readLine();
            while (temp != null) {
                if (stringBuilder.length() != 0) {
                    stringBuilder.append("\n");
                }
                stringBuilder.append(temp);
                temp = reader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return "";
    }

    /**
     * The config builder
     *
     * @return a new config builder
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Path path;
        private Class<? extends ConfigurationProvider> providerClass;

        private Builder() {
        }

        public Builder path(Path path) {
            this.path = path;
            return this;
        }

        public Builder provider(Class<? extends ConfigurationProvider> providerClass) {
            this.providerClass = providerClass;
            return this;
        }

        public Configuration build() {
            return new Configuration(path, providerClass);
        }
    }
}
