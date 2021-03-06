package com.caox;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;


@CrossOrigin  //允许跨越访问
@SpringBootApplication
@EnableApolloConfig
@EnableDubboConfiguration
@EnableEurekaClient
public class SpringBootWebDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebDemoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}

