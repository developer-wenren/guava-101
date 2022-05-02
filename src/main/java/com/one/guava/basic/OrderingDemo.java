package com.one.guava.basic;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.List;

/**
 * 排序
 *
 * @author One
 * @date 2022/05/02
 */
public class OrderingDemo {
    public static void main(String[] args) {
        Ordering<String> byLengthOrdering = new Ordering<String>() {
            public int compare(String left, String right) {
                return Ints.compare(left.length(), right.length());
            }
        };
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("aa");
        list.add("a");
        boolean ordered = byLengthOrdering.nullsFirst().reverse().isOrdered(list);
        System.out.println(ordered);
        String min = byLengthOrdering.min(list);
        System.out.println(min);
        System.out.println(byLengthOrdering.min("1", "cd"));
        List<String> sorted = byLengthOrdering.sortedCopy(list);
        System.out.println(sorted);

        Ordering<Foo> ordering = Ordering.natural().nullsFirst().onResultOf((Function<Foo, String>) foo -> foo.sortedBy);

    }

    public static class Foo {
        String sortedBy;
        int notSortedBy;
    }
}
