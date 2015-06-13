//package edu.ecnu.teisei.algo.hash;
//
//import edu.ecnu.teisei.algo.hash.functions.NumberHashFuncs;
//import edu.ecnu.teisei.algo.structure.domain.Person;
//import org.junit.Test;
//
///**
//* Created by Teisei on 2015/3/23.
// * @author Teisei
// * @version 1.0
//*/
//public class HashTable {
//    class Node{
//        int key;
//        Person p;
//        Node next = null;
//        Node(int key, Person p) {
//            this.key = key;
//            this.p = p;
//        }
//
//        public Node getNext() {
//            return next;
//        }
//
//        public void setNext(Node next) {
//            this.next = next;
//        }
//
//        public boolean equals(Node node) {
//            if (this.key == node.key && this.p.compareTo(node.p) == 0) {
//                return true;
//            }else {
//                return false;
//            }
//        }
//
//        @Override
//        public String toString() {
//            return "Node{" +
//                    "key=" + key +
//                    ", p=" + p.toString() +
//                    '}';
//        }
//    }
//
//    Node[] table;
//    int max_size;
//    NumberHashFuncs hashFunc;
//
//
//    public HashTable() {
//        hashFunc = new NumberHashFuncs();
//        hashFunc.setA(2.95804);
//        hashFunc.setM(1000);
//        hashFunc.setPrime(7);
//    }
//
//    public void setMax_size(int max_size) {
//        this.max_size = max_size;
//        table = new Node[max_size];
//
//    }
//
//
//
//    public void chained_hash_insert(Node node) {
//        int key = hashFunc.division(node.key);
//        if (table[key] == null) {
//            table[key] = node;
//        }else {
//            Node parent = table[key];
//            while (parent.next != null) {
//                parent = parent.next;
//            }
//            parent.next = node;
//        }
//    }
//
//    public Node chained_hash_search(int key) {
//        int new_key = hashFunc.division(key);
//        Node node = table[new_key];
//        while (node != null) {
//            if (node.key == key) {
//                return node;
//            }
//            node = node.next;
//        }
//        return null;
//    }
//
//    public Node chained_hash_delete(int key) {
//        int new_key = hashFunc.division(key);
//        Node parent = null;
//        Node node = table[new_key];
//        while (node != null) {
//            if (node.key == key) {
//                break;
//            }
//            parent = node;
//            node = node.next;
//        }
//        if (node!=null) {
//            if (parent != null) {
//                parent.next = node.next;
//            }
//            return node;
//        }
//        return null;
//    }
//
//    public void testPrint() {
//        System.out.println("**** print the table ****");
//        int i = 0;
//        for (Node node : table) {
//            if (node != null) {
//                System.out.println(node.toString());
//            }
//            i++;
//        }
//        System.out.println("\n\n\n");
//    }
//
//    @Test
//    public void testOne() {
//        HashTable hashTable = new HashTable();
//        hashTable.setMax_size(1000);
//
//        int[] key =new int[]{
//                1, 13, 27, 55, 103, 222, 768, 779, 1111, 2570
//        };
//        String[] name = new String[]{
//                "tom",
//                "cat",
//                "jack",
//                "queen",
//                "workspace",
//                "split",
//                "interface",
//                "extend",
//                "single",
//                "double"
//        };
//        Person person[] = new Person[10];
//        for (int i = 0; i < 10; i++) {
//            person[i] = new Person(name[i]);
//            hashTable.chained_hash_insert(new HashTable.Node(key[i], person[i]));
//        }
//        hashTable.testPrint();
//
//        int search_key = 1111;
//        Node res = hashTable.chained_hash_search(search_key);
//        System.out.println(res.toString());
//        hashTable.testPrint();
//    }
//
//    public static void main(String args[]) {
//
//    }
//
//}
