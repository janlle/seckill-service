package com.leone.seckill.access;

import java.lang.annotation.*;

/**
 * <p>
 *
 * @author leone
 **/
@Inherited
@Documented
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {

    // 标识 时间段
    int seconds();

    // 标识 指定sec时间段内的访问次数限制
    int limit();

    // 是否需要登录访问
    boolean needLogin() default true;
}
