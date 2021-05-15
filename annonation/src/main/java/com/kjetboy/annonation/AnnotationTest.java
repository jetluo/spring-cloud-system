package com.kjetboy.annonation;


/**
 * @author jet
 */
public class AnnotationTest {

    @Audit(value=AuditType.FIELD,audit="我是属性")
    private String name;

    @Audit(value=AuditType.METHOD,audit="我在方法上")
    public void test(){
        System.out.println("audit测试");
    }
    /**
     * 有默认值
     */
    @Audit
    public void test2(){
        System.out.println("audit测试");
    }

    @Override
    public String toString(){
        return "";
    }
    public static void main(String[] args) {
        AnnotationTest annotationTest=new AnnotationTest();
        annotationTest.test();
    }
}
