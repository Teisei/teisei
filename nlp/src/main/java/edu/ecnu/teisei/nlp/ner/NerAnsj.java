package edu.ecnu.teisei.nlp.ner;

import java.util.List;


public class NerAnsj implements NerInterface{
	static ConvertTag convertTag = new ConvertTag();
	public NerAnsj() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setDict(String dicpath) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 识别实体，返回
	 */
	@Override
	public String[] nertag(String[] pos) {
		String ners[] = new String[pos.length];
		String ner=null;
		for(int i=0;i<pos.length;i++){
			ner=convertTag.ansj2ner(pos[i]);
			if(ner!=null){
				ners[i]=ner;
			}else{
				ners[i]="";
			}
		}
		return ners;
	}
	/**
	 * 实体抽取
	 */
	@Override
	public String[][] nertag(String[][] words) {
		String thiswords[]=new String[words[0].length];
		String  thisners[]=new String[words[0].length];
		
		String ner = null;
		int number = 0;
		//遍历每一个词
		for(int i=0;i<words[0].length;i++){
			//获得该词的实体类别
			ner=convertTag.ansj2ner(words[1][i]);
			//如果该词实体类别不为空
			if(ner!=null){
				thiswords[number]=words[0][i];
				thisners[number] =ner;
				number++;
				continue;
			}
		}
		String res[][] = new String[2][number];
		for(int i=0;i<number;i++){
			res[0][i]=thiswords[i];
			res[1][i]=thisners[i];
		}
		
		thiswords=null;
		thisners=null;
		
		return res;
	}
	/**
	 * 过滤实体
	 */
	@Override
	public String[][] nertag(String words[][],List<String> nerlist) {
		String thiswords[]=new String[words[0].length];
		String  thisners[]=new String[words[0].length];
		
		String ner = null;
		int number = 0;
		//遍历每一个词
		for(int i=0;i<words[0].length;i++){
			//获得该词的实体类别
			ner=convertTag.ansj2ner(words[1][i]);
			//如果该词实体类别不为空
			if(ner!=null){
				for(String each: nerlist){
					if(ner.equals(each)){
						thiswords[number]=words[0][i];
						thisners[number] =ner;
						number++;
						continue;
					}
				}
			}
		}
		String res[][] = new String[2][number];
		for(int i=0;i<number;i++){
			res[0][i]=thiswords[i];
			res[1][i]=thisners[i];
		}
		return res;
	}
}
