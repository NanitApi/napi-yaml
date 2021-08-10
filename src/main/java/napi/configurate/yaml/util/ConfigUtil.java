package napi.configurate.yaml.util;

import ninja.leaping.configurate.ConfigurationNode;

import java.util.Map;

public final class ConfigUtil {

    private ConfigUtil() { }

    /**
     * Map all values in node to Java native types
     * @param node Configuration node
     * @return Map with values
     */
    public static Map<String, Object> mapValues(ConfigurationNode node) {
        return new NodeMapper().map(node);
    }

}
