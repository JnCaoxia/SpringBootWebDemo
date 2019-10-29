package com.caox.jwt;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/8/19 17:47
 */
@Setter
@Getter
@ToString
public class JSONTokenInfo {
    /**
     * 用户id
     */
    private  String uid;
    /**
     * 过期时间
     */
    private int exprie;

    public JSONTokenInfo(String uid) {
        this.uid = uid;
    }

    public JSONTokenInfo() {
    }
}
