package edu.ecnu.teisei.ir.common;

import edu.ecnu.teisei.ir.dataprocess.XmlDomReader;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringHelper {
	public static String null2String(String string){
		if(string==null){
			return "";
		}
		return string;
	}
	
	public static String getStringbyRegex(String string,String regexp){
		Pattern pattern = Pattern.compile(regexp); 
		Matcher matcher=pattern.matcher(string);
		StringBuffer sb=new StringBuffer();
		while(matcher.find())
		{
			sb.append(matcher.group(1)).append("\t");
		}
		return sb.toString();
	}
	
	public static String getTime(String date){
		date=date.replace(" ", "");
		date=date.replace(":", "");
		date=date.replace("年", "");
		date=date.replace("月", "");
		date=date.replace("日", "");
		return date;
	}
	
	//for test
	public static ArrayList<String> getMentionList(){
		ArrayList<String> result =new ArrayList<String>();
		NodeList nl;
//		XmlDomReader xdr = new XmlDomReader("C:/Users/CBD_15/Documents/MyFiles/Data/dataformerge/worldcompany/worldcompany-part4.xml");
		XmlDomReader xdr;
		try {
			xdr = new XmlDomReader("C:/Users/CBD_15/Documents/MyFiles/Data/dataformerge/public/3073.xml");
			nl = xdr.getElementsByTagName("company");
			HashSet<String> set =new HashSet<String>();
			for(int i=0;i<nl.getLength();i++){
				Element element = (Element)nl.item(i);
				element.getAttributes();
				String chinesename = element.getElementsByTagName("chinesename").item(0).getFirstChild().getNodeValue();
				if(!set.contains(chinesename)){
					result.add(chinesename);
					set.add(chinesename);
				}
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
