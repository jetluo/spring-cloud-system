package com.cloud.kjetboy.server.demo.annotation;

import java.lang.annotation.*;

/**
 * @author jet
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Audit {
    String categoryCode() default "";

    String categoryName() default "";

    String parentName() default "";

    String eventLevel() default "1";

    String eventType() default "11";

    String optType() default "";

    boolean defaultAuditItem() default false;

    String message() default "";
}
