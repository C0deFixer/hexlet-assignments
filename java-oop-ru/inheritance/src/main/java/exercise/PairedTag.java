package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class PairedTag extends Tag {
    String tagText;
    List<Tag> tagList;

    public PairedTag(String name, Map<String, String> atributs, String tagText, List<Tag> tagList) {
        super(name, atributs);
        this.tagText = tagText;
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        String subTagString = tagList.stream()
                .map(x -> x.toString())
                .collect(Collectors.joining());
        sb.append(subTagString);
        sb.append(tagText);
        sb.append("</");
        sb.append(name);
        sb.append(">");
        return sb.toString();
    }
}
// END
