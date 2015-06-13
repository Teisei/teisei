package edu.ecnu.teisei.algo.structure;

/**
 * Created by Teisei on 2015/5/22.
 */
public class LinkedType<T> {
    int key;
    T p;
    LinkedType next = null;

    public LinkedType() {

    }
    public LinkedType(int key, T p) {
        this.key = key;
        this.p = p;
    }

    public LinkedType getNext() {
        return next;
    }

    public void setNext(LinkedType next) {
        this.next = next;
    }

//    public boolean equals(LinkedType node) {
//        if (this.key == node.key && this.p.compareTo(node.p) == 0) {
//            return true;
//        }else {
//            return false;
//        }
//    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", p=" + p.toString() +
                '}';
    }
}
