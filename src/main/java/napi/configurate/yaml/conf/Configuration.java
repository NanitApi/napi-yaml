package napi.configurate.yaml.conf;

import napi.configurate.yaml.AbstractConfiguration;
import napi.configurate.yaml.source.ConfigSource;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;

public class Configuration extends AbstractConfiguration {

    Configuration(ConfigSource source, YAMLConfigurationLoader loader) {
        super(source, loader);
    }

    /**
     * Get builder for configuration
     * @return Configuration builder
     */
    public static ConfigurationBuilder builder() {
        return new ConfigurationBuilder();
    }
}
