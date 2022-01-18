package com.xl.demo.annotation;

import java.lang.annotation.*;

/**
 * @author XL
 * 自定义log记录注解
 * @date 2022/1/14 10:51
 */
@Target( {ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /** 标题 */
    String title() default "";

    String type() default "其他";


}
