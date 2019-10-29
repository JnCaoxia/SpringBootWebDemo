package com.caox.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 企业商户开户申请请求对象
 *
 * @author xieyanke email: xieyanke@baofu.com date:2018/7/30
 */
@Getter
@Setter
@ToString
public class MerchantCompanyReqDTO implements Serializable {
    
    private static final long serialVersionUID = -2254141721170593261L;

    // 基本信息
    
    /** 商户名称 */
    @NotNull(message = "商户名称不能为空")
    @NotBlank(message = "商户名称不能为空或空格符")
    @Length(max = 200, message = "商户名称超过最大长度(200)")
    private String memberName;

    /** 账户名称 （邮箱） */
    @NotNull(message = "账户名称不能为空")
    @NotBlank(message = "账户名称不能为空或空格符")
    @Length(max = 200, message = "账户名称超过最大长度(200)")
    private String accountName;

    /** 会员级别（默认101） */
    private Integer levelId;
    
    /** RiskId（默认103） */
    private Integer riskId;

    /** 企业资质备注 */
    @Length(max = 200, message = "企业资质备注超过最大长度(200)")
    private String aptitudeRemark;

    /** 身份证过期日期 */
    @NotNull(message = "身份证过期日期不能为空")
    private Date outdateTime;

    /** 营业执照过期日期 */
    @NotNull(message = "营业执照过期日期不能为空")
    private Date licenseOverdueTime;

    /** 企业经营范围 */
    @NotNull(message = "企业经营范围不能为空")
    @NotBlank(message = "企业经营范围不能为空或空格符")
    @Length(max = 1000, message = "企业经营范围超过最大长度(1000)")
    private String businessCope;

    /** 公司地址-省份 */
    @NotNull(message = "公司地址-省份不能为空")
    @NotBlank(message = "公司地址-省份不能为空或空格符")
    private String addressProvince;

    /** 公司地址-城市 */
    @NotNull(message = "公司地址-城市不能为空")
    @NotBlank(message = "公司地址-城市不能为空或空格符")
    private String addressCity;
    
    /** 公司地址-区 */
    @NotNull(message = "公司地址-区不能为空")
    @NotBlank(message = "公司地址-区不能为空或空格符")
    private String addressCountry;

    /** 公司地址-街道 */
    @NotNull(message = "公司地址-街道不能为空")
    @NotBlank(message = "公司地址-街道不能为空或空格符")
    private String addressStreet;

    /** 公司地址-邮编 */
    @Length(max = 20, message = "公司地址-邮编超过最大长度(20)")
    private String postcode;

    /** 固定电话 */
    @NotNull(message = "固定电话不能为空")
    @NotBlank(message = "固定电话不能为空或空格符")
    @Length(max = 256, message = "固定电话超过最大长度(256)")
    private String telephone;

    /** 手机号 */
    @NotNull(message = "手机号不能为空")
    @NotBlank(message = "手机号不能为空或空格符")
    @Length(max = 256, message = "手机号超过最大长度(256)")
    private String linkMobile;

    /** 公司网址名称 */
    @Length(min = 2, max = 50, message = "公司网址名称小于最小长度(2)或者超过最大长度(50)")
    private String websiteName;

    /** 公司网址 */
    @Length(max = 200, message = "联系地址-手机号超过最大长度(200)")
    private String website;

    /** 一级行业类型（默认0） */
    private Integer industryType;

    /** 二级行业类型（默认0） */
    private Integer subIndustryType;

    /** 二级行业风险等级（默认0） */
    private Integer riskIdChange;

    /** 二级行业风险等级调整备注 */
    private String riskRemark;

    /** 法人名称 */
    @NotNull(message = "法人名称不能为空")
    @NotBlank(message = "法人名称不能为空或空格符")
    @Length(max = 50, message = "法人名称超过最大长度(50)")
    private String legalName;

    /** 法人身份证号 */
    @NotNull(message = "法人身份证号不能为空")
    @NotBlank(message = "法人身份证号不能为空或空格符")
    @Length(max = 256, message = "法人身份证号超过最大长度(256)")
    private String idCard;

    /** ICP备案号 */
    @Length(max = 200, message = "ICP备案号超过最大长度(200)")
    private String icpCode;

    /** 税务登记号 */
    @NotNull(message = "税务登记号不能为空")
    @NotBlank(message = "税务登记号不能为空或空格符")
    @Length(max = 50, message = "税务登记号超过最大长度(50)")
    private String taxCode;

    /** 组织机构代码号 */
    @NotNull(message = "税务登记号不能为空")
    @NotBlank(message = "税务登记号不能为空或空格符")
    @Length(max = 50, message = "税务登记号超过最大长度(50)")
    private String orgCode;

    /** 营业执照号 */
    @NotNull(message = "营业执照号不能为空")
    @NotBlank(message = "营业执照号不能为空或空格符")
    @Length(max = 50, message = "营业执照号超过最大长度(50)")
    private String licenseNum;

    /** 商户所属1-宝付，2-新颜，3-跨境, 4-科技-融证通 5-小微商户 */
    @NotNull(message = "商户所属不能为空")
    @NotBlank(message = "商户所属不能为空或空格符")
    @Length(max = 4, message = "商户所属超过最大长度(4)")
    private Integer merchantOwned;

    /** 注册资本 */
    @NotNull(message = "注册资本不能为空")
    @NotBlank(message = "注册资本不能为空或空格符")
    @Length(max = 200, message = "注册资本超过最大长度(200)")
    private String registeredCapital;

    /** 商户预估笔均交易金额 */
    private BigDecimal estimatedTranAmount;
    
    /** 商户预估单用户月均交易金额 */
    private BigDecimal estimatedTranAmountSingleMonth;
    
    /** 商户预估月均交易金额 */
    private BigDecimal estimatedTranAmountMonth;

    /** 所属客户code */
    @Length(max = 15, message = "所属客户code超过最大长度(15)")
    private String customerCode;
    
    /** 所属客户 */
    @Length(max = 100, message = "所属客户超过最大长度(100)")
    private String customerName;
    
    /** 币种 */
    @NotNull(message = "币种不能为空")
    @NotBlank(message = "币种不能为空或空格符")
    @Length(max = 10, message = "币种超过最大长度(10)")
    private String currency;

    /** 国籍 */
    @Length(max = 10, message = "国籍超过最大长度(10)")
    private String nationality;
    
    /** 法人代表证件种类 */
    @Length(max = 2, message = "法人代表证件种类最大长度(2)")
    private String corporationCertificatesType;
    
    /** 实际经营地址 */
    @Length(max = 200, message = "实际经营地址最大长度(200)")
    private String actualOperatingAddress;
    
    /** 注册地址 */
    @Length(max = 200, message = "注册地址最大长度(200)")
    private String registeredAddress;
    
    /** 开票类型  1.独立 2.合并  3.个性化 */
    @Length(max = 16, message = "开票类型最大长度(16)")
    private String billType;
    

    // 结算信息
    /** 开户行 */
    @NotNull(message = "开户行不能为空")
    @NotBlank(message = "开户行不能为空或空格符")
    private String bankName;
    
    /** 开户行名称 */
    @NotNull(message = "开户行名称不能为空")
    @NotBlank(message = "开户行名称不能为空或空格符")
    private String bankAddress;

    /** 银行开户名 */
    @NotNull(message = "银行开户名不能为空")
    @NotBlank(message = "银行开户名不能为空或空格符")
    private String bankAccountName;

    /** 开户行省份 */
    @NotNull(message = "开户行省份不能为空")
    @NotBlank(message = "开户行省份不能为空或空格符")
    private String bankProvince;

    /** 开户行城市 */
    @NotNull(message = "开户行城市不能为空")
    @NotBlank(message = "开户行城市不能为空或空格符")
    private String bankCity;

    /** 开户支行名称 */
    private String bankSubBranch;

    /** 银行账号 */
    @NotNull(message = "银行账号不能为空")
    @NotBlank(message = "银行账号不能为空或空格符")
    private String bankAccount;

    /** 支付方式 */
    @NotNull(message = "支付方式不能为空")
    @NotBlank(message = "支付方式不能为空或空格符")
    @Length(max = 11, message = "支付方式超过最大长度(11)")
    private Integer payId;

    /** 结算周期 1:日结,2:周结,3:月结 */
    private Integer settlementPeriod;

    /** 结算起点金额 */
    private BigDecimal startingMoney;

    /** 结算类型 0-非实时,1-实时 ,2-工作日,3-自然日 */
    private Integer settleType;

    /** 结算天数 */
    private Integer tradeN;

    /** 结算状态 */
    private Boolean settleFlag;

    //联系人信息
    /** 业务邮箱 */
    @NotNull(message = "业务邮箱不能为空")
    @NotBlank(message = "业务邮箱不能为空或空格符")
    private String businessEmail;

    /** 业务联系人 */
    @NotNull(message = "业务联系人不能为空")
    @NotBlank(message = "业务联系人不能为空或空格符")
    private String businessName;

    /** 业务电话 */
    @NotNull(message = "业务电话不能为空")
    @NotBlank(message = "业务电话不能为空或空格符")
    private String businessPhone;

    /** 业务QQ */
    private String businessQq;

    /** 财务邮箱 */
    private String financeEmail;

    /** 财务联系人 */
    private String financeName;

    /** 财务电话 */
    private String financePhone;

    /** 财务QQ */
    private String financeQq;

    /** 技术邮箱 */
    private String techEmail;

    /** 技术联系人 */
    private String techName;

    /** 技术电话 */
    private String techPhone;

    /** 技术QQ */
    private String techQq;
    
    /** 联系人-证件种类 */
    private String certificatesType;
    
    /** 联系人-证件号码 */
    private String certificateNumber;

    /** 联系人-证件有效期 */
    private String certificatesExpiryDate;
    
    // CRM信息

    /** 销售员 */
    @NotNull(message = "销售员不能为空")
    @NotBlank(message = "销售员不能为空或空格符")
    private Long sellerId;

    /** 销售员名称 */
    @NotNull(message = "销售员名称不能为空")
    @NotBlank(message = "销售员名称不能为空或空格符")
    private String sellerName;

    /** 代理商ID */
    private Long agentMemberId;

    /** 代理商名称 */
    private String agentMemberName;

    /** 一级行业 */
    private Integer industryId;
    
    /** 一级行业名称 */
    private String industryName;

    /** 二级行业 */
    private Integer subIndustryId;

    /** 二级行业名称 */
    private String subIndustryName;

    /** 二级行业属性 */
    private Integer subIndustryNature;

    /** 来源ID */
    private Integer sourceId;

    /** 场景号(1:充值,2:还款,3:混合) - 默认0 */
    private Integer sceneId;

    //商户合同信息
    /** 生效时间 */
    private Date effectTime;
    
    /** 失效时间 */
    private Date invalidTime;

    // 首次下发验证信息
    /** 首次下发起点金额 */
    private BigDecimal startMoney;
    
    /** 0:不验证，1:验证 */
    private Integer firstFoCheckFlag;
    
    /** 0:未进行认证;1:已认证 */
    private Integer firstFoIsIdentified;

    //上传行业
    /** 上传行业ID */
    private Integer uploadIndustryId;
    
    /** 上传行业名称 */
    private String uploadIndustryName;

    /** 合同编号 */
    private String contractNo;

    // 股东、受益人信息
    /** 控股股东、受益人名称 */
    private String shareholdersBeneficiaryName;

    /** 控股股东、受益人 证件种类 */
    private String shareholdersBeneficiaryCertificatesType;

    /** 控股股东 证件号码*/
    private String shareholdersCertificatesNumber;

    /** 控股股东 证件有效期*/
    private String shareholderCertificatesExpiryDate;

    /**控股股东 地址 */
    private String shareholdersAddress;

    /**控股股东 类型 */
    private String shareholdersType;

    // 控股股东、受益人批量信息
    /**  产品计费配置 */
    @NotNull(message = "控股股东（受益人）信息不能为空")
    @NotBlank(message = "控股股东（受益人）信息不能为空或空格符")
    @NotEmpty(message = "控股股东（受益人）信息集合内容不能为空")
    List<ShareholdersReqDTO> shareholdersReqDTOList;

    /** 小微代理商户号 */
    private Long csAgentMemberId;

    /** 注册来源系统标签 */
    private String registerSource;

    /** 经营类目 1:物业 */
    private Integer orgType;

    /** 授权目录 */
    private String authorDir;

    /** APP ID */
    private String appId;

    /** 开户流水号  */
    @NotNull(message = "开户流水号不能为空")
    @NotBlank(message = "开户流水号不能为空或空格符")
    private String openSerialNo;

    /** 银联自定义商户名 */
    private String unionMerchantName;

    /** 网联自定义商户名 */
    private String networkMerchantName;

    /** 建立业务关系渠道 （建立业务关系渠道 1: 当面见证客户; 2:代理商推荐 3:其他方式建立业务关系）*/
    private Integer businessRelationChannel;

    /** 商户证件类型 （商户证件类型 1:营业执照 2:其他境内证件 3:其他境外证件）*/
    private Integer merchantCardType;

    /** 是否有代理人 */
    private Integer isAgent;

    /** 代理人姓名 */
    private String agentName;

    /** 代理人证件类型  */
    private Integer agentCertificatesType;

    /** 代理人证件号码 */
    private String agentCertificatesNumber;

    /** 代理人证件有效期截止日 */
    private String agentCertificatesExpiryDate;

    /** 公司类型 */
    private Integer companyType;

    /** 客户行业 */
    private String customerIndustry;

    /** 成立日期 */
    private Date establishDate;

    /** 核销方式  1 : 精确核销; 2 : 合并核销; 3:分组合并核销 */
    private String verifyType;

    /** 支付方式名称 */
    private String payName;

    /** 商户公共关系标记 北京BJ、深圳SZ、杭州HZ */
    private String commonCustomerFlag;
}
