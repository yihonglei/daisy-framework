package com.jpeony.daisy.cloud.admin.utils;

import com.jpeony.daisy.cloud.admin.dao.TokenDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class TokenUtils {
    @Autowired
    TokenDao tokenDao;

    String getCurrent(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

}
