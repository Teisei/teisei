package edu.ecnu.teisei.schema.news;

import edu.ecnu.teisei.schema.Article;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dingcheng on 2014/11/3.
 */
public class BigDataNewsSet {
    public List<Article> newsList;

    public BigDataNewsSet() {
        newsList = new ArrayList<Article>();
    }

    /**
     * 增加一篇新闻
     * @param aNews
     */
    public void addOneNews(BigDataNews aNews) {
        newsList.add(aNews);
    }
    public void addOneNews(Article aNews) {
        newsList.add(aNews);
    }

    /**
     * 创建一个新闻集合的xml tree
     * @return
     */
    public Element createXmlTree(){
        Element root = DocumentHelper.createElement("NewsList");

        int id = 0;
        for(Article aNews: newsList){
            Element tNews = aNews.createElement();
            tNews.addAttribute("id", "" + id++);
            root.add(tNews);
        }
        root.addAttribute("number", ""+id);

        return root;
    }
}
