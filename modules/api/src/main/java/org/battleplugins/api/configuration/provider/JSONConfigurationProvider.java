package org.battleplugins.api.configuration.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.battleplugins.api.configuration.ConfigurationProvider;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;

/**
 * Represents a {@link ConfigurationProvider} capable of parsing
 * and writing to JSON files.
 */
public class JSONConfigurationProvider extends ConfigurationProvider {

    @Override
    protected void load(String contents) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        this.configContents = gson.fromJson(contents, new TypeToken<LinkedHashMap<String, Object>>() {}.getType());
    }

    @Override
    protected void save(Path path) throws IOException {
        this.write(path, new GsonBuilder().setPrettyPrinting().create().toJson(configContents));
    }
}
