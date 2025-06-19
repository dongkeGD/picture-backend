package com.demo.picturebackend.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author:Thesy <a href="https://github.com/dongkeGD">dongkeGD</a>
 * @Create:2025/6/19 - 17:01
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {

    /**
     * 必须具有某个角色
     */
    String mustRole() default "";
}
