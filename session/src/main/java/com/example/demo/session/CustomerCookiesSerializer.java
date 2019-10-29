package com.example.demo.session;

import org.springframework.session.web.http.DefaultCookieSerializer;
import javax.servlet.http.HttpServletRequest;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/8/20 19:07
 */
public class CustomerCookiesSerializer extends DefaultCookieSerializer {
    private String getCookiePath(HttpServletRequest request) {
        return "/";
    }
}
