package edu.ecnu.teisei.ir.lucene.index;

import edu.ecnu.teisei.ir.dataprocess.Xml2DocumentUtils;
import edu.ecnu.teisei.ir.dataprocess.XmlSaxReader;
import org.apache.lucene.document.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class BuildIndex {
    public static void main(String[] args) {
//		buildindexSax(Configer.DATA_SOURCE_DIR);
        String path = "input/dashuju/";
//        buildIndexFromCSV(path);
        buildindexDom(path);
    }

    /**
     *
     * @param src
     */
    public static void buildindexDom(String src) {
        File dir = new File(src);
        IndexDAO index = IndexDAO.getInstance();
        if (!dir.isDirectory()) {
            System.out.println("Please integrate the data sources in an dir");
            return;
        }
        File[] xmlfiles = dir.listFiles();
        for (File xmlfile : xmlfiles) {

            ArrayList<Document> docs = Xml2DocumentUtils.xml2Documents(xmlfile
                    .getAbsolutePath());
            index.add(docs);
            System.out.println("finish indexing " + xmlfile.getAbsolutePath());
            docs.clear();
        }
        System.out.println("total news number " + Xml2DocumentUtils.count);
        System.out.println("start to optimize!");
        index.optimize();
    }

    public static void addindex(String xmlfile) {
        IndexDAO index = IndexDAO.getInstance();
        System.out.println("reading file :" + xmlfile);
        ArrayList<Document> docs = Xml2DocumentUtils.xml2Documents(xmlfile);
        System.out.println("start to index file " + xmlfile);
        index.add(docs);
        System.out.println("start to optimize!");
        index.optimize();
        System.out.println("optimize down!");
    }

    /**
     * indxing the src file with Sax Parser ,don't need to load the whole file into RAM
     *
     * @param src
     */
    public static void buildindexSax(String src) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;
        File dir = new File(src);
        try {
            parser = factory.newSAXParser();
            if (!dir.isDirectory()) {
                System.out
                        .println("Please integrate the data sources in an dir");
                return;
            }
            File[] xmlfiles = dir.listFiles();
            IndexDAO indexdao = IndexDAO.getInstance();
            XmlSaxReader dh = new XmlSaxReader();
            for (File file : xmlfiles) {
                System.out.println("start to index " + file.getAbsolutePath());
                parser.parse(file, dh);
            }
            System.out.println("start to optimize!");
            indexdao.optimize();
            System.out.println("the total news number :" + dh.getTotalCount());
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    /**
     * 从 CSV 文件建立 index
     * @param csvpath
     */
    public static void buildIndexFromCSV(String csvpath) {
        IndexDAO index = IndexDAO.getInstance();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(csvpath), "UTF-8"));
            String line = "";
            int count = 0;
            int k = 0;

            List<Document> docs = new ArrayList<Document>();
            while ((line = br.readLine()) != null) {
                Document doc = Xml2DocumentUtils.csvline2Document(line);
                if (doc != null) {
                    docs.add(doc);
                    count++;
                }
                if (count == 10000) {
                    k++;
                    index.add(docs);
                    System.out.println("finish indexing the " + k * 10000 + "th line ...");
                    docs.clear();
                    count = 0;
                }
            }
            index.add(docs);
            docs.clear();
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("start to optimization!");
        index.optimize();
    }
}
