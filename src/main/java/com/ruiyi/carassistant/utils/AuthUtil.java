package com.ruiyi.carassistant.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruiyi.carassistant.service.UserInfoService;

@Component
public class AuthUtil {
    
    private static UserInfoService userInfoService;

    @Autowired
    public void setUserInfoService(UserInfoService userInfoService) {
        AuthUtil.userInfoService = userInfoService;
    }
    
    public static boolean isValidated(String userID, String tokenStr) {
        if (userID == null || userID.trim().equals("") || tokenStr == null && tokenStr.trim().equals("")) return false;
        String token = userInfoService.findTokenByUserID(userID);
        if (token != null && token.equals(tokenStr)) {
            return true;
        }
        return false;
    }

    public static boolean isValidated(Integer userID, String tokenStr) {
        return isValidated("" + userID, tokenStr);
    }
}
