package napi.configurate.yaml;

import napi.configurate.yaml.source.ConfigSource;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;

import java.io.IOException;

public class Configuration extends AbstractConfiguration {

    Configuration(ConfigSource source, YAMLConfigurationLoader loader) {
        super(source, loader);
    }

    /**
     * Load config from file
     * @throws IOException if something wrong while loading
     */
    public void reload() throws IOException {
        root = loader.load();
    }

    /**
     * Save config to file
     * @throws IOException if something wrong while saving
     */
    public void save() throws IOException {
        loader.save(root);
    }

    /**
     * Add missing fields from specified configuration file and save result.
     * @param source Configuration source to merge
     * @throws IOException if something wrong while loading or saving
     */
    public void merge(ConfigSource source) throws IOException {
        Configuration conf = Configuration.builder()
                .source(source)
                .build();

        conf.reload();
        mergeValuesFrom(conf);
        save();
    }

    /**
     * Get builder for configuration
     * @return Configuration builder
     */
    public static ConfigurationBuilder builder() {
        return new ConfigurationBuilder();
    }
}
