package napi.configurate.yaml.lang;

import napi.configurate.yaml.source.ConfigSource;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;
import org.yaml.snakeyaml.DumperOptions;

import java.util.function.Function;

public class LanguageBuilder {

    private ConfigSource source;
    private ConfigurationOptions options;
    private DumperOptions.FlowStyle flowStyle;
    private int indent;
    private Function<String, String> valueModifier;

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

    public LanguageBuilder valueModifier(Function<String, String> valueModifier) {
        this.valueModifier = valueModifier;
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

        return new Language(source, loader, valueModifier);
    }

}
