package exercise;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

// BEGIN
class SingleTag extends Tag {
    public SingleTag(String name, Map<String, String> atributs) {
        super(name, atributs);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
// END
