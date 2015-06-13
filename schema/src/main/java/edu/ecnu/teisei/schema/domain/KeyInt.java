package edu.ecnu.teisei.schema.domain;

/**
 * Created by Teisei on 2015/3/27.
 */
public class KeyInt {
    int key;
    public KeyInt(int key) {
        this.key = key;
    }
    @Override
    public int hashCode() {
        return key % 1001;
    }
}
