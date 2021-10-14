package napi.configurate.yaml.lang;

import com.google.common.reflect.TypeToken;
import napi.configurate.yaml.AbstractConfiguration;
import napi.configurate.yaml.source.ConfigSource;
import napi.configurate.yaml.util.Placeholders;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Language extends AbstractConfiguration {

    private final Map<String, String> keys;
    private final Map<String, List<String>> lists;
    private final Function<String, String> valueModifier;

    Language(ConfigSource source, YAMLConfigurationLoader loader, Function<String, String> valueModifier) {
        super(source, loader);
        keys = new HashMap<>();
        lists = new HashMap<>();
        if (valueModifier == null) {
            this.valueModifier = (s) -> s;
        } else {
            this.valueModifier = valueModifier;
        }
    }

    public String of(String key) {
        return of(key, key);
    }

    public String of(String key, String def) {
        return keys.getOrDefault(key, def);
    }

    public String of(String key, Object... args) {
        return of(key, key, args);
    }

    public String of(String key, String def, Object... args) {
        return Placeholders.replace(of(key, def), args);
    }

    public List<String> ofList(String key) {
       return ofList(key, Collections.singletonList(key));
    }

    public List<String> ofList(String key, List<String> def) {
        return lists.getOrDefault(key, def);
    }

    public List<String> ofList(String key, Object... args) {
        return ofList(key, Collections.singletonList(key), args);
    }

    public List<String> ofList(String key, List<String> def, Object... args) {
        return Placeholders.replace(ofList(key, def), args);
    }

    @Override
    public void reload() throws IOException {
        super.reload();

        try {
            loadKeys();
        } catch (ObjectMappingException e) {
            throw new IOException(e);
        }
    }

    private void loadKeys() throws ObjectMappingException {
        for (Map.Entry<Object, ? extends ConfigurationNode> entry : getChildrenMap().entrySet()) {
            String key = entry.getKey().toString();
            if (entry.getValue().isList()) {
                List<String> list = entry.getValue().getList(TypeToken.of(String.class));
                lists.put(key, list.stream()
                        .map(valueModifier)
                        .collect(Collectors.toList()));
            } else {
                String str = entry.getValue().getString();
                keys.put(key, valueModifier.apply(str));
            }
        }
    }

    public static LanguageBuilder builder() {
        return new LanguageBuilder();
    }
}
