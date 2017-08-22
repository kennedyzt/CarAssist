###行程信息表：行程信息。(PackTripInfo)
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

###服务端请求obd定位信息
请求
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