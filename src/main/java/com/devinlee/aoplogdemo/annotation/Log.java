package com.devinlee.aoplogdemo.annotation;

import com.devinlee.aoplogdemo.enums.OperateType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    /**
     * 方法描述,可使用占位符获取参数:{{tel}}
     */
    String desc();

    /**
     * 日志等级:自己定，此处分为0-9
     */
    int level() default 0;

    /**
     * 操作类型
     */
    OperateType operateType() default OperateType.UNKNOWN;

    /**
     * 备注
     */
    String remark() default "";
}
