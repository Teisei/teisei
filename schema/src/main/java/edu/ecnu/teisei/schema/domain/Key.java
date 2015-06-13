package edu.ecnu.teisei.schema.domain;

/**
 * Created by Teisei on 2015/3/27.
 */
public class Key {
    int value;

    public Key(int value) {
        this.value = value;
    }


    @Override
    public int hashCode() {
        return value % 7;
    }
}
