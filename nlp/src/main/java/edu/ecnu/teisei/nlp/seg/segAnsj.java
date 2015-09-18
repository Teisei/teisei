package edu.ecnu.teisei.nlp.seg;


import edu.ecnu.teisei.utils.Sentenizer;
import org.ansj.domain.Term;
import org.ansj.library.UserDefineLibrary;
import org.ansj.splitWord.analysis.NlpAnalysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class segAnsj implements SegInterface {
	public segAnsj(){
	}
	
	/**
	 * 设置分词词典
	 */
	@Override
	public void setDict(String dicpath) throws IOException {
	}
	/**
	 * 删除单词word
	 */
	@Override
	public void removeWord(String word) {
		//删除词
		UserDefineLibrary.removeWord(word);
	}
	/**
	 * 在词典中添加一个词
	 */
	@Override
	public void addWord(String word,String pos) {
		//增加新词
		UserDefineLibrary.insertWord(word, pos, 1000);
	}
	/**
	 * 在词典中添加一些词
	 */
	@Override
	public void addWords(List<String> words, String pos) {
		//增加新词
		for(String word: words){
			UserDefineLibrary.insertWord(word, pos, 1000);
		}
	}
	
	/**
	 * 分词
	 */
	@Override
	public List<String> Seg2List(String src){
		List<Term> res = NlpAnalysis.parse(src);
		List<String> ans = new ArrayList<String>();
		String wordandtag[] = new String[res.size()];
		int i=0;
		for(Term word: res){
			ans.add(word.getName());
		}
		return ans;
	}
	/**
	 * 对句子进行分词，返回string[]
	 */
	@Override
	public String[] Seg2Array(String src) {
		List<Term> list = NlpAnalysis.parse(src);
		//List<String> list = MyStaticValue.getCRFSplitWord().cut(src);
		String[] res = new String[list.size()];
		int i=0;
		for(Term word: list){
			res[i]=word.getName();
			i++;
		}
		return res;
	}
	
	/**
	 * 将String src 分成句子之后，在分词
	 */
	@Override
	public String[][] Seg2DoubleArray(String src) {
		String res[][];
		String[] sents = Sentenizer.split(src);
		res=new String[sents.length][];
		
		for(int i=0;i<sents.length;i++){
			res[i]=Seg2Array(sents[i]);
		}
		return res;
	}
}
