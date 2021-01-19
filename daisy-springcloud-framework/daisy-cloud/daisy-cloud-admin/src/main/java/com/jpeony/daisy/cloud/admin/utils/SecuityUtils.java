package com.jpeony.daisy.cloud.admin.utils;

import com.jpeony.daisy.cloud.admin.secuity.CurrentUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecuityUtils {
    public static CurrentUser getCurrentUser() {
        return (CurrentUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
