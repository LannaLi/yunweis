package com.dfdk.yunwei.dao.log;

import org.springframework.stereotype.Repository;

import com.dfdk.yunwei.model.log.SysLog;
@Repository
public interface SysLogMapper {
    int deleteByPrimaryKey(String logid);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(String logid);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
}