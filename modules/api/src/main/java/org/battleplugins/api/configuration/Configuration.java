package org.battleplugins.api.configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Represents a file that contains persistable configuration
 * data that lasts through restarts and contains information
 * generally stored on disk.
 */
public class Configuration {

    private File file;
    private ConfigurationProvider provider;

    private Configuration(File file, Class<? extends ConfigurationProvider> providerClass) {
        if (!file.exists())
            throw new IllegalArgumentException("Could not find file " + file + "!");

        this.file = file;

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
        this.provider.save(file);
    }

    private String getReadableContents() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
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

        private File file;
        private Class<? extends ConfigurationProvider> providerClass;

        private Builder() {
        }

        public Builder file(File file) {
            this.file = file;
            return this;
        }

        public Builder provider(Class<? extends ConfigurationProvider> providerClass) {
            this.providerClass = providerClass;
            return this;
        }

        public Configuration build() {
            return new Configuration(file, providerClass);
        }
    }
}
