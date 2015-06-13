package edu.ecnu.teisei.ir.dataprocess;

import edu.ecnu.teisei.ir.common.StringHelper;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;


public class Xml2DocumentUtils {
	
	public static  int count=0;
	public static ArrayList<Document> xml2Documents(String xmlpath){
		NodeList nl;
		XmlDomReader xdr;
		ArrayList<Document> result=new ArrayList<Document>();
		try {
			xdr = new XmlDomReader(xmlpath);
			nl = xdr.getElementsByTagName("news");
			count+=nl.getLength();
			System.out.println(nl.getLength());
			for (int i = 0; i < nl.getLength(); i++) {
				Element element = (Element) nl.item(i);
				Document doc=element2Document(element);
				if(doc!=null){
					result.add(doc);
				}
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static Document element2Document(String elementStr){
		Document doc = new Document();
		String[] strs=elementStr.split(" ### ");
		if(strs.length !=7)
			return null;
		String title="";
		String time ="";
		String media="";
		String keywords = "";
		String description = "";
		String url = "";
		String text = "";
		url= StringHelper.null2String(strs[0]);
		title=StringHelper.null2String(strs[1]);
		time=StringHelper.getTime(StringHelper.null2String(strs[2]));
		media=StringHelper.null2String(strs[3]);
		keywords=StringHelper.null2String(strs[4]);
		description=StringHelper.null2String(strs[5]);
		text=StringHelper.null2String(strs[6]);
		try{
			doc.add(new TextField("title", title, Store.YES));
			doc.add(new LongField("time", Long.valueOf(time), Store.YES));
			doc.add(new StringField("url", url, Store.YES));
			doc.add(new StringField("media", media, Store.YES));
			doc.add(new StringField("keywords", keywords, Store.YES));
			doc.add(new TextField("description", description, Store.YES));
			doc.add(new TextField("text", text, Store.YES));
			return doc;
		}
		catch(NumberFormatException e){
			System.out.println("time can not convert to the Long!");
			return null;
		}
		
	}
	
	
	public static Document csvline2Document(String csvline){
      String[] components=csvline.split("\t");
      if(components.length!=9){
        return null;
      }
      String id=StringHelper.null2String(components[0]);
      String url=StringHelper.null2String(components[1]);
      String title=StringHelper.null2String(components[2]);
      long time=0;
      
      try{
        time=Long.valueOf(StringHelper.getTime(StringHelper.null2String(components[3])));
      }catch(Exception e){
        System.out.println("can't convert the time to long!");
        return null;
      }
      String plate=StringHelper.null2String(components[4]);
      String media=StringHelper.null2String(components[5]);
      String keywords=StringHelper.null2String(components[6]);
      String description=StringHelper.null2String(components[7]);
      String text=StringHelper.null2String(components[8]);
      
       
      if(title.equals("") || description.equals("") || text.equals("")){
        return null;
      }
      Document doc = new Document();
      doc.add(new StringField("id", id, Store.YES));
      doc.add(new TextField("title", title, Store.YES));
      doc.add(new LongField("time", Long.valueOf(time), Store.YES));
      doc.add(new StringField("url", url, Store.YES));
      doc.add(new StringField("plate", plate, Store.YES));
      doc.add(new StringField("media", media, Store.YES));
      doc.add(new StringField("keywords", keywords, Store.YES));
      doc.add(new TextField("description", description, Store.YES));
      doc.add(new TextField("text", text, Store.YES));
      return doc;
	}
	private static Document element2Document(Element element) {
		Document doc = new Document();
		String title="";
		String time ="";
		String media="";
		String keywords = "";
		String description = "";
		String url = "";
		String text="";
		
		if(element.getElementsByTagName("title")!=null && element.getElementsByTagName("title").item(0)!=null){
			title =StringHelper.null2String(element.getElementsByTagName("title").item(0)
				.getTextContent()); 
		}
		else{
			return null;
		}
		if(element.getElementsByTagName("url")!=null && element.getElementsByTagName("url").item(0)!=null){
			url = StringHelper.null2String(element.getElementsByTagName("url").item(0)
					.getTextContent());
			}
		if(element.getElementsByTagName("time")!=null && element.getElementsByTagName("time").item(0)!=null){
			time = StringHelper.getTime(StringHelper.null2String(element.getElementsByTagName("time").item(0)
				.getTextContent()));
		}
		
		if(element.getElementsByTagName("media")!=null && element.getElementsByTagName("media").item(0)!=null){
			media = StringHelper.null2String(element.getElementsByTagName("media").item(0)
					.getTextContent());
			}
		if(element.getElementsByTagName("keywords")!=null && element.getElementsByTagName("keywords").item(0)!=null){
			keywords = StringHelper.null2String(element.getElementsByTagName("keywords").item(0)
					.getTextContent());
			}
		if(element.getElementsByTagName("description" )!=null && element.getElementsByTagName("description").item(0)!=null){
			description = StringHelper.null2String(element.getElementsByTagName("description").item(0)
					.getTextContent());
			}
		if(element.getElementsByTagName("text" )!=null && element.getElementsByTagName("text").item(0)!=null){
			text = StringHelper.null2String(element.getElementsByTagName("text").item(0)
					.getTextContent());
			}
		doc.add(new TextField("title", title, Store.YES));
		doc.add(new LongField("time", Long.valueOf(time), Store.YES));
		doc.add(new StringField("url", url, Store.YES));
		doc.add(new StringField("media", media, Store.YES));
		doc.add(new StringField("keywords", keywords, Store.YES));
		doc.add(new TextField("description", description, Store.YES));
		doc.add(new TextField("text", text, Store.YES));
		return doc;
	}

}
