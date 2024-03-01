package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {
    HashMap<String, String> map;

    public InMemoryKV(Map<String, String> map) {
        this.map = new HashMap<>();
        this.map.putAll(map);
        //shallow copy
        //this.map = map.entrySet().stream()
        //        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
    }

    @Override
    public void set(String key, String value) {
        this.map.put(key, value);
    }

    @Override
    public void unset(String key) {
        this.map.remove(key, map.get(key));
    }

    @Override
    public String get(String key, String defaultValue) {
        return map.containsKey(key) ? map.get(key) : defaultValue;
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(map);
    }
}
// END
