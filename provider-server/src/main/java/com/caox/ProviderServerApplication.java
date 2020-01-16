package com.caox;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableApolloConfig
@EnableDiscoveryClient
@MapperScan("com.caox.dal.mapper")
//@ImportResource("classpath:spring.xml")
@EnableFeignClients
@ServletComponentScan
@EnableDistributedTransaction
public class ProviderServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderServerApplication.class, args);
	}

}
