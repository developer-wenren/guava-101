package com.one.guava.collections;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import java.util.Set;

/**
 *  不可变集合
 * @author One
 * @date 2022/05/03
 */
public class ImmutableCollectionsDemo {
    public static void main(String[] args) {
        // 不允许 null`
        ImmutableList<String> l1 = ImmutableList.of("a", "b");
        System.out.println(l1);
        ImmutableList<String> l2 = ImmutableList.<String>builder().add("c", "d").build();
        System.out.println(l2);
        ImmutableList<String> l3 = COLOR_NAMES.asList();
        System.out.println(l3);
        System.out.println(l3.get(0));
    }
    public static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(
            "red",
            "orange",
            "yellow",
            "green",
            "blue",
            "purple");

    class Foo {
        final ImmutableSet<Bar> bars;
        Foo(Set<Bar> bars) {
            this.bars = ImmutableSet.copyOf(bars); // defensive copy!
        }
    }

    /**
     * @author One
     * @date 2022/05/03
     */
    public static class Bar {
    }
}
