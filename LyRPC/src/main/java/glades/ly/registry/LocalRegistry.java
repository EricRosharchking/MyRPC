package glades.ly.registry;

import java.util.HashMap;
import java.util.Map;

public class LocalRegistry {

    private static Map<String, Class<?>> map = new HashMap<>();

    public static void register(String interfaceName, Class<?> implClass) {
        map.put(interfaceName, implClass);
    }

    // add version of the interface/method
    public static Class<?> get(String interfaceName) {
        return map.get(interfaceName);
    }
}
