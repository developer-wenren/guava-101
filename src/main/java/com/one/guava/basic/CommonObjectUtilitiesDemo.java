package com.one.guava.basic;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * 公共对象工具
 * @author One
 * @date 2022/05/02
 */
public class CommonObjectUtilitiesDemo {
    public static void main(String[] args) {
        // 比较相等，jdk7 已引入
        Objects.equal("a", "a"); // returns true
        Objects.equal(null, "a"); // returns false
        Objects.equal("a", null); // returns false
        Objects.equal(null, null); // returns true

        // hashCode
        int code = Objects.hashCode(new User());
        System.out.println(code);

        // toString
        // Returns "ClassName{x=1}"
        String s = MoreObjects.toStringHelper(CommonObjectUtilitiesDemo.class)
                .add("x", 1)
                .add("y", 2)
                .toString();
        System.out.println(s);

        // 做比较
    }

    public static class User {
        public String name;
    }


    public static class Person implements Comparable<Person> {
        private String lastName;
        private String firstName;
        private int zipCode;

        public int compareTo(Person other) {
            return ComparisonChain.start()
                    .compare(this.firstName, other.firstName)
                    .compare(this.lastName, other.lastName)
                    .result();
        }
    }
}
