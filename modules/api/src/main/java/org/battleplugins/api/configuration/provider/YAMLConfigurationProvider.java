package org.battleplugins.api.configuration.provider;

import org.battleplugins.api.configuration.ConfigurationProvider;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Represents a {@link ConfigurationProvider} capable of parsing
 * and writing to YAML files.
 */
public class YAMLConfigurationProvider extends ConfigurationProvider {

    @Override
    protected void load(String contents) {
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(dumperOptions);
        this.configContents = yaml.loadAs(contents, LinkedHashMap.class);
    }

    @Override
    protected void save(File file) throws IOException {
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(dumperOptions);
        String content = yaml.dump(configContents);
        this.write(file, content);
    }
}
