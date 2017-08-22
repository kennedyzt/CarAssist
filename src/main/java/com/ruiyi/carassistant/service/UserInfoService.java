package com.ruiyi.carassistant.service;

import com.ruiyi.carassistant.entity.UserInfo;

import java.util.Map;

public interface UserInfoService {

	Long getUserIDByParams(Map<String, Object> params);

	Long register(Map<String, Object> params);

	Map<String, Object> getUserInfoByParams(Map<String, Object> params);

	Long updateToken(Map<String, Object> userInfo);

	String findTokenByUserID(String userID);

	Integer updateByPrimaryKeySelective(UserInfo user);

	Integer updateUserInfoByPhone(Map<String, Object> paramMap);
}
