package com.caox.service;

import com.caox.dal.mapper.TestSaveMapper;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/10/28 14:56
 */
@Slf4j
@Service
@Mapper
public class TestSaveImpl{

    @Autowired TestSaveMapper testSave;

    @Transactional
    @LcnTransaction
    public int save(String result,String msg){
      int b = testSave.save(result,msg);
      return b;
    }
}
