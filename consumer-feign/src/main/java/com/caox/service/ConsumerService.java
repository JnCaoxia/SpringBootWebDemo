package com.caox.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/4/22 16:34
 */
@FeignClient(value = "spring-boot-web")
public interface ConsumerService {
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String hiFromProvider();
}
