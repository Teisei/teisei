package edu.ecnu.teisei.ir.dataprocess;

import edu.ecnu.teisei.ir.common.SenSegmentation;
import org.apache.lucene.document.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;


public class TFIDFAgorithm {
	
	/***
	 * HashMap<String, Integer> 代表该次计算过程当中选择出来的词库作为feature，词语使用数字来表示，HashMap<词语, 对应的数字>
	 */
	public static HashMap<String, Integer> features = new HashMap<String, Integer>();
	
	
	/***
	 * HashMap<String,List<String>> 用于存储每个新闻稿的每一句话
	 * HashMap<文档名,HashMap<句子,句子的得分>>在List<String>中的index就是在文档中的位置
	 */
	public static HashMap<String, List<String[]>> sentencesMap=new HashMap<String, List<String[]>>();
	
	/***
	 * 
	 */
	public static Map<Integer,Double> wordIdfMap =new  HashMap<Integer,Double> ();
	
	/***
	 * 
	 */
	public static Map<String,Map<Integer, Double>> wordFrequencyMap=new HashMap<String,Map<Integer, Double>>();
	/***
	 * HashMap<String,String[]> 用于存储整个语料库分词的结果，这样整个语料库只需要分词一次就可以
	 */
	
	public static HashMap<String, List<String>> segsMap=new HashMap<String,List<String>>();
	/***
	 * calulate tfidf of a document set
	 * @param list
	 * @param field
	 * @return Map<docname, Map<feature, tfidf>>
	 */
	
	public static Map<String, Map<Integer, Double>> tfidf(
			Map<String, Document> list, String field) {
		// TODO Auto-generated method stub
		features.clear();
		segsMap.clear();
		sentencesMap.clear();
		Map<String, Map<Integer, Double>> resultMap = new HashMap<String, Map<Integer, Double>>();
		Map<Integer, Double> idfPerWordMap = new HashMap<Integer, Double>();
		try {
			idfPerWordMap = idf(list, field);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// 计算每篇文档中含有各特征词数量
		Map<Integer, Double> TFPerDocMap = new TreeMap<Integer, Double>();
		Set<Entry<String, List<String>>> entrySet = segsMap.entrySet();
		for (Entry<String,List<String>> entry : entrySet) {
			TFPerDocMap.clear();
			Map<Integer, Double> tfmap = new HashMap<Integer, Double>();
			// calculate the tf of each document
			List<String> segs=entry.getValue();
			for (int i = 0; i < segs.size(); i++) {
				//search the feature table
				if(features.containsKey(segs.get(i))){
					int wordId=features.get(segs.get(i));
					if (tfmap.containsKey(wordId)) {
						tfmap.put(wordId, tfmap.get(wordId) + 1.0);
					} else {
						tfmap.put(wordId, 1.0);
					}
				}
			}
			
			wordFrequencyMap.put(entry.getKey(), tfmap);
			double maxCount = 0.0, wordWeight;
			
			// cal the max count ,for normalization
			Set<Entry<Integer, Double>> tempTF = tfmap.entrySet();
			for (Iterator<Entry<Integer, Double>> mt = tempTF.iterator(); mt
					.hasNext();) {
				Entry<Integer, Double> me = mt.next();
				if (me.getValue() > maxCount) {
					maxCount = me.getValue();
				}
			}
			
			for (Iterator<Entry<Integer, Double>> mt = tempTF.iterator(); mt
					.hasNext();) {
				Entry<Integer, Double> me = mt.next();
				Double IDF = Math.log(list.size()
						/ idfPerWordMap.get(me.getKey()))
						/ Math.log(10);
				wordWeight = (me.getValue() / maxCount) * IDF;
				TFPerDocMap.put(me.getKey(), wordWeight);
			}
			TreeMap<Integer, Double> tempMap = new TreeMap<Integer, Double>();
			tempMap.putAll(TFPerDocMap);
			resultMap.put(entry.getKey(), tempMap);
		}
		return resultMap;
	}

	private static Map<Integer, Double> idf(Map<String, Document> list,
			String field) throws IOException {
		// TODO Auto-generated method stub
		Map<String, Double> idfPerWordMap = new TreeMap<String, Double>();
		
		//提取出每一篇文档的每一个sentence
		
		
		// 记下当前已经遇到过的该文档中的词
		Set<String> alreadyCountWord = new HashSet<String>();
		String word;
		Map<String, Integer> set = new HashMap<String, Integer>();
		Set<Entry<String, Document>> entrySet = list.entrySet();
		for (Entry<String, Document> entry : entrySet) {
			alreadyCountWord.clear();
			List<String> segs=new ArrayList<String>();
			List<String[]> sentenList=new ArrayList<String[]>();
			String content = entry.getValue().get(field);
			if (content != null) {
			  
			    //存储新闻稿中的每一条句子
			    List<String> sentences= SenSegmentation.segSentence(content);
			    String[] words= null;
			    for(int i=0;i<sentences.size();i++){
			      words=SmartChineseSegmenter.smartChineseAnalyzerSegment(sentences.get(i));
			      for(int j=0;j<words.length;j++){
			        segs.add(words[j]);
			      }
			      sentenList.add(words);
			    }
			    sentencesMap.put(entry.getKey(), sentenList);
				for (int j = 0; j < segs.size(); j++) {
					word = segs.get(j);
					if (set.containsKey(word)) {
						set.put(word, set.get(word) + 1);
					} else {
						set.put(word, 1);
					}
					// 同一个文档中的词语只统计一次
					if (!alreadyCountWord.contains(word)) {
						if (idfPerWordMap.containsKey(word)) {
							idfPerWordMap.put(word,
									idfPerWordMap.get(word) + 1.0);
						} else
							idfPerWordMap.put(word, 1.0);
						alreadyCountWord.add(word);
					}
				}
			}
			else{
			  continue;
			}
			segsMap.put(entry.getKey(), segs);
		}

		
		Iterator<Entry<String, Integer>> entrys=set.entrySet().iterator();
		FilteStopWords filter=FilteStopWords.getInstance();
		while(entrys.hasNext()){
			Entry<String, Integer> entry=entrys.next();
			//过滤低频词和停顿词
			if (entry.getValue() < 3 || filter.isStopWord(entry.getKey())) {
				entrys.remove();
				set.remove(entry.getKey());
				idfPerWordMap.remove(entry.getKey());
			}
		}
		
		int i = 0;
		for (Entry<String, Double> entry : idfPerWordMap.entrySet()) {
		    //过滤在大部分文档中都出现的词
			if (entry.getValue() <= list.size()*2/3) {
				features.put(entry.getKey(), i);
				wordIdfMap.put(i, entry.getValue());
				i++;
			}
		}
		
		return wordIdfMap;
	}
	
	/***
	 * for test !
	 * 
	 * @param testSampleMap
	 * @throws java.io.IOException
	 */
	@SuppressWarnings("unused")
	private static void printTestSampleMap(
			Map<String, Map<String, Double>> testSampleMap) throws IOException {
		// TODO Auto-generated method stub
		File outPutFile = new File("testSampleMap.txt");
		FileWriter outPutFileWriter = new FileWriter(outPutFile);
		Set<Entry<String, Map<String, Double>>> allWords = testSampleMap
				.entrySet();
		for (Iterator<Entry<String, Map<String, Double>>> it = allWords
				.iterator(); it.hasNext();) {
			Entry<String, Map<String, Double>> me = it.next();
			outPutFileWriter.append(me.getKey() + " ");
			Set<Entry<String, Double>> vecSet = me.getValue().entrySet();
			for (Iterator<Entry<String, Double>> jt = vecSet.iterator(); jt
					.hasNext();) {
				Entry<String, Double> ne = jt.next();
				outPutFileWriter
						.append(ne.getKey() + " " + ne.getValue() + " ");
			}
			outPutFileWriter.append("\n");
			outPutFileWriter.flush();
		}
		outPutFileWriter.close();
	}
	
	public static String getFeatureWordByIndex(int index){
	  Set<Entry<String, Integer>> entrySet=features.entrySet();
	  for(Entry<String, Integer> entry:entrySet){
	    if(entry.getValue()==index){
	      return entry.getKey();
	    }
	  }
	  System.out.println("no index in the features!");
	  return "";
	}
	
	
	public static String[] getAllSentenceByDocName(String docName){
	    List<String[]> sentences=sentencesMap.get(docName);
	    
	    List<String> doc=new ArrayList<String>();
	    for(int i=0;i<sentences.size();i++){
	      String[] temp=sentences.get(i);
	      for(int j=0;j<temp.length;j++){
	        doc.add(temp[j]);
	      }
	    }
	    String[] result=new String[doc.size()];
	    for(int i=0;i<doc.size();i++){
	      result[i]=doc.get(i);
	    }
	    return result;
	}
}
