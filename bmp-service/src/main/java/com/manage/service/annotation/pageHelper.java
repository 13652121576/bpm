package com.manage.service.annotation;

import java.lang.annotation.*;

/**
 * @version : V1.0
 * @ClassName: com.manage.service.annotationpageHalper
 * @Description: pageHelper
 * @Auther: ydm
 * @Date: 2020/7/1516:58
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface pageHelper {
    String value() default "";
}
