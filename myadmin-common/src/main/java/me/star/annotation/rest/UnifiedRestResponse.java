package me.star.annotation.rest;

import java.lang.annotation.*;

/**
 * 统一Rest结果注解
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface UnifiedRestResponse {

    boolean formatResult() default true;
}
