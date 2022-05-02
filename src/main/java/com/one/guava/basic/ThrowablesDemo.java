package com.one.guava.basic;

import com.google.common.base.Throwables;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 异常处理
 * @author One
 * @date 2022/05/02
 */
public class ThrowablesDemo {
    public static void main(String[] args) throws IOException, SQLException {
        try {
            int i = 0 / 0;
        }catch (Exception e){
            String stackTraceAsString = Throwables.getStackTraceAsString(e);
            System.out.println(stackTraceAsString);
            e.printStackTrace();
        }

        try {
            someMethodThatCouldThrowAnything();
        } catch (Throwable t) {
            Throwables.throwIfInstanceOf(t, IOException.class);
            Throwables.throwIfInstanceOf(t, SQLException.class);
            Throwables.throwIfUnchecked(t);
            throw new RuntimeException(t);
        }
    }

    private static void handle(IKnowWhatToDoWithThisException e) {

    }

    private static void someMethodThatCouldThrowAnything() {

    }
}
