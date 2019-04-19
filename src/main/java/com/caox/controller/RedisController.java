package com.caox.controller;

import com.baofoo.ma.query.facade.model.Result;
import com.baofoo.ma.query.facade.model.respone.MemberOpenResDTO;
import com.caox.service.IRedisService;
import com.caox.service.SyncService;
import com.caox.service.impl.RemoteServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/1/3 9:48
 */
@Controller
public class RedisController {
    @Resource
    private IRedisService iRedisService;

    @Resource
    private SyncService syncService;

    @Resource
    private RemoteServiceImpl remoteService;

    @Value("${abc}")
    private String abc;

    @RequestMapping("/helloApollo")
    @ResponseBody
    public String sayApollo(){
        Result<List<MemberOpenResDTO>> result =  remoteService.queryMerchantOpenInfoByAccountName("85187209@qq.com");
        return "hello "+abc + result.getResult();
    }

    @GetMapping("/get")
    @ResponseBody
    public String test() {
        return iRedisService.get("hello");
    }
//
    @GetMapping("/set")
    @ResponseBody
    public String set() {
        iRedisService.set("hello", "world");
        return "Set Ok!";
    }

    @GetMapping("/sync")
    @ResponseBody
    public String sync() {
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(syncService.getIndex());
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(syncService.getIndex());
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(syncService.getIndex());
            }
        }).start();
        return String.valueOf(syncService.getIndex());
    }
}
