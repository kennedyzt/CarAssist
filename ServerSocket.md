<<<<<<< HEAD
                                                  汽车盒子
用户注册
请求方式：get
请求地址：/RegisterServlet
请求参数：
phone 电话号码 必填
password 密码  手机号注册必填
返回值：
{
returnInfo: 
{
password: "123456"
phone: "1646876616379"
}
success: true
error: ""
}


用户登录
请求方式：get
请求地址：/Login
请求参数：
phone 电话号码 手机登录必填，第三方登录不需要此字段
password 密码  手机号登录必填，第三方登录不需要此字段
userType   必填 1.QQ2.微信3.微博4.手机号
loginName 第三方帐号登录必填，手机登录不需要此字段
userName 第三方帐号登录必填，手机登录不需要此字段
userHeadUrl 第三方帐号登录必填，手机登录不需要此字段

返回值：
{
error: ""
errorCode: ""
returnInfo: {
carInfoList: [0]
password: null
phone:"24122554125632" //根据手机号是否存在判断是否绑定手机号
loginName: "241225541256323352221su"
userType: 1
userName: "121"
userID: 1351
userHeadUrl: "1212"
token: 6
}
success: true
}

绑定手机
请求方式：get
请求地址：/bindPhone
请求参数：
userId 用户ID  必填
phone 电话号码 必填
password 密码  必填
返回值：


{ 
      "error":"",
"errorCode":"",
"returnInfo":null,
"success":true
}
修改用户基本信息
请求方式：post
请求地址：/upload/headimg

请求参数：
userID 用户ID  必填
userName 昵称 不必填
headImg  头像 不必填
phone  电话号码 不必填
password 密码 不必填


返回值：


{ 
      "error":"",
"errorCode":"",
"returnInfo":null,
"success":true
}

发送验证码
请求方式：get
请求地址：/sendAuthCode
请求参数：
phone手机号码 必填
registered boolean default true 是否需要验证手机号是否注册 必填
返回值：
{
"authCode":"5131",
"success":true
}



爱车定位
请求方式：get
请求地址：/lbs/getlonlatbysimcode
请求参数
userID 用户id 必填
simcode 汽车盒子唯一标识 必填 
token  必填
返回值：
{
"error":"",
"errorCode":"",
"returnInfo":{
"longitude":"5131", //经度
"latitude":"5131" // 纬度
},
"success":true
}
切换盒子
请求方式：get
请求地址：/changeBox
请求参数
userId 用户ID 必填
simCode 盒子唯一标识 必填
返回值：
{     
      "error":"",
"errorCode":"",
"returnInfo":null,
"success":true
}

同步行程单
请求方式：get
请求地址：/SyncTravelInfo
请求参数
userID 用户id 必填
SimCode汽车盒子唯一标识 必填 
token  必填
TravelInfoID 必填 已同步行程单的数量 0代表返回所有
返回值：
 
{
AvgSpeed: 0
TrailStr: "[{ "longtitude": "104.0612564", "latitude": "30.5971909", "bearing": "266.6700134", "speed": "0.0000000", "loctime": "1491805309000" },{ "longtitude": "104.0612411", "latitude": "30.5972061", "bearing": "264.0599976", "speed": "0.0000000", "loctime": "1491805319000" },{ "longtitude": "104.0611343", "latitude": "30.5971546", "bearing": "130.2100067", "speed": "0.0000000", "loctime": "1491805330000" },{ "longtitude": "104.0608368", "latitude": "30.5970306", "bearing": "185.9900055", "speed": "0.0000000", "loctime": "1491805340000" },{ "longtitude": "104.0607758", "latitude": "30.5970078", "bearing": "176.5000000", "speed": "0.0000000", "loctime": "1491805350000" },{ "longtitude": "104.0607224", "latitude": "30.5969696", "bearing": "142.3000031", "speed": "0.0000000", "loctime": "1491805360000" },{ "longtitude": "104.0607224", "latitude": "30.5969696", "bearing": "142.3000031", "speed": "0.0000000", "loctime": "1491805371000" },{ "longtitude": "104.0607224", "latitude": "30.5969696", "bearing": "142.3000031", "speed": "0.0000000", "loctime": "1491805381000" },{ "longtitude": "104.0607224", "latitude": "30.5969696", "bearing": "142.3000031", "speed": "0.0000000", "loctime": "1491805391000" },{ "longtitude": "104.0606232", "latitude": "30.5970020", "bearing": "268.3900146", "speed": "0.0000000", "loctime": "1491805401000" },{ "longtitude": "104.0607452", "latitude": "30.5970402", "bearing": "58.2099991", "speed": "0.0000000", "loctime": "1491805412000" },{ "longtitude": "104.0607834", "latitude": "30.5970669", "bearing": "82.9800034", "speed": "0.0000000", "loctime": "1491805422000" },{ "longtitude": "104.0607834", "latitude": "30.5970745", "bearing": "74.5999985", "speed": "0.0000000", "loctime": "1491805427000" },{ "longtitude": "104.0607834", "latitude": "30.5970745", "bearing": "74.5999985", "speed": "0.0000000", "loctime": "1491805432000" }]"
DriveTime: 236
EndTime: "2017-04-10 14:23:57"
CarState: "2"
EndLocation: ""
EndLocationDesc: ""
StartTime: "2017-04-10 14:20:01"
TopSpeed: 0
Grade: 100
TravelInfoID: 591
userID: 1291
Cost: 0
Consume: 0
StartLocationDesc: ""
StartLocation: ""
SimCode: "6E6DF4E4-29D1-409D-947A-D29F9E7F2D11"
ConsumePerMile: 0
Distance: 0
}
安卓上传升级包地址
请求地址：/ota/android/toupload



安卓检查更新
请求方式：get
请求地址：/ota/android/checkupdate
请求参数：
version 当前版本号 必填
type 包类型（1：收音机2：语言助手3：行车记录仪4：音乐5:卡耳云） 必填

返回值：
{
"success": true,
"error": "",
"errorCode": "",
"returnInfo": 
{
"patchUrl": "",
"needUpdate": true,
"completeUrl": "/fm/v3/CarTingFM.apk"
}
}




统一请求格式
{
    "method": "info_local",
    "params": {\"userID\":234,\"travelInfoIDs\": \		   "58ff0ad1531a7c	f682db3cab\"}
}



统一返回格式
{
    "method": "info_local",
    "result": {\"userID\":234,\"travelInfoIDs\": \				"58ff0ad1531a7cf682db3cab\"},
    "success": true
} 

安卓ROM检查更新地址

请求方式：get
请求地址：/ota/android/checkupdaterom
请求参数：
version 当前版本号 必填
type 包类型（1：androidROM） 必填

{
    "success": true,
    "error": "",
    "errorCode": "",
    "returnInfo": {
        "versionMapID": 41,
        "oldVersion": null,
        "newVersion": null,
        					  "fileUrl":"http://obd.careryun.com:88/static/carassist/up			datepkg/eng.developper.1493087343/update.zip",
        "fileMd5": “55EBE9E7723BA37084F3BB3C5A8630BE”,
        "otherInfo": null,
        "createDate": null,
        "updateDate": null
    }
}

app请求升级obd
请求方式：get
请求地址：/updateobd
请求参数：
simCode obd唯一标识  必填
返回值：


{ 
      "error":"",
"errorCode":"",
"returnInfo":{
	  "status":1    // 1已是最新版本 2正在升级
},
"success":true
}


服务端Socket发送升级obd信息
{
    "method": "obd_update",
"params": {
fileUrl":"http://obd.careryun.com:88/static/carassist/up			datepkg/eng.developper.1493087343/update.zip",
        "fileMd5": “55EBE9E7723BA37084F3BB3C5A8630BE” }
}


Obd上线
请求方式：get
请求地址：/obdonline
请求参数：
simCode           当前版本  必填
userId             用户id  必填
obdAppVersion      obd App当前版本  必填
obdVersion         obd当前版本  必填
返回值：
{
"success": true,
"error": "",
"errorCode": "",
"returnInfo": 
{
}
}

行程信息表：行程信息
{
    "method": "PackTripInfo",
    "params": {
        "userID": "4",
        "travelInfoID": "0",
        "trailStr": "[{\"bearing\":77.31999969482422,\"latitude\":30.59475166666667,\"loctime\":1493780418000,\"longtitude\":104.07423333333332,\"speed\":0},{\"bearing\":25.6200008392334,\"latitude\":30.591091666666664,\"loctime\":1493780427000,\"longtitude\":104.07747666666667,\"speed\":0},{\"bearing\":18.079999923706055,\"latitude\":30.591234999999998,\"loctime\":1493780432000,\"longtitude\":104.07754333333332,\"speed\":4.501760482788086}]",
        "startTime": "",
        "simCode": "f5692e2c-8cb2-4d00-951f-32874d119f39",
        "endTime": "2017-05-03 11:00:33",
        "driveTime": 141,
        "gplus": 1,
        "isFirstData": 0,
        "isUploadSuccess": 0,
        "reduce": 0,
        "distance": 0.032222223,
        "cost": 0.020944444,
        "topSpeed": 21,
        "consume": 0.0032222224,
        "carState": 1,
        "avgSpeed": 7
    }
}
###

服务端请求obd定位信息请求
{
    "method": "info_local",
    "params": {"userID\":1
              }
}
返回值
{
    "method": "info_local",
    "result": {
                "longtitude" :"11",
                "latitude" :"11",
                "lac" :"11",
                "cid" : "11"
              },
    "success": true
}
###


=======
### 1. 汽车异常数据表：汽车的异常信息。(exceptioninfo)
```
{
    "method": "PackExceInfo",
    "params": [
        {
            "SimCode": "101010",
            "ExceptionTime": "",
            "userId": "11111",
            "ExceptionValue": "10"
        },
        {
            "ExceptionTime": "",
            "userId": "11111",
            "ExceptionValue": "5.5",
            "SimCode": "101010"
        }
    ]
}
```
### 2. 汽车全局状态数据(carglobalstateinfo)
```
{
    "method": "carGlobalStateInfo",
    "params": {
        "OtherInfo": "",
        "allCost": 111.1,
        "allDriveDistance": 1111.5,
        "allDriveTime": 11111,
        "allFuelConsume": 11.2,
        "userID": "11111",
        "SimCode": "101010"
    }
}
```
### 3. 汽车运行过程中产生的“实时”的数据.(carrealtimeinfo)
```
{
    "method": "carRTStateInfo",
    "params": [{
        "AverageSpeed": 94,
        "FuelConsume": 158.969086,
        "FuelWholeConsume": 4.931937,
        "Latitud": -1,
        "Longitude": -1,
        "RMP": 3124,
        "SimCode": "123254",
        "TripMileage": 9.652500,
        "curTime": "2016-11-03 15:05:20",
        "driveDistance": 11.479000,
        "driveDuration": 145,
        "driveTime": 174,
        "engine_load": 92.549019,
        "fireState": 1,
        "oilLeft": 85.490196,
        "speed": 96,
        "userID": 20100101,
        "voltage": 11.479000,
        "waterTemp": 177,
        "driveDistance": 12.5454
    }]
}
```
### 4. 行程信息表：行程信息。(travelinfo)
```
{
    "method": "PackTripInfo",
    "params": [
        {
            "AvgSpeed": 1.1,
            "Consume": 1.1,
            "ConsumePerMile": 1.1,
            "Cost": 1.1,
            "Distance": 1.1,
            "DriveTime": 1.1,
            "EndLocation": "",
            "Grade": 1.1,
            "SimCode": "1111",

            "StartTime": "",
            "TopSpeed": 1.1,
            "TrailStr": "[{longitude:122.123456,latitude:131.123456},{longitude:122.123456,latitude:131.123456},{longitude:122.123456,latitude:131.123456}]",
            "userID": "1111"
        }
    ]
}
```
### 5. 6572升级(versionMap_6572)
```
{
    "method": "3g_requestUpdate",
    "params": {
        "oldVersion_6572": "eng.fang.1477293196",
        "oldVersion_OBD": "0.1",
        "versionMap_Android": "0.1"
    }
}
```
### 6. 获取指定网站的日期时间
```
{
  "method": "getNetworkTime",
  "params": {
    "webUrl": ""
  }
}
```
### 7. save
```
{
  "method": "OBDExceptionInfo",
  "params": {
    "TypeID": 1,
    "UserID": 1,
    "Content": ""
  }
}
```
>>>>>>> 47194f29866932d95f2512306c16bf2eb1dc2d17
