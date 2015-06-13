package edu.ecnu.teisei.schema.domain;

/**
 * Created by dingcheng on 2015/3/14.
 */
public class Person implements Comparable<Person> {
    String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    public int compareTo(Person o) {
        return this.name.compareTo(o.getName());
    }

//    @Override
//    public int compareTo(Object o) {
//        return this.name.compareTo(((Person) o).getName());
//    }

}
