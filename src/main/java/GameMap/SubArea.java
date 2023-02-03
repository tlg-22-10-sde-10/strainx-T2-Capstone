package GameMap;

public class SubArea {
    private String name = "Laboratory";

    private String description = "";

    private Content contents = new Content();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Content getContents() {
        return contents;
    }

    public void setContents(Content contents) {
        this.contents = contents;
    }
}