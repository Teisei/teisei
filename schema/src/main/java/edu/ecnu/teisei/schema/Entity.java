package edu.ecnu.teisei.schema;

/**
 * �������װ��һ��ʵ������ԡ�
 *
 * @author ansj
 *
 */
public class Entity implements Knowledge{
    //ʵ�������
    public String title;
    //ʵ���url���൱��id
    public String url;
    //ʵ�������
    public String desc;

    //�����
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