package com.caox.service;

import com.caox.dal.mapper.RibbonSaveMapper;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/10/28 14:56
 */
@Slf4j
@Service
@Mapper
public class RibbonSaveImpl {

    @Autowired
    RibbonSaveMapper ribbonSaveMapper;

    @Autowired
    public RestTemplate restTemplate;

    @Transactional
    @LcnTransaction
    public int save(String result,String msg){
      int b = ribbonSaveMapper.save(result,msg);
        int c = restTemplate.getForObject("http://spring-boot-web/test", Integer.class);
        int a = 1/0;
      return b + c;
    }
}
