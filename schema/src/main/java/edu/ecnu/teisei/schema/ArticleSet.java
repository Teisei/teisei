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
     * ��Ӧ��xml�ļ��ĸ��ڵ�
     */
    public String rootTag = "";

    public String getRootTag() {
        return rootTag;
    }

    public void setRootTag(String rootTag) {
        this.rootTag = rootTag;
    }

    /**
     * ��������
     */
    public List<Article> list = new ArrayList<Article>();

    public ArticleSet() {
        list = new ArrayList<Article>();
    }

    /**
     * ����һƪ����
     * @param aNews
     */
    public void addOneNews(Article aNews) {
        list.add(aNews);
    }

    /**
     * ����һ�����ż��ϵ�xml tree
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
