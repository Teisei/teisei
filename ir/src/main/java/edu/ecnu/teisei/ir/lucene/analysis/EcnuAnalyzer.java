package edu.ecnu.teisei.ir.lucene.analysis;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.cn.smart.SentenceTokenizer;
import org.apache.lucene.analysis.cn.smart.WordTokenFilter;
import org.apache.lucene.util.Version;

import java.io.Reader;

/**
 * Created by dingcheng on 2014/12/22.
 */
public class EcnuAnalyzer extends Analyzer {
    private final Version matchVersion;
    boolean usePOSFilter;

    /**
     * 指定CNFactory路径
     * @param matchVersion
     * @param path CNFactory路径
     * @param usePOSFilter 使用词性作为停用词过滤
//     * @throws LoadModelException
     */
    public EcnuAnalyzer(Version matchVersion,String path,boolean usePOSFilter)
//            throws LoadModelException {
    {
        this.matchVersion = matchVersion;
//        CNFactory.getInstance(path,Models.SEG_TAG);
        this.usePOSFilter = usePOSFilter;
    }
    /**
     * 指定CNFactory路径
     * @param matchVersion CNFactory路径
     * @param path
//     * @throws LoadModelException
     */
    public EcnuAnalyzer(Version matchVersion,String path)
//            throws LoadModelException {
    {
        this.matchVersion = matchVersion;
//        CNFactory.getInstance(path,Models.SEG_TAG);
    }

    /**
     * 需要预先建立CNFactory
     * @param matchVersion
     */
    public EcnuAnalyzer(Version matchVersion) {
        this.matchVersion = matchVersion;
        this.usePOSFilter = true;
    }

    @Override
    public TokenStreamComponents createComponents(String fieldName, Reader reader) {
        Tokenizer tokenizer = new SentenceTokenizer(reader);
        TokenStream result = new WordTokenFilter(tokenizer);
//        result = new POSTaggingFilter(true,result);

        return new TokenStreamComponents(tokenizer, result);
    }
}
