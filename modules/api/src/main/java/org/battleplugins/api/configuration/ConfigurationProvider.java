package org.battleplugins.api.configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;

/**
 * Represents a provider capable of managing configuration
 * files.
 */
public abstract class ConfigurationProvider {

    protected LinkedHashMap<String, Object> configContents = new LinkedHashMap<>();

    protected ConfigurationProvider() {
    }

    /**
     * Loads the configuration contents from the given
     * file contents as a string
     *
     * @param contents the file contents
     */
    protected abstract void load(String contents);

    /**
     * Saves the configuration contents
     *
     * @param path the file to save
     * @throws IOException if the file could not be saved
     */
    protected abstract void save(Path path) throws IOException;

    protected void write(Path path, String content) throws IOException {
        Files.write(path, content.getBytes());
    }
}
