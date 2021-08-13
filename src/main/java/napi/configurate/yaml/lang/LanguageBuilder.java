package napi.configurate.yaml.lang;

import napi.configurate.yaml.source.ConfigSource;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;
import org.yaml.snakeyaml.DumperOptions;

public class LanguageBuilder {

    private ConfigSource source;
    private ConfigurationOptions options;
    private DumperOptions.FlowStyle flowStyle;
    private int indent;

    public LanguageBuilder() {
        options(ConfigurationOptions.defaults());
        flowStyle(DumperOptions.FlowStyle.FLOW);
        indent(2);
    }

    public LanguageBuilder source(ConfigSource source) {
        this.source = source;
        return this;
    }

    public LanguageBuilder options(ConfigurationOptions options) {
        this.options = options;
        return this;
    }

    public LanguageBuilder flowStyle(DumperOptions.FlowStyle flowStyle) {
        this.flowStyle = flowStyle;
        return this;
    }

    public LanguageBuilder indent(int indent) {
        this.indent = indent;
        return this;
    }

    public Language build() {
        if (source == null)
            throw new IllegalArgumentException("Missing language source");

        YAMLConfigurationLoader loader = YAMLConfigurationLoader.builder()
                .setSource(source::getReader)
                .setSink(source::getWriter)
                .setDefaultOptions(options)
                .setFlowStyle(flowStyle)
                .setIndent(indent)
                .build();

        return new Language(source, loader);
    }

}
