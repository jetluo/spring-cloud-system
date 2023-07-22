package com.kjetboy.annonation.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test02 {

    public static void main(String[] args) {
        v1();
        try {
            v2();
            try {
                v3();
                v4();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void v1() {
        User user = new User();
        user.setName("aaa");
        long time = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            user.getName();
        }
        long endtime = System.currentTimeMillis();
        System.out.println("普通执行时间：" + (endtime - time) + "ms");
    }
    public static void v2() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class user = Class.forName("com.kjetboy.annonation.reflection.User");
        User user1 = (User) user.newInstance();
        Field name = user.getDeclaredField("name");
        name.setAccessible(true);
        name.set(user1, "共匪");
        long time = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            user1.getName();
        }
        long endtime = System.currentTimeMillis();
        System.out.println("反射执行时间：" + (endtime - time) + "ms");
    }
    public static void v3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        Class c1 = user.getClass();
        Method getName = c1.getDeclaredMethod("getName",null);
        getName.setAccessible(true);
        long time = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            getName.invoke(user,null);
        }
        long endtime = System.currentTimeMillis();
        System.out.println("关闭反射执行时间：" + (endtime - time) + "ms");
    }
    public static void v4() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        Class c1 = user.getClass();
        Method getName = c1.getDeclaredMethod("getName",null);
        //getName.setAccessible(true);
        long time = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
           getName.invoke(user,null);
        }
        long endtime = System.currentTimeMillis();
        System.out.println("关闭反射执行时间：" + (endtime - time) + "ms");
    }
}
