package edu.ecnu.teisei.nlp.seg;

import java.util.List;

/**
 * 简单分词
 * Created by dingcheng on 2014/12/27.
 */
public class SegSimple implements SegInterface {
    @Override
    public void setDict(String dicpath) throws Exception {

    }

    @Override
    public void addWord(String word, String pos) {

    }

    @Override
    public void removeWord(String word) {

    }

    @Override
    public void addWords(List<String> words, String pos) {

    }

    @Override
    public List<String> Seg2List(String src) {
        return null;
    }

    @Override
    public String[] Seg2Array(String src) {
        return new String[0];
    }

    @Override
    public String[][] Seg2DoubleArray(String src) {
        return new String[0][];
    }
}
