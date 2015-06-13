package edu.ecnu.teisei.test.algo.hash;//package hash;
//
//import edu.ecnu.teisei.algo.structure.domain.Key;
//import edu.ecnu.teisei.algo.structure.domain.Person;
//import org.junit.Test;
//
//import java.util.*;
//
///**
// * Created by Teisei on 2015/3/27.
// */
//public class TestHashTable {
//
//    @Test
//    public void testOne() {
//        List<Person> table = new ArrayList<Person>();
//        int[] a = new int[100];
//        int[] aa = new int[100];
//        for (int i = 0; i < 100; i++) {
//            a[i] = i;
//        }
//        for (int i = 0; i < 100; i++) {
//            aa[i] = i;
//        }
//        Person[] b = new Person[a.length + aa.length];
//        for (int i = 0; i < a.length; i++) {
//            b[i] = new Person("" + a[i]);
//        }
//        for (int i = 0; i < aa.length; i++) {
//            b[a.length+i] = new Person("" + aa[i]);
//        }
//
//
//        for (int i = 0; i < b.length; i++) {
//            table.add(i, b[i]);
//        }
//        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//
//        for (int i = 0; i < b.length; i++) {
//            int id = table.get(i).hashCode();
//            if (map.containsKey(id)) {
//                map.put(id, map.get(id) + 1);
//            } else {
//                map.put(id, 1);
//            }
//        }
//
//        int i=0;
//        Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet().iterator();
//        while (iter.hasNext()) {
//            System.out.print(++i+"  ");
//            Map.Entry<Integer, Integer> each = iter.next();
//            System.out.println(each.getKey() + "   " + each.getValue());
//        }
//
//    }
//    public static void main(String args[]) {
//        Hashtable<Key, Person> table = new Hashtable<Key, Person>();
//
//        int[] a = new int[100];
//        for (int i = 0; i < 100; i++) {
//            a[i] = i;
//        }
//        Key[] k = new Key[a.length];
//        Person[] b = new Person[a.length];
//        for (int i = 0; i < b.length; i++) {
//            k[i] = new Key(i);
//            b[i] = new Person("" + a[i]);
//        }
//        for (int i = 0; i < b.length; i++) {
//            table.put(k[i], b[i]);
//            System.out.println(k[i].hashCode());
//        }
//    }
//
//    @Test
//    public void testTwo() {
//        LinkedHashMap<Key, Person> table = new LinkedHashMap<Key, Person>();
//
//        int[] a = new int[100];
//        for (int i = 0; i < 100; i++) {
//            a[i] = i;
//        }
//        Key[] k = new Key[a.length];
//        Person[] b = new Person[a.length];
//        for (int i = 0; i < b.length; i++) {
//            k[i] = new Key(i);
//            b[i] = new Person("" + a[i]);
//        }
//        for (int i = 0; i < b.length; i++) {
//            table.put(k[i], b[i]);
//            System.out.println(k[i].hashCode());
//        }
//    }
//
//    @Test
//    public void testHashMap() {
//        HashMap<Key, Person> table = new HashMap<Key, Person>();
//
//        int[] a = new int[8];
//        for (int i = 0; i < 8; i++) {
//            a[i] = i;
//        }
//        Key[] k = new Key[a.length];
//        Person[] b = new Person[a.length];
//        for (int i = 0; i < b.length; i++) {
//            k[i] = new Key(i);
//            b[i] = new Person("" + a[i]);
//        }
//        for (int i = 0; i < b.length; i++) {
//            table.put(k[i], b[i]);
//            System.out.println(k[i].hashCode());
//        }
//
//        table.containsKey(k[4]);
//        System.out.println();
//
//
//    }
//}
