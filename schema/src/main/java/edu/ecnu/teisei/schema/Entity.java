package edu.ecnu.teisei.schema;

/**
 * 这里面封装了一个实体的特性。
 *
 * @author ansj
 *
 */
public class Entity implements Knowledge{
    //实体的名称
    public String title;
    //实体的url，相当于id
    public String url;
    //实体的描述
    public String desc;

    //这个词
    public int n_gram;

    public int getN_gram() {
        return n_gram;
    }

    public void setN_gram(int n_gram) {
        this.n_gram = n_gram;
    }

    public Entity() {

    }

    public Entity(String title, String url, String desc) {
        this.title = title;
        this.url = url;
        this.desc = desc;
    }

    public Entity(String title, String url) {
        this.title = title;
        this.url = url;
        this.desc = "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTitleUrlDesc(String title, String url, String desc) {
        this.title = title;
        this.url = url;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}