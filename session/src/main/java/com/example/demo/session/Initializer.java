package com.example.demo.session;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/8/20 19:22
 */
public class Initializer extends AbstractHttpSessionApplicationInitializer {
    public Initializer() {
        super(Config.class);
    }
}
