package edu.ecnu.teisei.ir.dataprocess;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


public class XmlDomReader {
	private String filePath;
	private Document document;
	private String tagName;
	
	
	/**
	 * Constructor 
	 * @param fp file path
	 * @throws Throwable
	 */
	public XmlDomReader(String fp) throws Throwable{
		filePath = fp;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		document = db.parse(new File(filePath));
	}
	
	public Document getDom() throws Throwable{
		return document;	
		}
	
	public String getXmlEncoding(){
		return document.getXmlEncoding();
	}
	
	public String getXmlVersion(){
		return document.getXmlVersion();
	}
	
	public  boolean getXmlStandalone(){
		return document.getXmlStandalone();
	}
	
	public NodeList getElementsByTagName(String tn){
		tagName = tn;
		NodeList nodelist = document.getElementsByTagName(tagName);
		return nodelist;
	}
	
}
