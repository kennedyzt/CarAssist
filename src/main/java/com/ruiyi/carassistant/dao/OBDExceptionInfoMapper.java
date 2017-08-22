package com.ruiyi.carassistant.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;

public interface OBDExceptionInfoMapper {

	@Insert("insert into obd_exception_info(type_id, user_id, content) values(#{TypeID}, #{UserID}, #{Content})")
	void addExceptionInfo(Map<String, Object> param);

}
