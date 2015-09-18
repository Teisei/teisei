package edu.ecnu.teisei.nlp.ner;


import org.ansj.domain.Term;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Recognize some entity by the known entity.
 * Created by Teisei on 2015/5/15.
 */
public class LinkByEntityPipe {


    /**
     * ͨ���û��Զ����ֵ�ʶ������Ĵ�������ʵ������
     * @param words
     * @param tags
     * @return
     */
    public List<Term> findEntityByKnownEntity(String[] words, String[] tags) {
        Map<String, String> dicMap = new HashMap<>();
        for (int i =0;i<words.length;i++) {
            if (tags.equals("company")) {
                dicMap.put(words[i], tags[i]);
            }
        }
        return null;
    }
}
