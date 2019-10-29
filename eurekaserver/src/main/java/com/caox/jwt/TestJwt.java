package com.caox.jwt;

import io.jsonwebtoken.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/8/20 10:52
 */
public class TestJwt {

  //加密的
  private static final String SECRET_KEY = "123456789";

  public void jwtTest() throws InterruptedException {
         // 设置3秒后过期
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         long time = System.currentTimeMillis() + 30*60*1000;
         String jwt = this.buildJwt(new Date(time));
         System.out.println("jwt = " + jwt);
         // 验证token是否可用
         boolean isOk = this.isJwtValid(jwt);
         System.out.println(isOk);
     }

     public String buildJwt(Date exp) {
         String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, SECRET_KEY)
         //SECRET_KEY是加密算法对应的密钥，这里使用额是HS256加密算法
         .setExpiration(exp)     // expTime是过期时间
         .claim("name","wangtingjun").claim("age","18").claim("key", "vaule")
         //该方法是在JWT中加入值为vaule的key字段
         .compact();
         return jwt;
     }

     public boolean isJwtValid(String jwt) {
         try {
             //解析JWT字符串中的数据，并进行最基础的验证
              Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
             //SECRET_KEY是加密算法对应的密钥，jjwt可以自动判断机密算法
             .parseClaimsJws(jwt)
             //jwt是JWT字符串
             .getBody();
             System.out.println(claims);
             String vaule = claims.get("key", String.class);
             //获取自定义字段key
             // 判断自定义字段是否正确
             if ("vaule".equals(vaule)) {
                 return true;
             } else {
                 return false;
             }
         }
         //在解析JWT字符串时，如果密钥不正确，将会解析失败，抛出SignatureException异常，说明该JWT字符串是伪造的
         // 在解析JWT字符串时，如果‘过期时间字段’已经早于当前时间，将会抛出ExpiredJwtException异常，说明本次请求已经失效
         catch (SignatureException e) {
             return false;
         }catch (ExpiredJwtException e){
             return false;
         }
     }
}
