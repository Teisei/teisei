package edu.ecnu.teisei.ir.lucene.analysis;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.Reader;

/**
 * Created by dingcheng on 2014/12/22.
 */
public class SimpleAnalyzer extends Analyzer {
    @Override
    protected TokenStreamComponents createComponents(String s, Reader reader) {
//        return new LowerCaseTokenizer(reader);
        return null;
    }

    public String segment(String text, String splitToken) {
        String res = "";
        /**
         * 创建一个文本分析器:包装器
         * 通常一个 Tokenizer 和 多个 TokenFilter
         */
        Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_46, true);
        try {
            /**
             * TokenStream 接收一个 Reader 进行基础性分词
             */
            TokenStream tokenStream = analyzer.tokenStream("field", text);

            CharTermAttribute term = tokenStream.addAttribute(CharTermAttribute.class);

            tokenStream.reset();

            while (tokenStream.incrementToken()) {
                res += term.toString() + "|";
            }
            tokenStream.end();
            tokenStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        res = res.substring(0, res.length() - 1);
        return res;
    }

    @Test
    public void testSegment() {
        String str = "我这次来澳门，就是要同澳门同胞一起庆祝这个具有重大历史意义的节日，转达中央政府和全国各族人民对澳门同胞的祝福。";
        System.out.println(this.segment(str,"|"));
    }
}
