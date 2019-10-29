package com.caox.dal.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/10/28 14:59
 */
public interface RibbonSaveMapper {

    int save(@Param("result") String result, @Param("msg") String msg);
}
