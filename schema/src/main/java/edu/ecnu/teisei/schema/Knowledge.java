package edu.ecnu.teisei.schema;

/**
 * Created by dingcheng on 2014/12/26.
 */
public interface Knowledge {
//    public String title = null;
//    public String url = null;
//    public String desc = null;


    public void setTitleUrlDesc(String title, String url, String desc);

    public void setTitle(String title);

    public void setUrl(String url);

    public void setDesc(String desc);
}
