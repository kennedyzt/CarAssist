package com.ruiyi.carassistant.dao;

import org.apache.ibatis.annotations.Select;

import com.ruiyi.carassistant.entity.ExceptionInfo;

public interface ExceptionInfoMapper {
	int deleteByPrimaryKey(Integer exceptionInfoID);

	int insert(ExceptionInfo record);

	int insertSelective(ExceptionInfo record);

	ExceptionInfo selectByPrimaryKey(Integer exceptionInfoID);

	int updateByPrimaryKeySelective(ExceptionInfo record);

	int updateByPrimaryKey(ExceptionInfo record);

	@Select("select ExceptionInfoID from exceptioninfo where userID = #{userID} and ExceptionType = #{exceptionType}")
	Integer selectCountByUserIDAndExceptionType(ExceptionInfo exceptionInfo);
}