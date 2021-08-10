package napi.configurate.yaml;

import com.google.common.reflect.TypeToken;
import napi.configurate.yaml.source.ConfigSource;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;
import org.yaml.snakeyaml.DumperOptions;

public class ConfigurationBuilder {

    private ConfigSource source;
    private ConfigurationOptions options;
    private DumperOptions.FlowStyle flowStyle;
    private int indent;

    public ConfigurationBuilder() {
        options(ConfigurationOptions.defaults());
        flowStyle(DumperOptions.FlowStyle.FLOW);
        indent(2);
    }

    public ConfigurationBuilder source(ConfigSource source) {
        this.source = source;
        return this;
    }

    public ConfigurationBuilder options(ConfigurationOptions options) {
        this.options = options;
        return this;
    }

    public ConfigurationBuilder flowStyle(DumperOptions.FlowStyle flowStyle) {
        this.flowStyle = flowStyle;
        return this;
    }

    public ConfigurationBuilder indent(int indent) {
        this.indent = indent;
        return this;
    }

    public <T> ConfigurationBuilder serializer(Class<T> type, TypeSerializer<? super T> serializer) {
        return serializer(TypeToken.of(type), serializer);
    }

    public <T> ConfigurationBuilder serializer(TypeToken<T> type, TypeSerializer<? super T> serializer) {
        options.getSerializers().register(type, serializer);
        return this;
    }

    public Configuration build() {
        if (source == null)
            throw new IllegalArgumentException("Missing configuration source");

        YAMLConfigurationLoader loader = YAMLConfigurationLoader.builder()
                .setSource(source::getReader)
                .setSink(source::getWriter)
                .setDefaultOptions(options)
                .setFlowStyle(flowStyle)
                .setIndent(indent)
                .build();

        return new Configuration(source, loader);
    }

}
