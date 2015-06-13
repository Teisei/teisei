package edu.ecnu.teisei.test.algo.structure.tree;

import edu.ecnu.teisei.algo.tree.HashTrieTree;
import edu.ecnu.teisei.algo.tree.tire.domain.AppendForest;
import edu.ecnu.teisei.utils.MyFile;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Teisei on 2015/3/27.
 */
public class Test_HashTrieTree {

    class Word {
        /*
        the str of this word.
         */
        String str;

        /*
        part of speech of this word.
         */
        String pos;

        /*
        description of this word.
         */
        String dec;

        public Word(String str, String pos, String dec) {
            this.str = str;
            this.pos = pos;
            this.dec = dec;
        }

        public String getStr() {

            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        public String getPos() {
            return pos;
        }

        public void setPos(String pos) {
            this.pos = pos;
        }

        public String getDec() {
            return dec;
        }

        public void setDec(String dec) {
            this.dec = dec;
        }
    }


    @Test
    public void testOne() throws IOException {
        String path = "./data/library/default.dic";
        MyFile myFile = new MyFile(path, "utf-8");

        List<String> lines = new ArrayList<String>();

        String line = myFile.readLine();

        while (line != null) {
            lines.add(line);
            line = myFile.readLine();
        }

        int dict_start = 0;
        int dict_size = lines.size();

        Date start = new Date();
        HashTrieTree<Word> tree = new HashTrieTree<Word>();
        for (String each : lines) {
            String record[] = each.split("\t");
            String str = record[0];
            String pos = record[1];
            String weight = record[2];

            List<Word> infos = new ArrayList<Word>();
            infos.add(new Word(str, pos, weight));
            tree.add(str, infos);
        }
        System.out.println("time for HashTrieTree: " + (new Date().getTime() - start.getTime()));

        start = new Date();
        for (int i = dict_start; i < dict_start + dict_size; i++) {
            String record[] = lines.get(i).split("\t");
            String str = record[0];
            tree.search(str);
        }
        System.out.println("search time for HashTrieTree: " + (new Date().getTime() - start.getTime()));



        start = new Date();
        AppendForest<Word> tree2 = new AppendForest<Word>();
        for (String each : lines) {
            String record[] = each.split("\t");
            String str = record[0];
            String pos = record[1];
            String weight = record[2];

            tree2.add(str, new Word(str, pos, weight));
        }
        System.out.println("time for AppendForest: " + (new Date().getTime() - start.getTime()));

        start = new Date();
        for (int i = dict_start; i < dict_start + dict_size; i++) {
            String record[] = lines.get(i).split("\t");
            String str = record[0];
            tree2.getBranch(str);
        }
        System.out.println("search time for AppendForest: " + (new Date().getTime() - start.getTime()));




//        start = new Date();
//        Dictionary dict = new Dictionary();
//        for (String each : lines) {
//            String record[] = each.split("\t");
//            String str = record[0];
//            String pos = record[1];
//            String weight = record[2];
//
//            dict.add(str, pos, weight);
//        }
//        System.out.println("time for TrieTree: " + (new Date().getTime() - start.getTime()));



    }
}
