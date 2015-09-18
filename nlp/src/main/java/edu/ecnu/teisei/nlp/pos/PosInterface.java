package edu.ecnu.teisei.nlp.pos;


import org.ansj.domain.Term;

import java.util.List;

public interface PosInterface {


    /**
     * 词之间空格
     */
    public final String split_words = "  ";
    /**
     * 词和词性之间间隔
     */
    public final String split_wordTag = "__";

	//设置分词词典
	public void setDict(String dicpath) throws Exception;
	//加入单词word
	public void addWord(String word, String pos);
	//删除单词word
	public void removeWord(String word);
	//设置分词词典 List<String>
	public void addWords(List<String> words, String pos);
	
	
	
	//对字符串进行词性标注，结果以..w/pos w/pos..的形式返回。
    public List<Term> tagOneSeq2List(String str);
	public String tagOneSeq(String str);

	//对str进行词性标注，结果以String tags[][]返回。
	public String[][] tag(String str);
    public List<Term> tag2List(String str);
    public String[] tag2Array(String string);

    //对str进行词性标注，结果过滤掉词性集合 f 中的词性
    public List<Term> tagFilter2List(List<Term> tl, String[] f);
    public List<Term> tagFilter2List(String str, String[] f);

    //对str进行词性标注，结果只取词性集合 f 中的词性
    public List<Term> tagSelect2List(List<Term> tl, String[] f);
    public List<Term> tagSelect2List(String str, String[] f);
	
	//对str先进行断句，再进行词性标注，结果以String tags[][][]返回。
    public List tagSents2List(String str);
    public List tagSents2ListSelect(String str, String[] f);
	public String[][][] tagSents(String str);
	
	//对str进行词性标注，结果以String 返回(词按行，每行w \t pos)。
	public String tag2String(String str);
	
	//将已经分好词的数组传入，进行词性标注，返回词性数组。
	public String[] tagSeged2Array(String str[]);

    //将分好的结果变成 "... w1/pos1 w2/pos2 ..."
    public String array2str(String str[][]);
    // "... w1/pos1 w2/pos2 ..."变成词词性数组
    public String[][] str2array(String str);

    public String[][] filtePOS(String terms[][], String f[]);
    public String[][] selectPOS(String[][] terms, String[] f);

}
