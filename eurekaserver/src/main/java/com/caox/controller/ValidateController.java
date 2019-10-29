package com.caox.controller;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/9/4 13:48
 */

import com.caox.model.UserInfo;
import com.caox.model.UserInfo.CHECK;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ValidateController {
    @PostMapping(value="test")
    public void testStudent3(@RequestBody @Validated(CHECK.class) UserInfo userInfo,BindingResult result) {

        System.out.println("resolve this check success" + userInfo.getUserId());
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("\n");
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                System.out.println(error.getCode() + "---" + error.getArguments() + "---" + error.getDefaultMessage());
                System.out.println(error.toString());
                sBuilder.append(error.getDefaultMessage());
                sBuilder.append("\n");
            }
        }
        System.out.println(sBuilder.toString());
    }
}
