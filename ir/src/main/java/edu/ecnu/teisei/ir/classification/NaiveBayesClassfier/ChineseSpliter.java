package edu.ecnu.teisei.ir.classification.NaiveBayesClassfier;

import edu.ecnu.teisei.ir.lucene.analysis.SimpleAnalyzer;
import org.junit.Test;

/**
 * 中文分词器
 */
public class ChineseSpliter {
    /**
     * 对给定的文本进行中文分词
     *
     * @param text       给定的文本
     * @param splitToken 用于分割的标记,如"|"
     * @return 分词完毕的文本
     */
    public static String split(String text, String splitToken) {
        String result = null;
        SimpleAnalyzer analyzer = new SimpleAnalyzer();
        result = analyzer.segment(text, splitToken);
        return result;
    }

    @Test
    public void testSegment() {
        String text = "我这次来澳门，就是要同澳门同胞一起庆祝这个具有重大历史意义的节日，转达中央政府和全国各族人民对澳门同胞的祝福。";
        String splitToken = "|";
        SimpleAnalyzer analyzer = new SimpleAnalyzer();
        String result = analyzer.segment(text, splitToken);
        System.out.println(result);
    }
}
