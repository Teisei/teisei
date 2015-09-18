package edu.ecnu.teisei.schema.baike;

/**
 * Created by dingcheng on 2014/12/24.
 */
public class SimpleBaike {
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String title;
    public String description;
    public String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SimpleBaike(String title, String description, String content) {
        this.title = title;
        this.description = description;
        this.content = content;
    }

    public SimpleBaike(int id, String title, String description, String content) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
    }

    @Override
    public String toString() {
        return "SimpleBaike{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
