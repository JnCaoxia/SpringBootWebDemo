//package com.caox.service.impl;
//
//import com.alibaba.dubbo.config.annotation.Reference;
//import com.baofoo.ma.query.facade.MemberQueryService;
//import com.baofoo.ma.query.facade.model.Result;
//import com.baofoo.ma.query.facade.model.respone.MemberOpenResDTO;
//import com.baofu.rm.oms.facade.aml.customer.dto.request.NotificationGradDto;
//import com.baofu.rm.oms.facade.aml.customer.dubbo.CustomerGradFacade;
//import com.baofu.rm.oms.facade.common.RespMessage;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * @author : nazi
// * @version : 1.0
// * @date : 2019/4/12 15:53
// * 远端调用dubbo服务
// */
//@Component
//public class RemoteServiceImpl {
//
//    @Reference
//    MemberQueryService  memberQueryService;
//
//    @Reference
//    CustomerGradFacade customerGradFacade;
//
//    /**
//     * 根据账户名返回开户信息
//     * @param accountName      账户名
//     * @return                 开户信息
//     */
//    public Result<List<MemberOpenResDTO>> queryMerchantOpenInfoByAccountName(String accountName){
//        return memberQueryService.queryMerchantOpenInfoByAccountName(accountName);
//    }
//
//    public RespMessage  notice(NotificationGradDto notificationGradDto){
//        RespMessage respMessage = customerGradFacade.notice(notificationGradDto);
//        return respMessage;
//    }
//}
