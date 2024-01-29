package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
class Tag {
    protected String name;
    protected Map<String, String> atributs;

    public Tag(String name, Map<String, String> atributs) {
        this.name = name;
        this.atributs = atributs;
    }

    protected String getAtributeString() {
        String atributsString = atributs.entrySet().stream()
                .map(x -> " " + x.getKey() + "=\"" + x.getValue() + "\"")
                .collect(Collectors.joining());
        return atributsString;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("<");
        sb.append(name);
        sb.append(this.getAtributeString());
        sb.append(">");
        return sb.toString();
    }
}
// END
