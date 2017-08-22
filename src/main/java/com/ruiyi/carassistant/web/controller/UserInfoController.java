package com.ruiyi.carassistant.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ruiyi.carassistant.entity.ClientSocketInfo;
import com.ruiyi.carassistant.entity.SocketRequest;
import com.ruiyi.carassistant.entity.UserInfo;
import com.ruiyi.carassistant.entity.common.ResultMsg;
import com.ruiyi.carassistant.service.CarInfoService;
import com.ruiyi.carassistant.service.UserInfoService;
import com.ruiyi.carassistant.utils.*;
import com.ruiyi.carassistant.utils.netease.NIMPost;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

@Api(tags = "用户信息接口")
@Controller
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private CarInfoService carInfoService;
    @Value("#{prop.headImg}")
    private String headImg;

    private static final String sendMessageUrl = "http://106.ihuyi.com/webservice/sms.php?method=Submit&account=C52962550&password=39a5212aa923e80c9ef902c1b3a69157&mobile=PHONE&content=您的验证码是：AUTHCODE。请不要把验证码泄露给其他人。";

    @ApiOperation(value = "用户注册", notes = "")
    @ApiImplicitParams({@ApiImplicitParam(name = "phone", value = "用户手机号码", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = false, dataType = "String", paramType = "query")})
    @RequestMapping(value = "/RegisterServlet", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultMsg<Map<String, Object>> register(@RequestParam(value = "phone", required = false) String phone, @RequestParam(value = "password", required = false) String password) {
        // 是判断否已经注册
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("phone", phone);
        Long userId = userInfoService.getUserIDByParams(paramMap);
        if (userId == null) {
            // 注册
            paramMap.put("userType", 4);
            paramMap.put("password", password);
            paramMap.put("token", "1");
            paramMap.put("userName", phone); // 没有昵称默认手机号
            paramMap.put("userHeadUrl", "/user/imgs/head_default.jpg");
            userInfoService.register(paramMap);
            return ResultMsg.success(paramMap);
        } else {
            return ResultMsg.error("您的帐号已注册，请直接登录！");
        }
    }

    @ApiOperation(value = "用户登录", notes = "用户通过手机号码和密码登录")
    @ApiImplicitParams({@ApiImplicitParam(name = "phone", value = "用户手机号码", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "loginName", value = "第三方帐号", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userType", value = "用户类型", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userName", value = "用户昵称", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userHeadUrl", value = "用户头像", required = false, dataType = "String", paramType = "query")})
    @RequestMapping(value = "/Login", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultMsg<Map<String, Object>> login(@RequestParam(value = "phone", required = false) String phone, @RequestParam(value = "password", required = false) String password,
                                                @RequestParam(value = "loginName", required = false) String loginName,
                                                @RequestParam(value = "userType", required = false, defaultValue = "4") Integer userType,
                                                @RequestParam(value = "userName", required = false) String userName, @RequestParam(value = "userHeadUrl", required = false) String userHeadUrl) {
        if (userType == 4) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("phone", phone);
            paramMap.put("password", password);
            Map<String, Object> userInfo = userInfoService.getUserInfoByParams(paramMap);
            if (userInfo != null) {
                // 找到用户
                buildReturnMsg(userInfo);
                return ResultMsg.success(userInfo);
            } else {
                return ResultMsg.error("用户名或密码错误！");
            }
        } else {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("loginName", loginName);
            Map<String, Object> userInfo = userInfoService.getUserInfoByParams(paramMap);
            if (userInfo != null) {
                buildReturnMsg(userInfo);
                if (userInfo.get("userName") == null) {
                    userInfo.put("userName", userName);
                }
                if (userInfo.get("userHeadUrl") == null) {
                    userInfo.put("userHeadUrl", userHeadUrl);
                }
                return ResultMsg.success(userInfo);
            } else {
                // 注册
                paramMap.put("userType", userType);
                paramMap.put("token", "1");
                paramMap.put("loginName", loginName);
                userInfoService.register(paramMap);
                // 返回第三方头像和昵称
                paramMap.put("userName", userName);
                paramMap.put("userHeadUrl", userHeadUrl);
                paramMap.put("carInfoList", new LinkedList<Map<String, Object>>());
                return ResultMsg.success(paramMap);
            }
        }
    }

    /**
     * @Title: buildReturnMsg @Description: TODO(这里用一句话描述这个方法的作用) @param @param
     * userInfo 设定文件 @return void 返回类型 @throws
     */
    private void buildReturnMsg(Map<String, Object> userInfo) {
        Integer token = (Integer) userInfo.get("token");
        userInfo.put("token", (token + 1) % 256);
        userInfoService.updateToken(userInfo);
        List<Map<String, Object>> carInfoList = carInfoService.getCarInfoListByUserID(userInfo);
        userInfo.put("carInfoList", carInfoList);
    }

    @ApiOperation(value = "发送短信验证码", notes = "")
    @ApiImplicitParams({@ApiImplicitParam(name = "phone", value = "用户手机号码", required = true, dataType = "String", paramType = "query"),})
    @RequestMapping(value = "/sendAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> sendAuthSMS(@RequestParam(value = "phone", required = true) String phone,
                                           @RequestParam(value = "registered", required = false, defaultValue = "true") Boolean registered) {
        Map<String, Object> result = new HashMap<>();
        if (!PhoneNumUtil.isMobileNO(phone))
            return R.error("非有效的手机号码");
        if (registered) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("phone", phone);
            Long userId = userInfoService.getUserIDByParams(paramMap);
            if (null == userId) {
                result.put("authCode", "");
                result.put("success", false);
                result.put("error", "用户未注册");
                return result;
            }
        }
        Integer authCode = (int) (Math.random() * 9000 + 1000);
        // 互亿无线
        // RequestUtil.sendGet(sendMessageUrl.replace("PHONE", phone).replace("AUTHCODE", authCode.toString()));
        // 网易邮箱
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //params.add(new BasicNameValuePair("templateid", "3064185"));
        params.add(new BasicNameValuePair("mobile", phone));
        // UTF-8编码,解决中文问题
        UrlEncodedFormEntity entity;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            String res =
                    NIMPost.postNIMServer("https://api.netease.im/sms/sendcode.action",
                            entity);
            JSONObject jsonBean = JSONObject.parseObject(res);
            result.put("authCode", jsonBean.getString("obj"));
<<<<<<< HEAD
            result.put("success", true);
            return result;
=======
>>>>>>> 47194f29866932d95f2512306c16bf2eb1dc2d17
        } catch (Exception e) {
            e.printStackTrace();
            result.put("authCode", "");
            result.put("success", false);
            result.put("error", "获取验证码失败！");
            return result;
        }
<<<<<<< HEAD
=======
        result.put("authCode", authCode.toString());
        result.put("success", true);
        return result;
>>>>>>> 47194f29866932d95f2512306c16bf2eb1dc2d17
    }

    @ApiOperation("用户信息修改")
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateUserInfo(@RequestBody UserInfo user) {
        if (!AuthUtil.isValidated(user.getUserID(), user.getToken()))
            return R.error("权限验证失败");
        Integer num = userInfoService.updateByPrimaryKeySelective(user);
        if (num != null && num > 0)
            return R.ok();
        return R.error();
    }

    @ApiOperation("绑定手机号")
    @RequestMapping(value = "/bindPhone", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg<Map<String, Object>> bindPhone(@RequestParam(value = "phone", required = true) String phone, @RequestParam(value = "password", required = true) String password,
                                                    @RequestParam(value = "userId", required = true) Integer userId) {
        // 判断手机号是否已注册或被其他帐号绑定
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("phone", phone);
        if (null != userInfoService.getUserIDByParams(paramMap)) {
            return ResultMsg.error("手机号已注册，或已被其他帐号绑定！");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserID(userId);
        userInfo.setPhone(phone);
        userInfo.setPassword(password);
        userInfoService.updateByPrimaryKeySelective(userInfo);
        return ResultMsg.success();
    }

    @RequestMapping(value = "/updatePwd", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg<Map<String, Object>> updatePwd(@RequestParam(value = "phone", required = true) String phone, @RequestParam(value = "password", required = true) String password) {
        Map<String, Object> checkPhoneMap = new HashMap<>();
        checkPhoneMap.put("phone", phone);
        Long userId = userInfoService.getUserIDByParams(checkPhoneMap);
        if (null == userId) {
            return ResultMsg.error("用户未注册");
        }
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("phone", phone);
        paramMap.put("password", password);
        userInfoService.updateUserInfoByPhone(paramMap);
        return ResultMsg.success();
    }

    @ApiOperation("修改用户头像或昵称")

    @ApiImplicitParams({

            @ApiImplicitParam(name = "userID", value = "用户ID", required = true, dataType = "String", paramType = "query"),

            @ApiImplicitParam(name = "userName", value = "用户token", required = false, dataType = "String", paramType = "query"),

            @ApiImplicitParam(name = "headImg", value = "汽车系列ID", required = false, dataType = "String", paramType = "query")})

    @RequestMapping(value = "/upload/headimg", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addUser(UserInfo userInfo, HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> uploadResultMap = new HashMap<>();
        MultipartFile file = null;
        try {
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
            file = mRequest.getFile("headImg");
        } catch (Exception e) {
        }
        if (null != file) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("userID", userInfo.getUserID());
            Map<String, Object> userInfoMap = userInfoService.getUserInfoByParams(paramMap);
            uploadResultMap = FileUploadUtil.upload(file, headImg + "/user/imgs");
            LoggerUtil.debug("文件上传成功");
            userInfo.setUserHeadUrl("/user/imgs/" + uploadResultMap.get("aliasFileName").toString());
            result.put("url", "/user/imgs/" + uploadResultMap.get("aliasFileName").toString());
            if (!userInfoMap.get("userHeadUrl").toString().equals("/user/imgs/head_default.jpg")) {
                FileUploadUtil.deleteFile(headImg + userInfoMap.get("userHeadUrl").toString());
                LoggerUtil.debug("原文件已删除");
            }
        } else {
            result.put("url", "");
        }
        userInfoService.updateByPrimaryKeySelective(userInfo);
        result.put("userName", userInfo.getUserName() == null ? "" : userInfo.getUserName());
        result.put("error", "");
        result.put("success", true);
        return result;
    }

    @ApiOperation(value = "修改wifi")
    @ApiImplicitParams({

            @ApiImplicitParam(name = "simcode", value = "simcode", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "wifiname", value = "wifiname", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "wifipwd", value = "wifipwd", required = true, dataType = "String", paramType = "query")})
    @RequestMapping(value = "/updateWifi", method = RequestMethod.GET)
    @ResponseBody
    public synchronized Map<String, Object> updateWifi(@RequestParam(value = "wifiname", required = true) String wifiname, @RequestParam(value = "simcode", required = true) String simcode,
                                                       @RequestParam(value = "wifipwd", required = true) final String wifipwd, HttpServletResponse response) {

        Map<String, Object> result = new HashMap<String, Object>();
        ClientSocketInfo clientSocketInfo = ParseJson.clientSocketMap.get(simcode);
        if (null != clientSocketInfo) {
            Socket clientSocket = clientSocketInfo.getSocket();
            // 请求obd数据
            try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println("{\"jsonrpc\":\"2.0\", \"method\": \"update_wifi\", \"params\":[\"" + wifiname + "\",\"" + wifipwd + "\"], \"id\": 1}");
                out.flush();
            } catch (Exception e) {
                ParseJson.clientSocketMap.remove(simcode);
                result.put("error", "长链接已断开");
                result.put("success", false);
                return result;
            }
            Integer times = 0;
            while (true) {
                while (null != ParseJson.updateWifiResultJsonObject) {
                    result.put("wifiname", ParseJson.updateWifiResultJsonObject.getString("wifiname"));
                    result.put("wifipwd", ParseJson.updateWifiResultJsonObject.getString("wifipwd"));
                    ParseJson.updateWifiResultJsonObject = null;
                    result.put("error", "");
                    result.put("success", true);
                    return result;
                }
                try {
                    Thread.sleep(1000);
                    if (times > 10) {
                        LoggerUtil.debug("获取次数：" + times);
                        result.put("error", "请求超时");
                        result.put("success", false);
                        return result;
                    }
                    times++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            result.put("error", "OBD不在线");
            result.put("success", false);
            return result;
        }
    }

    @ApiOperation(value = "获取wifi")
    @ApiImplicitParams({

            @ApiImplicitParam(name = "simcode", value = "simcode", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "wifiname", required = true, dataType = "String", paramType = "query")})
    @RequestMapping(value = "/getWifiInfo", method = RequestMethod.GET)
    @ResponseBody
    public synchronized Map<String, Object> getWifiIno(@RequestParam(value = "simcode", required = true) String simcode, @RequestParam(value = "userId", required = true) String userId) {
        Map<String, Object> result = new HashMap<String, Object>();
        ClientSocketInfo clientSocketInfo = ParseJson.clientSocketMap.get(simcode);
        if (null != clientSocketInfo) {
            Socket clientSocket = clientSocketInfo.getSocket();
            // 请求obd数据
            try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                Map<String, Object> params = new HashMap<>();
                params.put("userId", userId);
                SocketRequest socketRequest = new SocketRequest("info_wifi", params);
                out.println(socketRequest.getJsonObject());
                out.flush();
            } catch (Exception e) {
                result.put("error", "OBD已掉线");
                ParseJson.clientSocketMap.remove(simcode);
                result.put("success", false);
                return result;
            }
            Integer times = 0;
            while (true) {
                while (null != ParseJson.wifiParamsJsonObject) {
                    result.put("wifiname", ParseJson.wifiParamsJsonObject.getString("wifiname"));
                    result.put("wifipwd", ParseJson.wifiParamsJsonObject.getString("wifipwd"));
                    ParseJson.wifiParamsJsonObject = null;
                    result.put("error", "");
                    result.put("success", true);
                    return result;
                }
                try {
                    Thread.sleep(1000);
                    if (times > 10) {
                        LoggerUtil.debug("获取次数：" + times);
                        result.put("error", "请求超时");
                        result.put("success", false);
                        return result;
                    }
                    times++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            result.put("error", "OBD不在线");
            result.put("success", false);
            return result;
        }
    }

}
