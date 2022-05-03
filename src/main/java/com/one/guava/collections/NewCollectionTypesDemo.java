package com.one.guava.collections;

import com.google.common.collect.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 *  新集合
 * @author One
 * @date 2022/05/03
 */
public class NewCollectionTypesDemo {

    public static void main(String[] args) {
        multiset();
        multimap();
        bimap();
        table();
        classToInstanceMap();
        rangeset();
        rangemap();
    }

    private static void rangemap() {
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "foo"); // {[1, 10] => "foo"}
        rangeMap.put(Range.open(3, 6), "bar"); // {[1, 3] => "foo", (3, 6) => "bar", [6, 10] => "foo"}
        rangeMap.put(Range.open(10, 20), "foo"); // {[1, 3] => "foo", (3, 6) => "bar", [6, 10] => "foo", (10, 20) => "foo"}
        rangeMap.remove(Range.closed(5, 11)); // {[1, 3] => "foo", (3, 5) => "bar", (11, 20) => "foo"}
        System.out.println(rangeMap);
    }

    private static void rangeset() {
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10)); // {[1, 10]}
        rangeSet.add(Range.closedOpen(11, 15)); // disconnected range: {[1, 10], [11, 15)}
        rangeSet.add(Range.closedOpen(15, 20)); // connected range; {[1, 10], [11, 20)}
        rangeSet.add(Range.openClosed(0, 0)); // empty range; {[1, 10], [11, 20)}
        rangeSet.remove(Range.open(5, 10)); // splits [1, 10]; {[1, 5], [10, 10], [11, 20)}
        System.out.println(rangeSet);
    }

    private static void classToInstanceMap() {
        ClassToInstanceMap<Number> numberDefaults = MutableClassToInstanceMap.create();
        numberDefaults.putInstance(Integer.class, Integer.valueOf(0));
        numberDefaults.putInstance(Integer.class, Integer.valueOf(2));
        numberDefaults.putInstance(Long.class, Long.valueOf(2));
        System.out.println(numberDefaults);
    }

    private static void table() {
        Table<String, String, Integer> weightedGraph = HashBasedTable.create();
        weightedGraph.put("a", "A", 4);
        weightedGraph.put("a", "B", 41);
        weightedGraph.put("b", "B", 20);
        weightedGraph.put("c", "C", 5);

        Map<String, Integer> a = weightedGraph.row("a");// returns a Map mapping v2 to 4, v3 to 20
        System.out.println(a);
        Map<String, Integer> b = weightedGraph.column("B");// returns a Map mapping v1 to 20, v2 to 5
        System.out.println(b);
        Set<Table.Cell<String, String, Integer>> cells = weightedGraph.cellSet();
        System.out.println(cells);
    }

    private static void bimap() {
        BiMap<String, Integer> bimap = HashBiMap.create();
        bimap.put("a",1);
        bimap.put("b",2);
        bimap.put("b",2);
        // 不允许 value 对应有多个 key，否则 IllegalArgumentException
//        bimap.put("a",2);
        bimap.putIfAbsent("a", 2);
        System.out.println(bimap);
        System.out.println(bimap.inverse());
        System.out.println(bimap.inverse().get(1));
        System.out.println(bimap.get("a"));
    }

    private static void multimap() {
// creates a ListMultimap with tree keys and array list values
        ListMultimap<String, Integer> treeListMultimap =
                MultimapBuilder.treeKeys().arrayListValues().build();
        System.out.println(treeListMultimap);

// creates a SetMultimap with hash keys and enum set values
        SetMultimap<Integer, MyEnum> hashEnumMultimap =
                MultimapBuilder.hashKeys().enumSetValues(MyEnum.class).build();
        hashEnumMultimap.put(1,MyEnum.A);
        hashEnumMultimap.put(2,MyEnum.B);
        hashEnumMultimap.put(1,MyEnum.B);
        System.out.println(hashEnumMultimap);
        Map<Integer, Collection<MyEnum>> map = hashEnumMultimap.asMap();
        System.out.println(map);
        SetMultimap<MyEnum, @Nullable Object> enumMappingSet = MultimapBuilder.enumKeys(MyEnum.class).hashSetValues().build();
        enumMappingSet.put(MyEnum.A,"a");
        enumMappingSet.put(MyEnum.A,"A");
        enumMappingSet.put(MyEnum.B,"b");
        System.out.println(enumMappingSet);

        System.out.println(enumMappingSet.get(MyEnum.A));
        System.out.println(enumMappingSet.get(MyEnum.A).add("1"));
        System.out.println(enumMappingSet.get(MyEnum.A));
        System.out.println(enumMappingSet);
    }

    private static void multiset() {
        // 统计个数set,不去重
        HashMultiset<String> objects = HashMultiset.<String>create();
        objects.add("a");
        objects.add("b");
        objects.add("a");
        objects.add("b");
        objects.add("b");
        objects.add("b");
        System.out.println(objects);
        int a = objects.count("a");
        System.out.println(a);
        System.out.println(objects.add("c", 4));
        System.out.println(objects.add("c", 3));
        System.out.println(objects.remove("c", 2));

        Object[] objects1 = objects.toArray();
        System.out.println(Arrays.toString(objects1));
        for (String object : objects) {
            System.out.println(object);
        }
        System.out.println(objects.elementSet());
        System.out.println(objects.entrySet());
        System.out.println(objects.size());
    }

    /**
     * @author One
     * @date 2022/05/03
     */
    public enum MyEnum {
        A,B;
    }
}
