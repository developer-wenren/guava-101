package com.one.guava.basic;

import com.google.common.base.Preconditions;

import static com.google.common.base.Preconditions.*;

/**
 * 参数检查
 * @author One
 * @date 2022/05/02
 */
public class PreconditionsDemo {
    public static void main(String[] args) {
        int i = 10, j = 11;
        checkArgument(i >= 0, "Argument was %s but expected nonnegative", i);
        checkArgument(i < j, "Expected i < j, but %s >= %s", i, j);
        checkArgument2(args);
        checkNull(args);
        checkState(args);
        checkIndex(args);
    }

    private static void checkIndex(String[] args) {
        int index = 2;
        int size = 2;
        try {
            index = checkPositionIndex(index, size); // 元素位置，包含 size
            index = checkElementIndex(index, size); // 元素索引， 不包含 size
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 状态检查
     * @param args
     */
    private static void checkState(String[] args) {
        try {
            User user = new User("one");
            Preconditions.checkState(user.isAlive(), "user %s is not alive", user.getName());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 空值检查
     * @param args
     */
    private static void checkNull(String[] args) {
        try {
            User user = new User("one");
            Long id = checkNotNull(user.getId(), "user %s id is null", user.getName());
            checkNotNull(null, "object is null");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 表达式检查
     * @param args
     */
    public static void checkArgument2(String[] args) {
        try {
            Preconditions.checkArgument(1 > 2, "1 > 2 is false");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class User {
        private Long id;

        private boolean alive;

        private final String name;

        public User(String name) {
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public boolean isAlive() {
            return alive;
        }

        public void setAlive(boolean alive) {
            this.alive = alive;
        }

        public String  getName() {
            return name;
        }
    }
}
