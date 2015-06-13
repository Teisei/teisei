package edu.ecnu.teisei.schema.domain;

import edu.ecnu.teisei.utils.StringUtil;

/**
 * Created by Teisei on 2015/5/22.
 */
public class Term implements Comparable<Term> {
    String str;
    int frequency;

    public Term(String line) {
        String res[] = line.split(StringUtil._read_split_);
        this.str = res[0];
        this.frequency = Integer.parseInt(res[1]);
    }

    public Term(String str, int frequency) {
        this.str = str;
        this.frequency = frequency;
    }


    public String getStr() {
        return str;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return str + StringUtil._write_split_ + frequency;
    }

    public int compareTo(Term o) {
        if (o.frequency > this.frequency) {
            return -1;
        } else if (o.frequency == this.frequency) {
            return 0;
        } else {
            return 1;
        }
    }
}
