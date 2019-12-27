package org.battleplugins.api.configuration.provider;

import org.battleplugins.api.configuration.ConfigurationProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;

/**
 * Represents a {@link ConfigurationProvider} capable of parsing
 * and writing to .properties files.
 */
public class PropertiesConfigurationProvider extends ConfigurationProvider {

    @Override
    protected void load(String contents) {
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(contents));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        for (Map.Entry<Object, Object> entrySet : properties.entrySet()) {
            this.configContents.put(entrySet.getKey().toString(), entrySet.getValue());
        }
    }

    @Override
    protected void save(File file) throws IOException {
        Properties properties = new Properties();
        properties.putAll(configContents);
        properties.store(new FileOutputStream(file), null);
    }
}
