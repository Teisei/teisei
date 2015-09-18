package edu.ecnu.teisei.schema;

import org.dom4j.Element;

/**
 * Created by dingcheng on 2014/11/3.
 */
public interface Article {

    public String getRootTag();
    public String getTitle();
    public String getContent();
    /**
     * 将这个文本创建成一个 XML 的 Element
     * @return
     */
    public Element createElement();
}
