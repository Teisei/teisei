package edu.ecnu.teisei.schema;


import edu.ecnu.teisei.utils.FileUtil;

/**
 * 上传给人大用的文件格式
 * Created by dingcheng on 2014/7/17.
 */
public class MyData {

    //文件读写工具类
    static FileUtil fileTool = new FileUtil();

    public String id;
    public String title;
    public String keyword;
    public String description;
    public String type;
    public String format;
    public String size;
    public String date;

    public String identifier_path;
    public String identifier_id;

    public String source;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        if(format.contains(".pdf")||format.contains(".PDF")){
            System.out.println(".pdf");
            this.format = ".pdf";
        }else{
            this.format = format;
        }
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIdentifier_path() {
        return identifier_path;
    }

    public void setIdentifier_path(String identifier_path) {
        this.identifier_path = identifier_path;
    }

    public String getIdentifier_id() {
        return identifier_id;
    }

    public void setIdentifier_id(String identifier_id) {
        this.identifier_id = identifier_id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public MyData(String id, String title, String keyword, String description, String type, String format, String size, String date, String identifier_path, String identifier_id, String source) {
        this.id = id;
        this.title = title;
        this.keyword = keyword;
        this.description = description;
        this.type = type;
        this.format = format;
        this.size = size;
        this.date = date;
        this.identifier_path = identifier_path;
        this.identifier_id = identifier_id;
        this.source = source;
    }

    public void write2xml(String output){
        String temp = "";
        temp = "<metadata id = \""+getId()+"\">\n";
        temp+= "<title>"+getTitle()+"</title>\n";
        temp+= "<keyword>"+getKeyword()+"</keyword>\n";
        temp+= "<description>\n" +
                "<abstract>"+getDescription()+"\n" +
                "</abstract>\n" +
                "</description>\n";
        temp+= "<type>"+getType()+"</type>\n" +
                "<format>"+getFormat()+"</format>\n" +
                "<size>"+getSize()+"</size>\n";
        temp+= "<date>\n" +
                "<mdate>"+getDate()+"</mdate>\n" +
                "</date>\n";
        temp+= "<identifier platform=\"local\">\n" +
                "<path>"+getIdentifier_path()+"</path>\n" +
                "<id>"+getIdentifier_id()+"</id>\n" +
                "</identifier>\n";
        temp+= "<source>"+getSource()+"</source>\n";
        temp+= "<reference>none</reference>\n";
        temp+= "</metadata>\n";
        fileTool.write(output, temp, true, "utf-8");
    }
}
