package com.github.fashionbrot.common.annotation;

import java.lang.annotation.*;

/**
 * 是否开启日志持久化
 */
@Documented
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PersistentLog {

    /**
     * 是否持久化到 mysql
     * @return
     */
    boolean persistent() default true;

}
