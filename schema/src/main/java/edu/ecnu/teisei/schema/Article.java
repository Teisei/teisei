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
     * ������ı�������һ�� XML �� Element
     * @return
     */
    public Element createElement();
}
