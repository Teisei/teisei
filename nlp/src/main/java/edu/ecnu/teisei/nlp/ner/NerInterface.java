package edu.ecnu.teisei.nlp.ner;

import java.util.List;

public interface NerInterface {
	//设置实体词典
	public void setDict(String dicpath) throws Exception;
	
	//将文本进行ner处理。先分句子，然后对每一句话进行处理，词性标注，实体识别。
	public String[] nertag(String[] pos);
	
	/**
	 * 对文本进行实体抽取
	 * @param words：传入 word 和 pos
	 * @return：二维数组，对应的 word 和 ner
	 */
	public String[][] nertag(String words[][]);
	
	/**
	 * 对实体进行过滤
	 * @param words[][]:所有的词性和实体
	 * @param nerlist:传入所需实体类别
	 * @return：指定类别的所有实体
	 */
	public String[][] nertag(String words[][], List<String> nerlist);

	
}
