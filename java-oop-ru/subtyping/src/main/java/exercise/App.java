package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage kvs) {
        //for ( Map.Entry me: kvs) kvs.unset();
        Set<String> keySet = (Set<String>) kvs.toMap().entrySet().stream()
                .map(Entry::getKey).collect(Collectors.toSet());
        for (String key : keySet) {
            String value = kvs.get(key, "default");
            kvs.unset(key);
            kvs.set(value, key);
        }

    }

}
// END
