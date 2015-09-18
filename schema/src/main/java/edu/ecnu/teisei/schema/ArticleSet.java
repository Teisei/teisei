package edu.ecnu.teisei.schema;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dingcheng on 2014/11/10.
 */
public class ArticleSet {
    /**
     * 对应的xml文件的根节点
     */
    public String rootTag = "";

    public String getRootTag() {
        return rootTag;
    }

    public void setRootTag(String rootTag) {
        this.rootTag = rootTag;
    }

    /**
     * 新闻数组
     */
    public List<Article> list = new ArrayList<Article>();

    public ArticleSet() {
        list = new ArrayList<Article>();
    }

    /**
     * 增加一篇新闻
     * @param aNews
     */
    public void addOneNews(Article aNews) {
        list.add(aNews);
    }

    /**
     * 创建一个新闻集合的xml tree
     * @return
     */
    public Element createXmlTree() {
        return createXmlTree(getRootTag());
    }
    public Element createXmlTree(String rootTag){
        Element root = DocumentHelper.createElement(rootTag);

        int id = 0;
        for(Article aNews: list){
            Element tNews = aNews.createElement();
            tNews.addAttribute("id", "" + id++);
            root.add(tNews);
        }
        root.addAttribute("number", ""+id);

        return root;
    }
}
