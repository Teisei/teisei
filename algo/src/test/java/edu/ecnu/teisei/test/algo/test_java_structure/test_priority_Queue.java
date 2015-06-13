package edu.ecnu.teisei.test.algo.test_java_structure;

import org.junit.Test;

import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Created by Teisei on 2015/3/29.
 */
public class test_priority_Queue {
    public static void main(String args[]) {
    }

    @Test
    public void testOne() {
        PriorityQueue<Haha> que = new PriorityQueue<>();
        que.add(new Haha(1, 3));
        que.add(new Haha(2, 1));
        que.add(new Haha(3, 4));
        que.add(new Haha(4, 2));
        que.add(new Haha(5, 5));
//        System.out.println(que.peek().getKey() + " " + que.peek().getValue());
        print(que);
        print2(que);
        print2(que);
        print2(que);



//        que.add(new Haha(5, 5));

    }

    public void print(PriorityQueue<Haha> que) {
        Iterator<Haha> iter = que.iterator();
        while (iter.hasNext()) {
            Haha each = iter.next();
            System.out.print(each.getKey() + ":" + each.getValue()+"   ");
        }
        System.out.println();
    }

    public void print2(PriorityQueue<Haha> que) {
        while (!que.isEmpty()) {
            Haha each = que.poll();
            System.out.print(each.getKey() + ":" + each.getValue() + "   ");
        }
        System.out.println();
    }

    public class Haha implements Comparable<Haha> {
        int key;
        int value;

        public int getKey() {
            return key;
        }

        public Haha(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        @Override
        public int compareTo(Haha o) {
            if (this.value < o.getValue()) {
                return -1;
            }else if (this.value == o.getValue()) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
