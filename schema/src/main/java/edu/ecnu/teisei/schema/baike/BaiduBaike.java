package edu.ecnu.teisei.schema.baike;

import edu.ecnu.teisei.schema.Knowledge;
import org.dom4j.Element;

/**
 * Created by dingcheng on 2014/12/24.
 */
public class BaiduBaike implements Knowledge{
    public String title;    //标题
    public String url;  //url，当做id用
    public String desc; //description，描述信息

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public BaiduBaike(String title, String url, String desc) {
        this.title = title;
        this.url = url;
        this.desc = desc;
    }

    public BaiduBaike(Element element) {
        this.title = element.elementText("title");
        this.url = element.elementText("url");
        this.desc = element.elementText("desc");
    }
    public void setTitleUrlDesc(String title, String url, String desc) {
        this.title = title;
        this.url = url;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "BaiduBaike{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
