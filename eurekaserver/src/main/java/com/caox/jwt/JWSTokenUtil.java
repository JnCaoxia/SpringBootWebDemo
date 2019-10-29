package com.caox.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/8/19 17:48
 */
public class JWSTokenUtil {
    private static String jwt_key_user_id="uid";

    /**
     * 生成一个加密key
     * @return
     */
    public static Key getKey(){
        SignatureAlgorithm es256 = SignatureAlgorithm.HS256;
        byte[] bytes= DatatypeConverter.parseBase64Binary("secret key");
        SecretKeySpec secretKeySpec = new SecretKeySpec(bytes,es256.getJcaName());
        return secretKeySpec;
    }

    /**
     * 生成token
     * @param jsonTokenInfo
     * @param exprie
     * @return
     */
    public static String getToken(JSONTokenInfo jsonTokenInfo,int exprie){
        return  Jwts.builder().claim(jwt_key_user_id,jsonTokenInfo)
                .setExpiration(DateTime.now().plusSeconds(exprie).toDate())
                .signWith(SignatureAlgorithm.HS256,getKey()).compact();

    }

    /**
     * 根据token获取负载的用户信息
     * @param token
     * @return
     */
    public static JSONTokenInfo getInstance(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        JSONTokenInfo jsonTokenInfo=new JSONTokenInfo();
        jsonTokenInfo.setUid(body.get(jwt_key_user_id).toString());
        return jsonTokenInfo;
    }
}
