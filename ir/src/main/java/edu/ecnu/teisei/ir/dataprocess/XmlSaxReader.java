package edu.ecnu.teisei.ir.dataprocess;


import edu.ecnu.teisei.ir.lucene.index.IndexDAO;
import org.apache.lucene.document.Document;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.CharArrayWriter;
import java.util.ArrayList;


public class XmlSaxReader extends DefaultHandler {

	/**
	 * 
	 * 
	 * 
	 * doc.add(new Field("title", title, Store.YES, Index.ANALYZED));
	 * doc.add(new Field("time", time, Store.YES, Index.NOT_ANALYZED));
	 * doc.add(new Field("url", url, Store.YES, Index.NO)); doc.add(new
	 * Field("media", media, Store.YES, Index.NO)); doc.add(new
	 * Field("keywords", keywords, Store.YES, Index.NOT_ANALYZED)); doc.add(new
	 * Field("description", description, Store.YES,Index.NOT_ANALYZED));
	 * doc.add(new Field("text", description, Store.YES,Index.NO));
	 */
	private StringBuffer sb=new StringBuffer();
	IndexDAO index=null;
	
	private CharArrayWriter contents = new CharArrayWriter(); 
	private int count=0;
	private static int counter=0;
	private int totalCount=0;
	private ArrayList<Document> docs=new ArrayList<Document>();
	
	public XmlSaxReader(){
		index = IndexDAO.getInstance();
	}
	public int getTotalCount() {
		return this.totalCount;
	}
	@Override
	public void startDocument() throws SAXException {
		// System.out.println("SAX Event: START DOCUMENT");

	}
	@Override
	public void endDocument() throws SAXException {
//		System.out.println("start optimization!");
		if(docs!=null){
			index.add(docs);
		}
//		index.optimize();
	}
	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes attr) throws SAXException {
		contents.reset(); 
	}
	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		
		if (qName.equalsIgnoreCase("title")) {
			sb.append(contents.toString());
			sb.append(" ### ");
		}
		if (qName.equalsIgnoreCase("time")) {
			sb.append(contents.toString());
			sb.append(" ### ");
		}
		if (qName.equalsIgnoreCase("url")) {
			sb.append(contents.toString());
			sb.append(" ### ");
		}
		if (qName.equalsIgnoreCase("media")) {
			sb.append(contents.toString());
			sb.append(" ### ");
		}
		if (qName.equalsIgnoreCase("keywords")) {
			sb.append(contents.toString());
			sb.append(" ### ");
		}
		if (qName.equalsIgnoreCase("description")) {
			sb.append(contents.toString());
			sb.append(" ### ");
		}
		if (qName.equalsIgnoreCase("text")) {
			sb.append(contents.toString());
			sb.append(" ### ");
		}
		if (qName.equalsIgnoreCase("news")) {
			totalCount++;
			count++;
			String elementStr=sb.toString();
			if(elementStr.contains("新浪声明")){
				elementStr=elementStr.substring(0,elementStr.indexOf("新浪声明"));
			}
//			System.out.println(elementStr);
			if(count/10000==1){
				counter++;
				System.out.println("indexing the "+counter*10000+"th elements!");
				index.add(docs);
				docs.clear();
				count=0;
			}
			else{
				Document doc = Xml2DocumentUtils.element2Document(elementStr);
				if(doc!=null){
					docs.add(doc);
				}
			}
			sb.setLength(0);
		}
	}
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		contents.write( ch, start, length ); 
		
	}
	
}
