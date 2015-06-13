package edu.ecnu.teisei.schema.domain;

/**
 * represent a word.
 * Created by Teisei on 2015/3/27.
 */
public class Word {
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
