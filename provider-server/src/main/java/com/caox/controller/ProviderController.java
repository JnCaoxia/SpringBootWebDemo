package com.caox.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/4/22 17:02
 */
@Controller
public class ProviderController {

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "hi")
    @ResponseBody
    public String hello(){
        return "hello world! I am from " + port;
    }
}
