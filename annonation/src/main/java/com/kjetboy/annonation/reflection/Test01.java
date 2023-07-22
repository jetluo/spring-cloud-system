package com.kjetboy.annonation.reflection;

import com.kjetboy.annonation.Audit;

import java.lang.reflect.Field;

/**
 * @author jet
 */
public class Test01 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class c1 = Audit.class;
        ClassLoader classLoader = Class.forName("java.lang.Object").getClassLoader();
        System.out.println(classLoader);
        ClassLoader classLoader1 = Class.forName("com.kjetboy.annonation.AuditType").getClassLoader();
        System.out.println(classLoader1);

        Class user = Class.forName("com.kjetboy.annonation.reflection.User");
        User user1 = (User) user.newInstance();
        Field name= user.getDeclaredField("name");
        name.setAccessible(true);
        name.set(user1,"共匪");
        System.out.println(user1.getName());

    }
}
