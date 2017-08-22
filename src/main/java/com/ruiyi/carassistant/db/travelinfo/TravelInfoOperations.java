package com.ruiyi.carassistant.db.travelinfo;

import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.ruiyi.carassistant.entity.TravelInfo;

public interface TravelInfoOperations {

    TravelInfo getLastTripInfoBySimCode(String simCode);

    void appendTrailStr(String travelInfoID, String trailJsonStr);

    void updateByPrimaryKeySelective(TravelInfo tripInfo) throws Exception;

    List<Map<String, Object>> getSyncTravelInfoByParams(String simCode, Integer userId, Integer travelInfoID);

    List<Map<String, Object>> getTravelInfoByMap(BasicDBObject filter_dbobject, Integer indexLength, Integer indexStart);
}
