package edu.ecnu.teisei.ir.dataprocess;


import edu.ecnu.teisei.ir.config.Configer;

import java.io.*;
import java.util.ArrayList;



public class FilteStopWords {
	static FilteStopWords instance=null;
	static ArrayList<String> stopwords=new ArrayList<String>();
	
	public static FilteStopWords getInstance(){
		if(instance==null){
			return new FilteStopWords();
		}
		return instance;
	}
	
	public FilteStopWords(){
		loadStopWords();
	}
	public boolean isStopWord(String word){
		for(int i=0;i<stopwords.size();i++){
			if(stopwords.get(i).equals(word)){
				return true;
			}
		}
		return false;
	}
	
	private static void loadStopWords(){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(Configer.STOP_WORDS_PATH), "UTF-8"));
			String line="";
			while ((line = br.readLine()) != null) {
				stopwords.add(line);
			}
			br.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
