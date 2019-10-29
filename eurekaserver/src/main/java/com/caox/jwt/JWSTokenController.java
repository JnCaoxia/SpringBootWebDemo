package com.caox.jwt;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/8/19 17:54
 */
@RestController
public class JWSTokenController {
    /**
     * 生成token给前端存在Cookie
     * @param uid
     * @param response
     * @return
     */
    @RequestMapping("testJws")
    public String testJws(String uid, HttpServletResponse response){

        JSONTokenInfo jsonTokenInfo=new JSONTokenInfo(uid);
        String token = JWSTokenUtil.getToken(jsonTokenInfo, 300);
        response.addHeader("Set-Cookie","access_token="+token+"PATH=/;HttpOnly");
        System.out.println(token);
        return token;
    }

    /**
     * 根据token检查uuid
     * @param token
     * @return
     */
    @RequestMapping("testToken")
    public JSONTokenInfo testToken(String token){
        JSONTokenInfo jsonTokenInfo = JWSTokenUtil.getInstance(token);
        return  jsonTokenInfo;
    }
}
