package com.cloud.kjetboy.server.audit.annotation;

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

    String eventLevel() default "2";

    String eventType() default "11";

    String optType() default "";

    boolean defaultAuditItem() default false;

    String message() default "";

    String messageTemplate() default "{userName!'unknown'}]{(startTimeAsDate?string('yyyy-MM-dd HH:mm:ss'))!}]{ip!''}]{commonAttribute.category.categoryName!''}]{commonAttribute.operationName!''}]{commonAttribute.operationStatus!''}]";

    String messageTemplateCode() default "";

    String analysis() default "0";
}
