package edu.ecnu.teisei.test.schema.domain;

import edu.ecnu.teisei.schema.domain.NewsSentRelation;

/**
 * Created by Teisei on 2015/5/22.
 */
public class test_NewsSentRelation {
    public static void main(String args[]) {
        String a = "http://aaa|#|news1|#|sentence1|#|a_b|#|word1_word2";
        String b = "http://bbb|#|news1|#|sentence1|#|a_b|#|word1_word2";
        NewsSentRelation aa = new NewsSentRelation(a);
        NewsSentRelation bb = new NewsSentRelation(b);
        System.out.println(aa.compareTo(bb));
    }
}
