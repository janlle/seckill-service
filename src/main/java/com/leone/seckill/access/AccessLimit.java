package com.leone.seckill.access;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>
 *
 * @author leone
 **/
@Inherited
@Documented
@Target({ElementType.FIELD,ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {

    //标识 时间段
    int seconds();

    //标识 指定sec时间段内的访问次数限制
    int limit();

    boolean needLogin() default true;
}
