package com.leone.seckill.common;

import java.lang.annotation.*;

/**
 * <p>
 *
 * @author leone
 * @since 2019-04-17
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VersionFlag {

    String version() default "0.0";

}
