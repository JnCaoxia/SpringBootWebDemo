package com.caox.controller;

import com.caox.service.RibbonSaveImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/4/22 14:01
 */
@RestController
public class ConsumerController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RibbonSaveImpl ribbonSave;


    @RequestMapping("/hi")
    public String hello(){
        return restTemplate.getForObject("http://spring-boot-web/hi", String.class);
    }

    @RequestMapping("/test")
    public int test(){
        return ribbonSave.save("ribbon-result","ribbon-msg");
    }
}
