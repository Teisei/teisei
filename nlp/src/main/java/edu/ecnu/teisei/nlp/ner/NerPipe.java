package edu.ecnu.teisei.nlp.ner;


import org.ansj.domain.Term;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dingcheng on 2014/11/4.
 */
public class NerPipe {

    public static String[] NoCompany = {"我","你","了","，","。","、","：","被告","原告","申请人","收款人","文书","受理","传票","根据","债务人","上诉人","出票人"," "};
    public static String[] NoCompanyTag = {"n","v","c","p"};
    public static String[] NoEntityPosTag = {"v","c","p","cc","m","u","t","w","e","y","o"};
    public static String[] CompanyTag = {"nw","ntc","nt"};
    public static String[] PersonTag = {"nr","nw"};
    public static String[] EntityTag = {"entity" };

    public String[][] mergeEntity(String[][] wordsAtags) {
        String temp_wordsAtags[][] = wordsAtags;
        int temp_length = temp_wordsAtags[0].length; //句子长度
        while (true) {
            temp_wordsAtags = mergeEntity(temp_wordsAtags[0], temp_wordsAtags[1]);
            //
            if (temp_length == temp_wordsAtags[0].length) {
                return temp_wordsAtags;
            }else{
                temp_length = temp_wordsAtags[0].length;
            }
        }
    }
    /**
     * 将词性标注好的序列进行实体合并（）
     * @param words
     * @param tags
     * @return
     */
    public String[][] mergeEntity(String[] words, String[] tags) {
//        System.out.println("merge once!");
        String[] labels = new String[words.length];

        Map<Integer, Integer> temp_map = new HashMap<Integer, Integer>();

        for (int i = 0; i < words.length; i++) {

            boolean flag = false;//不合并

            //这一个词，
            if (words[i].contains("公司")) {

                //不一定为实体

                //向前
                flag = true;
                String temp_entity = "";
                int temp_length = 1;

                /**
                 * 从该词语向前走3格
                 */
                for (int j = i - 1; j >= Math.max(i - 3, 0); j--) {

                    /**
                     * 如果前一个词 label 已经为e
                     */
                    if (j == i - 1 && labels[j].equals("e")) {
                        labels[j] = "c";
                        temp_length++;
//                        System.out.print("add word: "+words[i] + "|" + tags[i] + "|e\n");
                        labels[i] = "e";
                        break;
                    }


                    /**
                     * 向前合并过滤词
                     */

                    /**
                     * 如果向前的词为一个字
                     */
//                    if (words[j].length() == 1) {
//                        flag = false;
//                        break;
//                    }

                    /**
                     * 如果公司名禁用词包含，则退出
                     */
                    for (int u = 0; u < NoCompany.length; u++) {
                        if (words[j].contains(NoCompany[u])) {
                            flag = false;
                            break;
                        }
                    }
                    if (!flag) {
                        break;
                    }

                    /**
                     * 过滤部分词性
                     */
                    for (int u = 0; u < NoEntityPosTag.length; u++) {
                        if (tags[j].equals(NoEntityPosTag[u])) {
                            flag = false;
                            break;
                        }
                    }
                    if (!flag) {
                        break;
                    }

//                    /**
//                     * 向前move的条件
//                     */
//                    boolean isMove = false;
//                    for (int u = 0; u < CompanyTag.length; u++) {
//                        if (tags[j].equals(CompanyTag[u])) {
//                            isMove = true;
//                            break;
//                        }
//                    }
//                    flag = isMove;
//                    if (!flag) {
//                        break;
//                    }


                    /**
                     * 不在继续向前
                     */
                    if (!flag)
                        break;
//
//                    //如果含有一个动词，不合并
//                    if (words[j].equals("v") || words[j].contains("公司")) {
//                        break;
//                    }

                    /**
                     * 到这样的词就结束，往后到i标记为连续
                     */
                    if (tags[j].equals("nw") || tags[j].equals("ns") || words[j].contains("省") || words[j].contains("市")) {
                        labels[j] = "c";
                        labels[i] = "e";
                        temp_length++;
//                        System.out.println();
                        break;
                    } else {
                        /**
                         * 合并词汇加入该词,继续向前寻找 可合并词
                         */
                        labels[j] = "c";
                        temp_length++;
                        continue;
                    }
                }
                if (temp_length == 1)
                    labels[i] = "s";
                else
                    labels[i] = "e";
            } else {
                labels[i] = "s";
            }
        }

        int temp_reduce_number=0;
        for (int uu = 0; uu < labels.length; uu++) {
            if(labels[uu].equals("c"))
                temp_reduce_number++;
        }

        String res[][] = new String[2][words.length - temp_reduce_number];
        int temp_index = 0;
        String temp_word = "";
        for (int i = 0; i < words.length; i++) {
            if (labels[i].equals("c")) {
                temp_word += words[i];
//                System.out.print(words[i] + "|" + tags[i] + " ");
            } else if (labels[i].equals("e")) {
                res[0][temp_index] = temp_word + words[i];
                res[1][temp_index] = "entity";
                temp_index++;
//                System.out.println(words[i] + "|" + tags[i]);
            } else {
                temp_word = "";
                res[0][temp_index] = words[i];
                res[1][temp_index] = tags[i];
                temp_index++;
            }
        }
        return res;
    }

//    public List<Term> mergeEntity(List<Term> words){
//        String[] labels = new String[words.size()];
//        int temp_reduce_number = 0;
//
////      for(int i=0;i<words.size();i++){
//
//
//        int temp_words_index = 0;
//        for(Term t: words){
//            boolean flag = false;//不合并
//
//            //这一个词，
//            if(t.getName().contains("公司")){
//
//                //肯定为实体
//                flag = true;
//                String temp_entity = "";
//
//                //往前走4个词语，进行合并。
//                for(int j=1;j<5;j++){
//                    String temp_this_word = words.get(temp_words_index-j).getName();
//                    String temp_this_tag  = words.get(temp_words_index-j).getNatrue().natureStr;
//
//                    //如果含有一个动词，不合并
//                    if(temp_this_tag.equals("v")||temp_this_word.contains("公司")){
//                        break;
//                    }
//                    //
//                    if(temp_this_tag.equals("ns")||temp_this_word.contains("省")||temp_this_word.contains("市")){
//
//                        for(int u=temp_words_index-j;u<temp_words_index;u++) {
//                            labels[u] = "c";
//                            temp_reduce_number++;
//                        }
//                        break;
//                    }
//                }
//                //最后一个词为实体后缀
//                labels[temp_words_index]="e";
//            }
//
//            if(!flag){
//                labels[temp_words_index] = "s";
//            }
//
//            temp_words_index++;
//        }
//
//        int temp_index = 0;
//        String temp_word = "";
////        for (int i = 0; i < words.length; i++) {
//
//        temp_words_index = 0;
//
//        //遍历每个词
//        for(Term t: words){
//            if (labels[temp_words_index].equals("c")) {
//                temp_word += t.getName();
//                words.remove(t);//将该点删除
//            } else if (labels[temp_words_index].equals("e")) {
//                t.setName(temp_word + t.getName());//将名称设置为 合并的名
//                //修改词性
//            } else {
////                temp_word = "";
////                res[0][temp_index] = t.getName();
////                res[1][temp_index] = "null";
//            }
//            temp_words_index++;
//        }
//        return words;
//    }

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
