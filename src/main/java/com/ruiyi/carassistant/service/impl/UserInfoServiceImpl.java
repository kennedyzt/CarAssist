package com.ruiyi.carassistant.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruiyi.carassistant.dao.UserInfoMapper;
import com.ruiyi.carassistant.entity.UserInfo;
import com.ruiyi.carassistant.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public Long getUserIDByParams(Map<String, Object> params) {
		return userInfoMapper.getUserIDByParams(params);
	}

	@Override
	public Long register(Map<String, Object> params) {
		return userInfoMapper.register(params);
	}

	@Override
	public Map<String, Object> getUserInfoByParams(Map<String, Object> params) {
		return userInfoMapper.getUserInfoByParams(params);
	}

	@Override
	public Long updateToken(Map<String, Object> params) {
		return userInfoMapper.updateToken(params);
	}

	@Override
	public String findTokenByUserID(String userID) {
		return userInfoMapper.findTokenByUserID(userID);
	}

	@Override
	public Integer updateByPrimaryKeySelective(UserInfo user) {
		return userInfoMapper.updateByPrimaryKeySelective(user);
	}

	/*
	 * (非 Javadoc) <p>Title: getUserInfoByPhone</p> <p>Description: </p>
	 * 
	 * @param phone
	 * 
	 * @return
	 * 
	 * @see
	 * com.ruiyi.carassistant.service.UserInfoService#getUserInfoByPhone(java.
	 * lang.String)
	 */
	@Override
	public Integer updateUserInfoByPhone(Map<String, Object> paramMap) {
		return userInfoMapper.updateUserInfoByPhone(paramMap);
	}

}
