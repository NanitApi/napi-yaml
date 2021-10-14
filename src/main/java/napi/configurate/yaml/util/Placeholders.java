package napi.configurate.yaml.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class Placeholders {

    private Placeholders() { }

    /**
     * Replace placeholders.
     * @param message Raw string
     * @param values Placeholders. Each even element is a placeholder and odd element is a value.
     * @return String with replaced placeholders
     */
    public static String replace(String message, Object... values) {
        if (message == null) return null;

        String msg = message;

        for (int i = 0; i < values.length; i+=2) {
            msg = msg.replace("%" + values[i] + "%", values[i+1].toString());
        }

        return msg;
    }

    public static List<String> replace(List<String> list, Object... values) {
        if (list == null) return Collections.emptyList();
        return list.stream().map((elem)->replace(elem, values)).collect(Collectors.toList());
    }

}