package com.trafficLab.modules.jingjia.enums;

/**
 * author jinsq
 *
 * @date 2019/6/3 9:48
 */
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Description {
    public String value() default "";
}
