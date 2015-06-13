package edu.ecnu.teisei.algo.tree;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Teisei on 2015/3/27.
 * @author Teisei
 * @version 1.0
 */
public class HashTrieNode<T> implements Comparable<HashTrieNode<T>> {

    char c;
    int status = -1;//1.continue, not a word; 2.is a word, end. 3.continue and is a word and can ;
    List<T> infos;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    HashMap<Character, HashTrieNode<T>> table;

    public HashTrieNode() {
        table = new HashMap<Character, HashTrieNode<T>>();
    }

    public HashTrieNode(char c, int status) {
        this.c = c;
        this.status = status;
        table = new HashMap<Character, HashTrieNode<T>>();
    }

    public List<T> getInfos() {
        return infos;
    }

    public void setInfos(List<T> infos) {
        this.infos = infos;
    }

    public void add(char c, int status) {
        if (table == null) {
            table = new HashMap<Character, HashTrieNode<T>>();
        }
        if (table.containsKey(c)) {
            HashTrieNode<T> node = table.get(c);
            if ((node.getStatus() == 2 && status == 1)||(node.getStatus() == 1 && status == 2)) {
                // change to 3
                node.setStatus(3);
            }
        } else {
            table.put(c, new HashTrieNode<T>(c, status));
        }
    }

    public boolean contain(char c, int status) {
        if (table.containsKey(c)) {
            int node_status = table.get(c).getStatus();
            if (node_status == 3 || node_status == status) {
                return true;
            }
        }
        return false;
    }
    public boolean contain(char c) {
        return table.containsKey(c);
    }

    /**
     * �������ڵ��� c ��ʼ���ӷ�֧
     */
    public HashTrieNode<T> getChild(char c) {
        return this.table.get(c);
    }

    @Override
    public int compareTo(HashTrieNode<T> o) {
        return 0;
    }
}
