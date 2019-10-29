package com.caox.controller;

import com.caox.service.TestSaveImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/4/22 17:02
 */
@Controller
public class ProviderController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private TestSaveImpl testSave;

    @RequestMapping(value = "hi")
    @ResponseBody
    public String hello(){
        return "hello world! I am from " + port;
    }

    @RequestMapping(value = "test")
    @ResponseBody
    public int save(){
//        String result = map.get("result");
//        String msg = map.get("lcn");
        String result = "test-lcn-222";
        String msg = "lcn-msg";
        return testSave.save(result, msg);
    }
}
