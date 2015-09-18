package edu.ecnu.teisei.nlp.pos;


import org.ansj.domain.Term;

import java.util.List;

/**
 * Created by dingcheng on 2014/11/7.
 */
public class PosPipe {
    /**
     * 将 List<Term> 转换为 String[][]
     * @param tl
     * @return
     */
    public String[][] convertTermToString(List<Term> tl) {
        String res[][] = new String[2][tl.size()];
        int temp_index = 0;
        for (Term word : tl) {
            res[0][temp_index] = word.getName();
            res[1][temp_index] = word.getNatureStr();
            temp_index++;
        }
        return res;
    }
}
