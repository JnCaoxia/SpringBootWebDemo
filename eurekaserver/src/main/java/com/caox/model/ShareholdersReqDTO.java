package com.caox.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.MemberOf;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

/**
 * 股东、受益人信息请求参数
 * @author : nazi
 * @version : 1.0
 * @date : 2018/12/7 13:34
 */
@Setter
@Getter
@ToString
public class ShareholdersReqDTO implements Serializable {
    private static final long serialVersionUID = 1637448432154722448L;

    // 股东、受益人信息
    /** 控股股东、受益人名称 */
    @NotNull(message = "控股股东（受益人）名称不能为空")
    @NotBlank(message = "控股股东（受益人）不能为空或空格符")
    private String shareholdersBeneficiaryName;

    /** 控股股东、受益人 证件种类 */
    @NotNull(message = "控股股东（受益人）证件种类不能为空")
    @NotBlank(message = "控股股东（受益人）证件种类不能为空或空格符")
    private String shareholdersBeneficiaryCertificatesType;

    /** 控股股东 证件号码*/
    @NotNull(message = "控股股东（受益人）证件号码不能为空")
    @NotBlank(message = "控股股东（受益人）证件号码不能为空或空格符")
    private String shareholdersCertificatesNumber;

    /** 控股股东 证件有效期*/
    @NotNull(message = "控股股东（受益人）证件有效期不能为空")
    @NotBlank(message = "控股股东（受益人）证件有效期不能为空或空格符")
    private String shareholderCertificatesExpiryDate;

    /**控股股东 地址 */
    @NotNull(message = "控股股东（受益人）地址不能为空")
    @NotBlank(message = "控股股东（受益人）地址不能为空或空格符")
    private String shareholdersAddress;

    /**控股股东 类型 */
    @NotNull(message = "控股股东（受益人）类型不能为空")
    @NotBlank(message = "控股股东（受益人）类型不能为空或空格符")
    @MemberOf(value = {"01", "02"}, message = "控股股东（受益人）类型非法")
    private String shareholdersType;
}
