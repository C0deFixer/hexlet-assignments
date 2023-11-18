package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private String textLabel;
    private TagInterface tag;

    public LabelTag(String textLabel, TagInterface tag) {
        this.textLabel = textLabel;
        this.tag = tag;
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        sb.append("<label>");
        sb.append(this.textLabel);
        sb.append(tag.render());
        sb.append("</label>");
        return sb.toString();
    }
}
// END
