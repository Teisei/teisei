package edu.ecnu.teisei.algo.tree;

import java.util.List;


/**
 * a TrieTree, storage for dictionary.
 * Created by Teisei on 2015/3/27.
 * @author teisei
 * @version 1.0
 */
public class HashTrieTree<T> implements Comparable<HashTrieTree<T>> {


    /**
     * root of the TrieTree, containing no char.
     * */
    HashTrieNode<T> root = new HashTrieNode<T>();

    /**
     * add a word to this tree.
     * */
    public List<T> add(String word) {
        HashTrieNode<T> father = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (word.length() == i + 1) {
                father.add(c, 2);
            } else {
                father.add(c, 1);
            }
            father = father.getChild(c);
        }
        return father.getInfos();
    }
    public List<T> add(String word, List<T> info) {
        HashTrieNode<T> father = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (word.length() == i + 1) {
                father.add(c, 2);
            } else {
                father.add(c, 1);
            }
            father = father.getChild(c);
        }
        father.setInfos(info);
        return father.getInfos();
    }

    protected HashTrieNode<T> getNode(String word) {
        HashTrieNode<T> father = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (word.length() == i + 1) {
                if (!father.contain(c, 2))
                    return null;
            } else {
                if (!father.contain(c, 1))
                    return null;
            }
            father = father.getChild(c);
        }
        return father;
    }

    /**
     * if contain word.
     * */
    public boolean contain(String word) {
        HashTrieNode<T> res = getNode(word);
        if (res == null) {
            return false;
        } else {
            return true;
        }
    }

    public List<T> search(String word) {
        HashTrieNode<T> res = getNode(word);
        return res == null ? null : res.getInfos();
    }

    /**
     * delete a word.
     */
    public boolean delete(String word) {
        HashTrieNode<T> node = getNode(word);
        if (node == null) {
            return false;
        } else {
            if (node.getStatus() == 1) {
                return false;
            }else if (node.getStatus() == 2) {
                node.setStatus(1);
            } else {
                node.setStatus(1);
            }
        }
        return true;
    }
    @Override
    public int compareTo(HashTrieTree<T> o) {
        return 0;
    }

}
