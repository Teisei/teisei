package edu.ecnu.teisei.utils.filter;

import edu.ecnu.teisei.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A simple filter to filter line_format data.
 * Created by Teisei on 2015/5/22.
 */
public class SimpleFilter {
    Set<String> dict = new HashSet<>();

    /**
     * Load a dictionary for filter or select.
     */
    public void load(List<String> words) {
        for (String e : words) {
            dict.add(e);
        }
    }
    public void load(String[] words) {
        for (String e : words) {
            dict.add(e);
        }
    }

    /**  filter */
    public List<Object> filter(List<Object> objectsList, int column) {
        List<Object> res = new ArrayList<>();
        for (Object o : objectsList) {
            String record[] = o.toString().split(StringUtil._read_split_);
            if (!dict.contains(record[column])) {
                res.add(o);
            }
        }
        return objectsList;
    }

    /**  select */
    public List<Object> select(List<Object> objectsList, int column) {
        List<Object> res = new ArrayList<>();
        for (Object o : objectsList) {
            String record[] = o.toString().split(StringUtil._read_split_);
            if (dict.contains(record[column])) {
                res.add(o);
            }
        }
        return res;
    }
}
