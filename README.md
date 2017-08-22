### 在线接口测试地址
```
http://120.24.152.177/CarAssist/apis.html#/
```
---

### [用户信息接口](http://*.*.*.*/CarAssist/apis.html#!/用户信息接口)
###### 1.用户登录

```
请求URL
http://*.*.*.*/CarAssist/LoginServlet?phone=123456&password=123456
响应体
{ "returnInfo": { "userID": 2, "SimCode": "", "token": 5 }, "error": "", "success": true}
```
```
请求URL
http://*.*.*.*/CarAssist/LoginServlet?phone=123456&password=1
响应体
{ "returnInfo": null, "error": "fail to login!", "success": false}
```
###### 2.用户注册
```
请求URL
http://*.*.*.*/CarAssist/RegisterServlet?phone=1234567&password=1234567
响应体
{ "returnInfo": { "userID": 4, "phone": "1234567", "token": "1", "password": "1234567" }, "error": "", "success": true}
```
```
请求URL
http://*.*.*.*/CarAssist/RegisterServlet?phone=1234567&password=1234567
响应体
{ "returnInfo": {}, "error": "Your phone number have registered!", "success": false}
```
---
###[汽车信息接口](http://*.*.*.*/CarAssist/apis.html#!/汽车信息接口)
##### 1.绑定盒子
```
请求URL
http://*.*.*.*/CarAssist/BindBoxServlet?token=6&userID=2&SimCode=123456
响应体
{"returnInfo":"","error":"","success":true}
```
```
请求URL
http://*.*.*.*/CarAssist/BindBoxServlet?token=6&userID=2&SimCode=123456
响应体
{"error":"SimCode have existed!","returnInfo":null,"success":false}
```
##### 2.解绑盒子
```
请求URL
http://120.24.152.177/CarAssist/unBindBoxServlet?token=7&userID=2&SimCode=123456
响应体
{"returnInfo":"","error":"","success":true}
```
```
请求URL
http://120.24.152.177/CarAssist/unBindBoxServlet?token=7&userID=2&SimCode=123
响应体
{"error":"SimCode is not exist!","returnInfo":null,"success":false}
```

##### 3.上传汽车信息
```
请求URL
http://*.*.*.*/CarAssist/UploadCarInfoServlet?userID=2&token=6&autoBrand=123456&autoSubBrand=123456&SimCode=123456
响应体
{"returnInfo":"","error":"","success":true}
```
```
请求URL
http://*.*.*.*/CarAssist/UploadCarInfoServlet?userID=2&token=6&autoBrand=123456&autoSubBrand=123456&SimCode=1234567
响应体
{"error":"SimCode is not exist!","returnInfo":null,"success":false}
```
##### 4.获取所有的汽车品牌信息
```
请求URL
http://127.0.0.1/CarAssist/getAllCarBrand?userID=1&token=1
响应体
{"error":"","returnInfo":[],"success":true}
```
##### 5.获取指定汽车品牌的所有车型信息
```
请求URL
http://127.0.0.1/CarAssist/getCarInfoByBrandId?userID=1&token=1&brandId=79
响应体
{"error":"","returnInfo":[],"success":true}
```
---
### [排行信息接口](http://*.*.*.*/CarAssist/apis.html#!/排行信息接口)
##### 1.获取排行榜数据
```
请求URL
http://*.*.*.*/CarAssist/GetRankListByDistanceServlet?userID=2&token=6
响应体
{"returnInfo":[{"allDriveDistance":2.47972},{"allDriveDistance":1.0}],"error":"","success":true}
```
```
请求URL
http://*.*.*.*/CarAssist/GetRankListByDistanceServlet?userID=1&token=6
响应体
{"error":"please login again!","returnInfo":null,"success":false}
```
---
### [全局信息接口](http://*.*.*.*/CarAssist/apis.html#!/全局信息接口)
```
请求URL
http://*.*.*.*/CarAssist/GetGlobalInfoServlet?token=6&userID=2&SimCode=123456
响应体
{"returnInfo":{"allDriveDistance":1.0,"userID":2,"SimCode":"123456","allDriveTime":1.0,"allFuelConsume":1.0,"allCost":1.0,"otherInfo":"1"},"error":"","success":true}
```
```
请求URL
http://*.*.*.*/CarAssist/GetGlobalInfoServlet?token=6&userID=2&SimCode=1234561
响应体
{"error":"data is null!","returnInfo":null,"success":false}
```
---
### [系统信息接口](http://*.*.*.*/CarAssist/apis.html#!/系统信息接口)
##### 获取差分包信息
```
请求URL
http://127.0.0.1/CarAssist/GetSystemVersionInfoServlet?oldVersion_6572=eng.fang.1472886349
响应体
{"returnInfo":{"url_6572":"http:\/\/120.24.152.177:8080\/OtaUpdatePkg\/eng.fang.1472886349\/update.zip","fileMD5_6572":"asdasdasdasdasdasdasdasdasdasdas"},"error":"","success":true}
```
```
请求URL
http://127.0.0.1/CarAssist/GetSystemVersionInfoServlet?oldVersion_6572=eng.fang.147288634
响应体
{"error":"version is not exist!","returnInfo":null,"success":false}
```
```
请求URL
http://127.0.0.1/CarAssist/GetSystemVersionInfoServlet
响应体
{"error":"parameters error!","returnInfo":{},"success":false}
```
---
### [新闻信息接口](http://*.*.*.*/CarAssist/apis.html#!/新闻信息接口)
##### 1.获取新闻列表
```
请求URL
http://127.0.0.1/CarAssist/GetNewsListServlet?userID=2&token=10&newsCategoryID=1&pageNum=1&pageSize=5
响应体
{"returnInfo":[{"NewsCategoryID":1,"NewsTitle":"test1","PictureURL":"http:\/\/img2.bitautoimg.com\/autoalbum\/files\/20140825\/492\/23201149237008_3551273_14.JPG","KeyWords":"奔驰","NewsContentURL":"http:\/\/news.bitauto.com\/iihs\/20161009\/1406640599.html","NewsID":1,"NewsSummary":"eee"}],"error":"","success":true}
```
```
请求URL
http://*.*.*.*/CarAssist/GetNewsListServlet?userID=2&token=1&pageNum=1&pageSize=5
响应体
{"error":"please login again!","returnInfo":null,"success":false}
```
##### 2.获取新闻种类列表
```
请求URL
http://127.0.0.1/CarAssist/getNewsCategoryList?userID=2&token=7
响应体
{"returnInfo":[{"id":1,"name":"汽车"}],"error":"","success":true}
```
```
请求URL
http://127.0.0.1/CarAssist/GetNewsListServlet?userID=2&token=10&newsCategoryID=2&pageNum=1&pageSize=5
响应体
{"returnInfo":[],"error":"data is null!","success":false}
```
---
### [行程信息接口](http://*.*.*.*/CarAssist/apis.html#!/行程信息接口)
##### 1.获取行程单列表
```
请求URL
http://*.*.*.*/CarAssist/GetTravelInfoServlet?token=6&userID=2&SimCode=123456&pageNum=1&pageSize=5
响应体
{"returnInfo":[],"error":"data is null!","success":false}
```
##### 2.同步行程单
```
请求URL
http://120.24.152.177/CarAssist/SyncTravelInfo?token=4&userID=97&SimCode=2D1089F8-908A-440F-B4F7-046C049E0AF4&TravelInfoID=85
响应体
{"returnInfo":[{"SimCode":"2D1089F8-908A-440F-B4F7-046C049E0AF4","EndLocation":null,"TopSpeed":4.0,"Distance":0.014,"DriveTime":149.0,"ConsumePerMile":null,"Cost":0.008,"userID":97,"AvgSpeed":0.0,"StartLocationDesc":null,"Grade":77.0,"TravelInfoID":87,"TrailStr":null,"EndTime":"2016-12-15 09:46:35","EndLocationDesc":null,"StartLocation":null,"StartTime":"2016-12-15 09:44:33","Consume":null},{"SimCode":"2D1089F8-908A-440F-B4F7-046C049E0AF4","EndLocation":null,"TopSpeed":74.0,"Distance":6.754,"DriveTime":1549.0,"ConsumePerMile":null,"Cost":3.715,"userID":97,"AvgSpeed":0.0,"StartLocationDesc":null,"Grade":77.0,"TravelInfoID":86,"TrailStr":null,"EndTime":"2016-12-15 09:38:34","EndLocationDesc":null,"StartLocation":null,"StartTime":"2016-12-15 08:59:34","Consume":null}],"error":"","success":true}
```
##### 3.同步行程单
```
请求URL
http://120.24.152.177/CarAssist/pushTravelInfo?token=4&userID=97&travelInfo=[{},{}]
响应体
{"returnInfo":"","error":"","success":true}
```
---
### [OBD错误码接口](http://*.*.*.*/CarAssist/apis.html#!/OBD错误码接口)
##### 1.获取错误码对应的描述信息
```
请求URL
http://*.*.*.*/CarAssist/SearchOBDErrorCode?userID=2&token=13&OBDErrorCode=P1001
响应体
{"returnInfo":[{"obderror_id":2267,"obderror_advice":"","obderror_adviceid":0,"obderror_type":"NULL","obderror_desc_cn":"可变气门正时（VVT）跛行回家请求 - 高输入","obderror_infomation":"凸轮轴的作用是控制气门的开启和闭合。可变气门正时系统中，电子凸轮轴调节阀（或机油控制阀，OCV）根据来自发动机控制模块（ECM）的指令通过改变到凸轮轴调节器（机械式）的机油压力的方式来调整凸轮轴的角度，以确保气门在最佳时间打开和关闭。从而优化发动机在所有转速范围内的效率和功率。","obderror_name":"P1001","obderror_car":"宝马","obderror_desc_en":"Variable Valve Timing (VVT) Limp Home Request - High Input","obderror_category":"动力总成系统（制造商自定义）"},{"obderror_id":2268,"obderror_advice":"","obderror_adviceid":0,"obderror_type":"NULL","obderror_desc_cn":"钥匙开，发动机运行（KOER）自检没有完成，自检放弃","obderror_infomation":"故障原因包括自检过程中发动机转速超出标准，错误的自检过程，扫描工具通讯问题，来自自检监控器难以预料的反应等。","obderror_name":"P1001","obderror_car":"福特，水星，林肯","obderror_desc_en":"Key on Engine Running (KOER) Self Test Not Able to Complete, KOER Aborted","obderror_category":"动力总成系统（制造商自定义）"},{"obderror_id":2269,"obderror_advice":"","obderror_adviceid":0,"obderror_type":"NULL","obderror_desc_cn":"数据连接器 - 线路故障","obderror_infomation":"该故障码表明故障码扫描仪无法跟电子模块通讯。","obderror_name":"P1001","obderror_car":"马自达","obderror_desc_en":"Data Link Connector - Circuit Malfunction","obderror_category":"动力总成系统（制造商自定义）"}],"error":"","success":true}
```
```
请求URL
http://*.*.*.*/CarAssist/SearchOBDErrorCode?userID=2&token=6&OBDErrorCode=P10000
响应体
{"error":"data is null!","returnInfo":null,"success":false}
```