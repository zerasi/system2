package com.sys.utils.aop;

import java.lang.annotation.*;

// 在方法上使用
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StoredFile {
}
