package com.caox.controller;

import com.caox.service.ConsumerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/4/22 16:33
 */
@RestController
public class ConsumerController {
    @Autowired
    ConsumerService consumerService;

    @RequestMapping(value="/test", method = RequestMethod.GET)
    public int test(){
        return consumerService.testFromProvider();
    }

    @ApiOperation(value = "获取服务提供端口号", notes = "获取服务提供端口号")
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String hi(){
        return consumerService.hiFromProvider();
    }


}
