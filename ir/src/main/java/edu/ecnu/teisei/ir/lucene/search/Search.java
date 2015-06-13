package edu.ecnu.teisei.ir.lucene.search;


import edu.ecnu.teisei.ir.dataprocess.SmartChineseSegmenter;
import edu.ecnu.teisei.ir.lucene.index.IndexDAO;
import edu.ecnu.teisei.ir.lucene.index.QueryResult;
import org.apache.lucene.document.Document;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author wlcheng
 */
public class Search {


    public static void main(String[] args) {
        Search.searchDocuments("百度");
        Set<Entry<String, Document>> docSet = resultDocuments.entrySet();
        for (Entry<String, Document> entry : docSet) {
            Document doc = entry.getValue();
            String title = doc.get("title");
            String url = doc.get("url");
            String media = doc.get("media");
            String time = doc.get("time");
            System.out.println(title + "\t" + time + "\t" + url + "\t" + media);
        }
        System.out.println(docSet.size());
    }



    HashMap<String, Integer> features = new HashMap<String, Integer>();
    static IndexDAO index = IndexDAO.getInstance();
    static int maxDocument = 2000;
    public static String query = "";


    public static Map<String, Document> resultDocuments = new HashMap<String, Document>();

    /**
     * 查找一片相关 document
     *
     * @param queryString
     * @return
     */
    public static Map<String, Document> searchDocuments(String queryString) {
        query = queryString;
        QueryResult result = index.search(SmartChineseSegmenter.smartChineseAnalyzerSegment(queryString), 0, maxDocument);
        Map<Integer, Document> list = result.getRecordList();
        Map<String, Document> resultMap = new HashMap<String, Document>();
        HashSet<Integer> set = new HashSet<Integer>();
        Set<Entry<Integer, Document>> entrySet = list.entrySet();
        for (Entry<Integer, Document> entry : entrySet) {
            Document doc = entry.getValue();
            String time = doc.get("time");
            if (!set.contains(time.hashCode())) {
                resultMap.put(
                        entry.getKey() + "_" + entry.getValue().get("time"),
                        entry.getValue());
                set.add(time.hashCode());
            }
        }
        resultDocuments = resultMap;
        return resultMap;
    }
//
//	public static ArrayList<DataPoint> builtDataPointSet(
//			Map<String, Map<Integer, Double>> list) {
//		Set<Entry<String, Map<Integer, Double>>> allWords = list.entrySet();
//		ArrayList<DataPoint> result = new ArrayList<DataPoint>();
//		for (Iterator<Entry<String, Map<Integer, Double>>> it = allWords
//				.iterator(); it.hasNext();) {
//			Entry<String, Map<Integer, Double>> me = it.next();
//			DataPoint dp = new DataPoint();
//
//			//此处只设置dataPoint的名称和时间
//			String dpName=me.getKey();
//			dp.setDataPointName(dpName);
//			dp.setTime(dpName.substring(dpName.indexOf("_")+1,dpName.length()));
//			Set<Entry<Integer, Double>> vecSet = me.getValue().entrySet();
//			HashMap<Integer, Double> vector = new HashMap<Integer, Double>();
//			for (Iterator<Entry<Integer, Double>> jt = vecSet.iterator(); jt
//					.hasNext();) {
//				Entry<Integer, Double> ne = jt.next();
//				vector.put(ne.getKey(), ne.getValue());
//			}
//
//			dp.setVector(vector);
//			result.add(dp);
//		}
//		return result;
//	}
}
