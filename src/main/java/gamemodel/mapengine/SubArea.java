package gamemodel.mapengine;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubArea {
    private String name;

    private String description = "";

    @JsonProperty("reusability")
    private int reUseCap;

    private Boolean visited = false;

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

    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public int getReUseCap() {
        return reUseCap;
    }

    public void setReUseCap(int reUseCap) {
        this.reUseCap = reUseCap;
    }
}