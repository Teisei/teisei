package edu.ecnu.teisei.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 简单中文文本的断句
 * @author dingcheng
 *
 */
public class Sentenizer {

    private static char[] puncs = new char[] { '。', '？', '！','；',';' }; //分隔句子
    private static char[] puncs2= new char[] {'：','、','，'}; //分隔其他符号
    private static char[] punce3= new char[] {',','，','、',':','：'}; //分隔其他

    /**
     * 根据标点符号进行断句
     * @param puncs
     * @return
     */

    public static void setPuncs(char[] puncs) {
        Sentenizer.puncs = puncs;
    }

    /**
     * 分隔句子
     * @param sent
     * @return
     */
    public static String[] split(String sent) {
        List<Integer> plist = new ArrayList<Integer>();
        int p = 0;
        for (int i = 0; i < puncs.length; i++) {
            p = sent.indexOf(puncs[i]);
            while (p != -1) {
                plist.add(p);
                p = sent.indexOf(puncs[i], p + 1);
            }
        }
        Collections.sort(plist);
        if (!plist.isEmpty()) {
            p = plist.get(plist.size() - 1);
            if (p < sent.length() - 1)
                plist.add(sent.length() - 1);
        }else	{
            plist.add(sent.length() - 1);
        }

        String[] ret = new String[plist.size()];
        p = 0;
        for (int i = 0; i < plist.size(); i++) {
            ret[i] = sent.substring(p, plist.get(i) + 1);
            p = plist.get(i) + 1;
        }
        plist.clear();

        return ret;
    }

    /**
     * 分隔逗号之类
     * @param sent
     * @return
     */
    public static String[] split2(String sent) {
        List<Integer> plist = new ArrayList<Integer>();
        int p = 0;
        for (int i = 0; i < puncs2.length; i++) {
            p = sent.indexOf(puncs2[i]);
            while (p != -1) {
                plist.add(p);
                p = sent.indexOf(puncs2[i], p + 1);
            }
        }
        Collections.sort(plist);
        if (!plist.isEmpty()) {
            p = plist.get(plist.size() - 1);
            if (p < sent.length() - 1)
                plist.add(sent.length() - 1);
        }else	{
            plist.add(sent.length() - 1);
        }

        String[] ret = new String[plist.size()];
        p = 0;
        for (int i = 0; i < plist.size(); i++) {
            ret[i] = sent.substring(p, plist.get(i) + 1);
            p = plist.get(i) + 1;
        }
        plist.clear();

        return ret;
    }

    /**
     * 分隔逗号之类
     * @param sent
     * @return
     */
    public static String[] split3(String sent) {
        List<Integer> plist = new ArrayList<Integer>();
        int p = 0;
        for (int i = 0; i < punce3.length; i++) {
            p = sent.indexOf(punce3[i]);
            while (p != -1) {
                plist.add(p);
                p = sent.indexOf(punce3[i], p + 1);
            }
        }
        Collections.sort(plist);
        if (!plist.isEmpty()) {
            p = plist.get(plist.size() - 1);
            if (p < sent.length() - 1)
                plist.add(sent.length() - 1);
        }else	{
            plist.add(sent.length() - 1);
        }

        String[] ret = new String[plist.size()];
        p = 0;
        for (int i = 0; i < plist.size(); i++) {
            ret[i] = sent.substring(p, plist.get(i) + 1);
            p = plist.get(i) + 1;
        }
        plist.clear();

        return ret;
    }

    /**
     * 分隔逗号之类
     * @param sent
     * @return
     */
    public static String[] split4(String sent, char[] punce_1) {
        List<Integer> plist = new ArrayList<Integer>();
        int p = 0;
        for (int i = 0; i < punce_1.length; i++) {
            p = sent.indexOf(punce_1[i]);
            while (p != -1) {
                plist.add(p);
                p = sent.indexOf(punce_1[i], p + 1);
            }
        }
        Collections.sort(plist);
        if (!plist.isEmpty()) {
            p = plist.get(plist.size() - 1);
            if (p < sent.length() - 1)
                plist.add(sent.length() - 1);
        }else	{
            plist.add(sent.length() - 1);
        }

        String[] ret = new String[plist.size()];
        p = 0;
        for (int i = 0; i < plist.size(); i++) {
            ret[i] = sent.substring(p, plist.get(i) + 1);
            p = plist.get(i) + 1;
        }
        plist.clear();

        return ret;
    }


    /**
     * 分
     * @param sent
     * @param punce_1
     * @return
     */
    public static String[] split5(String sent, char[] punce_1) {
        List<Integer> plist = new ArrayList<Integer>();
        int p = 0;
        for (int i = 0; i < punce_1.length; i++) {
            p = sent.indexOf(punce_1[i]);
            while (p != -1) {
                plist.add(p);
                p = sent.indexOf(punce_1[i], p + 1);
            }
        }
        Collections.sort(plist);
        if (!plist.isEmpty()) {
            p = plist.get(plist.size() - 1);
            if (p < sent.length() - 1)
                plist.add(sent.length() - 1);
        }else	{
            plist.add(sent.length() - 1);
        }

        String[] ret = new String[plist.size()];
        p = 0;
        for (int i = 0; i < plist.size(); i++) {
            if (i < plist.size() - 1) {
                ret[i] = sent.substring(p, plist.get(i));
            }else{
                ret[i] = sent.substring(p, plist.get(i)+1);
            }

            p = plist.get(i) + 1;
        }
        plist.clear();

        return ret;
    }
}
