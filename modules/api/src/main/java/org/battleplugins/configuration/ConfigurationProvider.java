package org.battleplugins.configuration;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
     * @param file the file to save
     * @throws IOException if the file could not be saved
     */
    protected abstract void save(File file) throws IOException;

    protected void write(File file, String content) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
        try (FileOutputStream stream = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                stream.write(buffer, 0, length);
            }
        }
        inputStream.close();
    }
}
