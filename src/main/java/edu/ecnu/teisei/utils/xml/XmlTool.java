package edu.ecnu.teisei.utils.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理xml工具类
 * @author dingcheng
 *
 */
public class XmlTool {
    //element标签库
    static List<String> elements;

    static Map<String, String> TagMap;

    public XmlTool(){

    }

    public static void loadElems(){
        elements = new ArrayList<String>();
        //21世纪新闻、网易公司新闻、sina新闻
        elements.add("businessnews");//0
        elements.add("news");//1
        elements.add("url");
        elements.add("title");
        elements.add("time");
        elements.add("plate");
        elements.add("source");
        elements.add("text");
        elements.add("sentence");
        //网易公司公告
        elements.add("公司公告");//0
        elements.add("公告");//1
        elements.add("公告标题");
        elements.add("公告url");
        elements.add("公布时间");
        elements.add("公告类型");
        elements.add("pdf文件名");
        elements.add("公告正文");
    }

    public static void loadTagMap(){
        TagMap=new HashMap<String, String>();
        TagMap.put("businessnews", "metadatas");
        TagMap.put("news", "metadata");
        TagMap.put("url", "source");
        TagMap.put("plate", "contributor");
        TagMap.put("text", "description");
        TagMap.put("time", "date");
        TagMap.put("title","title");


        TagMap.put("companies","metadatas");
        TagMap.put("company","metadata");
        TagMap.put("name","title");
        TagMap.put("website","source");
        TagMap.put("bussiness","description");
        TagMap.put("URL","source");
        TagMap.put("model","keyword");
        TagMap.put("organization","keyword");
        TagMap.put("contact-name","contributor");
        TagMap.put("website","reference");
        TagMap.put("id","title");
        TagMap.put("info","description");


        TagMap.put("公司公告","metadatas");
        TagMap.put("公告","metadata");
        TagMap.put("公告标题","title");
        TagMap.put("公告url","source");
        TagMap.put("公布时间","date");
        TagMap.put("pdf文件名","format");
        TagMap.put("公告正文","description");

        TagMap.put("companyInfo","metadatas");
        TagMap.put("公司","metadata");
        TagMap.put("链接","source");
        TagMap.put("企业类型","type");
        TagMap.put("经营模式","keyword");
        TagMap.put("法定代表人","contributor");
        TagMap.put("联系人姓名","contributor");
        TagMap.put("主营产品","description");


    }

    /**   增加一个标签转换规则  */
    public static String addTagMap(String from, String to) {
        TagMap.put(from, to);
        return "";
    }

    /**  删除一个标签转换规则 */
    public static String delTagMap(String from){
        TagMap.remove(from);
        return "";
    }

    public static String mapElem(String tag){
        return TagMap.get(tag);
		/*
        if(tag.contains("标题")){
			return "title";
		}else if(tag.contains("正文")||tag.contains("content")){
			return "text";
		}else if(tag.contains("url")||tag.contains("URL")||tag.contains("链接")||tag.contains("Url")){
			return "url";
		}else if(tag.contains("时间")){
			return "time";
		}else if(tag.contains("类型")||tag.contains("type")){
			return "plate";
		}
		return tag;*/
    }
}
