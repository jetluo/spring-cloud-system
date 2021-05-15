package com.kjetboy.annonation;

import java.lang.annotation.*;

/**
 * 定义注解
 * @author jet
 */
@Target(value = {ElementType.METHOD,ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Inherited //子类可以继承主类的注解
public @interface Audit {

    AuditType[] value() default  AuditType.METHOD;

    String audit() default  "啥都不是";
}
