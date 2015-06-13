package edu.ecnu.teisei.ir.dataprocess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

public class SmartChineseSegmenter {
	public static String[] smartChineseAnalyzerSegment(String str) {
		List<String> list = new ArrayList<String>();
		@SuppressWarnings("resource")
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer(
				Version.LUCENE_46, true);
		TokenStream tokenStream;
		try {
			tokenStream = analyzer.tokenStream("field", str);
			CharTermAttribute term = tokenStream
					.addAttribute(CharTermAttribute.class);
			tokenStream.reset();
			while (tokenStream.incrementToken()) {
				list.add(term.toString());
			}
			tokenStream.end();
			tokenStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] result = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}
		return result;
	} // 测试切词
}
