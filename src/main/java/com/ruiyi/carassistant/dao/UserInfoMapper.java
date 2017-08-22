package com.ruiyi.carassistant.dao;

import java.util.Map;

import com.ruiyi.carassistant.entity.UserInfo;

public interface UserInfoMapper {
	int deleteByPrimaryKey(Integer userid);

	int insert(UserInfo record);

	int insertSelective(UserInfo record);

	UserInfo selectByPrimaryKey(Integer userid);

	int updateByPrimaryKeySelective(UserInfo record);

	int updateByPrimaryKey(UserInfo record);

	Long getUserIDByParams(Map<String, Object> params);

	Long register(Map<String, Object> params);

	Map<String, Object> getUserInfoByParams(Map<String, Object> params);

	Long updateToken(Map<String, Object> params);

	String findTokenByUserID(String userID);

	/**
	 * @Title: updateUserInfoByPhone @Description:
	 *         TODO(这里用一句话描述这个方法的作用) @param @param paramMap @param @return
	 *         设定文件 @return Integer 返回类型 @throws
	 */
	Integer updateUserInfoByPhone(Map<String, Object> paramMap);

}