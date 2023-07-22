package com.kjetboy.annonation.reflection;

import java.lang.annotation.*;

/**
 * @author jet
 */

public class Test3 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class c1 = Class.forName("com.kjetboy.annonation.reflection.Test4");
        // 获取方法注解
        for (Annotation annotation:c1.getAnnotations()){
            System.out.println(annotation);
        }
        // 获取值
        Table table =  (Table) c1.getAnnotation(Table.class);
        System.out.println(table.value());
        // 属性
        java.lang.reflect.Field field= c1.getDeclaredField("name");
        Field  fa = field.getAnnotation(Field.class);
        System.out.println(fa.columnName());
        System.out.println(fa.type());
        System.out.println(fa.length());
        java.lang.reflect.Field field1= c1.getDeclaredField("id");
        Field  fb = field1.getAnnotation(Field.class);
        System.out.println(fb.columnName());
        System.out.println(fb.type());
        System.out.println(fb.length());
    }
}
/**
 * @author jet
 */
@Table("db_test4")
 class Test4 {
    @Field(columnName = "db_id",type = "int",length = 10)
    private int id;
    @Field(columnName = "db_name",type = "varchar",length = 60)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/**
 * @author jet
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Table{
    String value();
}
/**
 * @author jet
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Field{
    String columnName();
    String type();
    int length();
}