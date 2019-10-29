package com.caox;

import com.baofoo.ma.query.facade.model.Result;
import com.baofoo.ma.query.facade.model.respone.MemberOpenResDTO;
//import com.baofu.rm.oms.facade.aml.customer.dto.request.NotificationGradDto;
//import com.caox.service.impl.RemoteServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootWebDemoApplicationTests {

//	@Resource
//	RemoteServiceImpl remoteService;

	@Value("${spring.dubbo.registry.address}")
	private String registerAddress;


	@Test
	public void contextLoads() {
		log.info("abc:"+registerAddress);
	}

//	@Test
//	public void testQueryInfoByAccountName(){
//		String accountName = "85187209@qq.com";
//		Result<List<MemberOpenResDTO>> result = remoteService.queryMerchantOpenInfoByAccountName(accountName);
//		log.info("call testQueryInfoByAccountName | RESULT : {}", result);
//		NotificationGradDto notificationGradDto = new NotificationGradDto();
//		remoteService.notice(notificationGradDto);
//	}

}

