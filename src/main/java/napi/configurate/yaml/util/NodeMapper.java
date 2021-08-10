package napi.configurate.yaml.util;

import ninja.leaping.configurate.ConfigurationNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeMapper {

    public Map<String, Object> map(ConfigurationNode node) {
        Map<String, Object> map = createMap();
        Object value = mapElement(node);

        if (value instanceof Map) {
            map.putAll((Map<String, Object>) value);
        } else {
            map.put(node.getKey().toString(), value);
        }

        return map;
    }

    private Object mapElement(ConfigurationNode node) {
        Object element;

        if (node.isMap()) {
            element = mapObject(node);
        } else if (node.isList()) {
            element = mapList(node);
        } else {
            element = mapScalar(node);
        }

        return element;
    }

    private Object mapScalar(ConfigurationNode node) {
        return node.getValue();
    }

    private Map<String, Object> mapObject(ConfigurationNode node) {
        Map<Object, ? extends ConfigurationNode> children = node.getChildrenMap();
        Map<String, Object> map = createMap();

        for (Map.Entry<Object, ? extends ConfigurationNode> entry : children.entrySet()){
            String key = entry.getKey().toString();
            map.put(key, mapElement(entry.getValue()));
        }

        return map;
    }

    private List<Object> mapList(ConfigurationNode node) {
        List<? extends ConfigurationNode> children = node.getChildrenList();
        List<Object> list = new ArrayList<>();

        for (ConfigurationNode elem : children){
            list.add(mapElement(elem));
        }

        return list;
    }

    private Map<String, Object> createMap() {
        return new HashMap<>();
    }

}
