package com.sys.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    /**
     * 获取当前用户
     * @return
     */
    public static Authentication getCurrentUserAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前用户
     * @return
     */
    public static Object getCurrentPrincipal(){
        return getCurrentUserAuthentication().getPrincipal();
    }
}
