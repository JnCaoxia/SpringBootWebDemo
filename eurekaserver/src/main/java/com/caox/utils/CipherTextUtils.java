package com.caox.utils;

import com.baofoo.cryption.CipherTextAssortmentHandle;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

/**
 * 封装CipherTextAssortmentHandle类的几个解密方法
 * 主要解密证件号，手机号，银行卡号，邮箱
 * Created by nazi on 2018/3/8.
 */
@Slf4j
public class CipherTextUtils {

    /**
     * 证件号解密
     * @param idCard
     * @return
     */
    public static String aesDecryptIdCard(String idCard){
        log.info("证件号解密aesDecryptIdCard开始 idCard:{}",idCard);
        if(StringUtils.isNumeric(idCard)){
            return idCard;
        }
        if(idCard != null && idCard.length() !=0 && (idCard.endsWith("X") || idCard.endsWith("x"))
                && StringUtils.isNumeric(idCard.substring(0,idCard.length()-1))){
           return idCard;
        }
        String  aesDecryptIdCard = CipherTextAssortmentHandle.aesDecryptIdCard(idCard);
        log.info("证件号解密aesDecryptIdCard结果 aesDecryptIdCard:{}",aesDecryptIdCard);
        if(null  == aesDecryptIdCard){
            aesDecryptIdCard = idCard;
        }
        log.info("证件号解密aesDecryptIdCard返回 aesDecryptIdCard:{}",aesDecryptIdCard);
        return  aesDecryptIdCard;

    }
    /**
     * 手机号、联系电话解密
     * @param phoneNum
     * @return
     */
    public static String aesDecryptPhoneNum(String phoneNum){
        if(StringUtils.isNotBlank(phoneNum) && phoneNum.contains("-")){
            String newPhoneNum = phoneNum.replaceAll("-","");
            if(StringUtils.isNumeric(newPhoneNum)){
                return phoneNum;
            }
        }
        if(StringUtils.isNumeric(phoneNum)){
            return phoneNum;
        }
        return CipherTextAssortmentHandle.aesDecryptPhoneNum(phoneNum);
    }
    /**
     * 银行卡号解密
     * @param bankCard
     * @return
     */
    public static String aesDecryptBankCard(String bankCard){
        if(StringUtils.isNumeric(bankCard)){
            return bankCard;
        }
        return CipherTextAssortmentHandle.aesDecryptBankCard(bankCard);
    }
    /**
     * 邮箱帐号解密
     * @param mail
     * @return
     */
    public static String aesDecryptMail(String mail){
        if(StringUtils.isNotBlank(mail) && mail.contains("@")){
            return mail;
        }
        return CipherTextAssortmentHandle.aesDecryptMail(mail);
    }


    /**
     * 证件号加密
     * @param idCard
     * @return
     */
    public static String aesEncryptIdCard(String idCard){
        return CipherTextAssortmentHandle.aesEncryptIdCard(idCard);
    }
    /**
     * 手机号加密
     * @param phoneNum
     * @return
     */
    public static String aesEncryptPhoneNum(String phoneNum){
        return CipherTextAssortmentHandle.aesEncryptPhoneNum(phoneNum);
    }
    /**
     * 银行卡号加密
     * @param bankCard
     * @return
     */
    public static String aesEncryptBankCard(String bankCard){
        return CipherTextAssortmentHandle.aesEncryptBankCard(bankCard);
    }
    /**
     * 邮箱帐号加密
     * @param mail
     * @return
     */
    public static String aesEncryptMail(String mail){
        return CipherTextAssortmentHandle.aesEncryptMail(mail);
    }

}
