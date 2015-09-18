package edu.ecnu.teisei.nlp.seg;

import java.util.List;

public interface SegInterface {
	//设置分词词典
	public void setDict(String dicpath) throws Exception;
	//加入单词word
	public void addWord(String word, String pos);
	//删除单词word
	public void removeWord(String word);
	//设置分词词典 List<String>
	public void addWords(List<String> words, String pos);



	//对句子进行分词，返回 List<String>
	public List<String> Seg2List(String src);
	
	//对句子进行分词，返回string[]
	public String[] Seg2Array(String src);
	
	// 将String src分成句子之后，在分词=
	public String[][] Seg2DoubleArray(String src);
	
}
