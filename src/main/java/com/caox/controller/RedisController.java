package com.caox.controller;

import com.baofoo.ma.query.facade.model.Result;
import com.baofoo.ma.query.facade.model.respone.MemberOpenResDTO;
import com.caox.service.IRedisService;
import com.caox.service.SyncService;
import com.caox.service.impl.RemoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    RestTemplate restTemplate;

    @Value("${abc}")
    private String abc;

    @Value("${server.port}")
    private String port;
    @RequestMapping(value = "hi")
    @ResponseBody
    public String hello(){
        return "hello world! I am from " + port;
    }

    @RequestMapping("/testLoadBalancerClient")
    @ResponseBody
    public String testLoadBalancerClient(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("spring-boot-web");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/hi";
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }

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
