package edu.ecnu.teisei.utils;

/**
 * Created by Teisei on 2015/5/22.
 */
public class test_ImportTool {
    public static void main(String args[]) {
        String path = "./library/stopword.txt";
        String encode = "utf-8";
        String res[] = ImportTool.getDataByLine(path, "\r\n", encode);
    }
}
