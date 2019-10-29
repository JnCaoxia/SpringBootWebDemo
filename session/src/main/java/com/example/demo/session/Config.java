package com.example.demo.session;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/8/20 19:20
 */
@Configuration
@EnableRedisHttpSession
public class Config {
    /**
     *jedis简单配置
     * @return
     */
    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setPort(6379);
        connectionFactory.setHostName("192.168.80.150");
        return connectionFactory;
    }

    /**
     *CookieHttpSessionStrategy 配置
     * @return
     */
    @Bean
    public CookieHttpSessionStrategy cookieHttpSessionStrategy(){
        CookieHttpSessionStrategy cookieHttpSessionStrategy=new CookieHttpSessionStrategy();
        CustomerCookiesSerializer cookiesSerializer= new CustomerCookiesSerializer();
        cookiesSerializer.setDomainName("sumei.com");
        cookieHttpSessionStrategy.setCookieSerializer(cookiesSerializer);
        return cookieHttpSessionStrategy;
    }
}
