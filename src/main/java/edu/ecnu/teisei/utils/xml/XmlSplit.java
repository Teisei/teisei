package edu.ecnu.teisei.utils.xml;

import edu.ecnu.teisei.utils.FileUtil;
import edu.ecnu.teisei.utils.XmlUtil;
import org.dom4j.Element;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 这个类用来分隔比较大的 xml 文件。
 * Created by dingcheng on 2014/12/24.
 */
public class XmlSplit {

    /**
     * 根节点
     */
    static String rootTag = "data";
    /**
     * xml 每个元文件的 tag
     */
    static String eachTag = "page";
    /**
     * 一共的xml数量
     */
    static int number = 0;

    /**
     * 需要分成文件的数量
     */
    static int file_number = 1;


    /**
     * 输出的xml文件的路径
     */
    static String output_path[];

    /**
     * 输出的文件夹
     */
    static String outputDir;

    /**
     * 读写xml文件工具类
     */
    static XmlUtil xmlUtil = new XmlUtil();
    static FileUtil fileUtil = new FileUtil();




    public static String getEachTag() {
        return eachTag;
    }

    public static void setEachTag(String eachTag) {
        XmlSplit.eachTag = eachTag;
    }

    class XmlCountHandler extends DefaultHandler {

        /**
         * 每次开始一个document
         */
        public void startDocument() throws SAXException {
            number = 0;
        }
        /**
         * 每次结束一个document
         */
        public void endDocument() throws SAXException {
        }
        /**
         * 每次遇到一个元素的开始元素<start>，localName=tag
         */
        public void startElement(String namespaceURI, String localName,
                                 String qName, Attributes attr) throws SAXException {
            if (localName.equals(getEachTag())) {
                number++;
            }

        }

        public void endElement(String namespaceURI, String localName, String qName)
                throws SAXException {
        }
        /**
         * 读取一个元素的text
         */
        public void characters(char[] ch, int start, int length)
                throws SAXException {
//            try {
//                System.out.println("text:"+new String(ch,start,length));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }

        /** 解析XML */
        public int opXML(String inputpath,String tag,String encode){
            System.out.println("XmlCounter:");
            try {
                setEachTag(tag);

                XMLReader xr = XMLReaderFactory
                        .createXMLReader("org.apache.xerces.parsers.SAXParser");

                xr.setContentHandler(new XmlCountHandler());
                xr.parse(new InputSource(new InputStreamReader(
                        new FileInputStream(new File(inputpath)), encode)));

            } catch (Exception e) {
                e.printStackTrace();
            }
            return number;
        }
    }




    /**
     * 切分xml文件的类
     */
    class XmlSplitHandler extends DefaultHandler {
        String temp_text = "";

        boolean inEachTag = false;

        int _index_of_number_ = 0;
        Element docRoot = null;

        /**
         * 当前写文件的编号
         */
        int _now_file_index_ = -1;

        String _temp_content_ = null;

        /**
         * 每次开始一个document
         */
        public void startDocument() throws SAXException {
            System.out.println("SAX Event: START DOCUMENT");
//            docRoot = xmlUtil.createElement(rootTag);
            _temp_content_ = "";
            _now_file_index_++;
        }
        /**
         * 每次结束一个document
         */
        public void endDocument() throws SAXException {
            System.out.println("SAX Event: END DOCUMENT");
            try {
                writeToFile(output_path[_now_file_index_], "utf-8");

//                xmlUtil.writeXMLDom(docRoot, output_path[_now_file_index_], "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        /**
         * 每次遇到一个元素的开始元素<start>，localName=tag
         */
        public void startElement(String namespaceURI, String localName,
                                 String qName, Attributes attr) throws SAXException {
            if (localName.equals(rootTag)) {
//                System.out.println("SAX Event: START ELEMENT[ " + localName + " ]");
            }
            if (localName.equals(eachTag)) {
//                System.out.println("SAX Event: START ELEMENT[ " + localName + " ]");
                temp_text = "";
                inEachTag = true;
            } else {
                temp_text += "<" + localName + ">";
            }


        }

        public void endElement(String namespaceURI, String localName, String qName)
                throws SAXException {
            if (localName.equals(rootTag)) {
//                System.out.println("SAX Event: END ELEMENT[ " + localName + " ]");
            }else if (localName.equals(eachTag)) {

//                System.out.println("text:"+temp_text);
//                System.out.println("SAX Event: END ELEMENT[ " + localName + " ]");

                //写入xml文件, 写入的xml文件的编号
                int _file_number_ = _index_of_number_ / (number / file_number);
//                System.out.println("$$$$$$$$$$$$$$ should be write the file: No." + _file_number_ + "  now_file_index = " + _now_file_index_);

                /**
                 * 如果应该写入下一个xml文件，则创建下一个。
                 */
                if (_now_file_index_ < _file_number_) {

                    //将上次的结果写入 文件
                    try {
                        System.out.println("\n\n\n\n$$ create another xml file $$\n\n");
                        writeToFile(output_path[_now_file_index_], "utf-8");
//                        xmlUtil.writeXMLDom(docRoot, output_path[_now_file_index_], "utf-8");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    docRoot = xmlUtil.createElement(rootTag);

                    _now_file_index_++;
                    _temp_content_ = "";
                }

                /**
                 * 将合并的 xml text 加入到doc中。
                 */
//                docRoot.addElement(eachTag).setText(temp_text);
                _temp_content_ += "<" + eachTag + ">";
                _temp_content_ += temp_text + "\r\n";
                _temp_content_ += "</" + eachTag + ">\r\n";
                System.out.println("**  add { " + _index_of_number_ + "/" + number + " } to xml file **");

                /**
                 * 下一篇page 的序号
                 */
                _index_of_number_++;

            }else {
                temp_text += "</" + localName + ">";
            }
        }

        /**
         * 读取一个元素的text
         */
        public void characters(char[] ch, int start, int length)
                throws SAXException {
            try {
                String temp_str = new String(ch,start,length);
//                System.out.println("11***"+temp_str+"****222");
                temp_str = temp_str.replace("<", "");
                temp_str = temp_str.replace(">", "");
                temp_str = temp_str.replace("&", "&amp;");
                temp_text += temp_str;

//                System.out.println("text:"+new String(ch,start,length));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void writeToFile(String path,String encode) {
            System.out.println("write to file :" + path);
            fileUtil.write(path, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n", true, encode);
            fileUtil.write(path, "<" + rootTag + ">\r\n", true, encode);
//            _temp_content_ = _temp_content_.replaceAll("<.*>", "");
//            _temp_content_ = _temp_content_.replaceAll("&(.*?);", "");
            fileUtil.write(path, _temp_content_, true, encode);
            fileUtil.write(path, "</" + rootTag + ">", true, encode);
        }

        /**  解析XML  */
        public void opXML(String inputpath, String encode){
            System.out.println("XmlSplit: to "+file_number+" files");
            try {
                /**
                 * 初始化所有需要写入的xml文件的路径
                 */
                File inputFile = new File(inputpath);
                output_path = new String[file_number+1];
                for (int i = 0; i < file_number+1; i++) {
                    output_path[i] = outputDir+"/"+inputFile.getName()+"_part"+i+".xml";
                }

                XMLReader xr = XMLReaderFactory
                        .createXMLReader("org.apache.xerces.parsers.SAXParser");

                xr.setContentHandler(new XmlSplitHandler());
                xr.parse(new InputSource(new InputStreamReader(
                        new FileInputStream(new File(inputpath)), encode)));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**  将一个大的 xml 文件分成 number 的小的 xml文件 */
    public void split(String path,String dir,String tag_root, String tag_each, String encode, int split_number) {
        outputDir = dir;

        rootTag = tag_root;
        eachTag = tag_each;
        XmlCountHandler xmlCountHandler = new XmlCountHandler();
        number = xmlCountHandler.opXML(path, eachTag, "utf-8");
        System.out.println("total number the this xml file is : " + number);


        System.out.println("\n\n\n******************************\n\n\n");
        file_number = split_number;

        XmlSplitHandler xmlTool = new XmlSplitHandler();
        xmlTool.opXML(path, encode);
    }

    /**
     *
     */
    public static void main(String args[]) {
        if (args.length < 6) {
            System.out.println("XmlSplit path outputDir rooTag eachTag encode splitNumber");
            return;
        }
        XmlSplit xmlSplit = new XmlSplit();
        String path = "data/input/xml/baikedumpsample.xml";
        path = args[0];
        String dir = "data/input/xml";
        dir = args[1];
        String rootTag = "data";
        rootTag = args[2];
        String eachTag = "page";
        eachTag = args[3];
        String encode = "utf-8";
        encode = args[4];
        int split_number = 4;
        split_number = Integer.parseInt(args[5]);
        System.out.println("切分xml文件：");
        System.out.println("rootTag：" + rootTag);
        System.out.println("eachTag：" + eachTag);
        System.out.println("encode：" + encode);
        System.out.println("切成 " + split_number + " 个文件");

        xmlSplit.split(path,dir, rootTag, eachTag, encode, split_number);
        System.out.println();
    }

    /**
     *
     */
    public static String reformat(String message) {
        StringBuffer sb = new StringBuffer("");

        message = message.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;")
                .replace("'", "&apos;").replace("^", "").replace("\b", "");

        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);
            if ((ch == 0x9) || (ch == 0xA) || (ch == 0xD)
                    || ((ch >= 0x20) && (ch <= 0xD7FF))
                    || ((ch >= 0xE000) && (ch <= 0xFFFD))
                    || ((ch >= 0x10000) && (ch <= 0x10FFFF)))
                sb.append(ch);
        }
        return sb.toString();
    }
}
