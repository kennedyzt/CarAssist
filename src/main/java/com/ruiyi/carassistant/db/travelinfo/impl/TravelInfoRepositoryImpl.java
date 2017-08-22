package com.ruiyi.carassistant.db.travelinfo.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.ruiyi.carassistant.db.travelinfo.TravelInfoOperations;
import com.ruiyi.carassistant.entity.ObdInfo;
import com.ruiyi.carassistant.entity.TravelInfo;
import com.ruiyi.carassistant.utils.BeanUtil;
import com.ruiyi.carassistant.utils.DateUtil;
import com.ruiyi.carassistant.utils.NumberUtil;

public class TravelInfoRepositoryImpl implements TravelInfoOperations {
    @Autowired
    private MongoOperations mongo;

    @Override
    public TravelInfo getLastTripInfoBySimCode(String simCode) {
        BasicDBObject filter_dbobject = new BasicDBObject();
        filter_dbobject.put("simCode", simCode);
        filter_dbobject.put("carState", "1");
        DBCursor limit = mongo.getCollection("travelInfo").find(filter_dbobject).sort(new BasicDBObject("_id", -1)).limit(1);
        TravelInfo lastTravelInfo = new TravelInfo();
        while (limit.hasNext()) {
            try {
                DBObject dbObject = limit.next();
                lastTravelInfo = BeanUtil.dbObject2Bean(dbObject, lastTravelInfo);
                lastTravelInfo.setTravelInfoId(dbObject.get("_id").toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lastTravelInfo;
    }

    @Override
    public void appendTrailStr(String travelInfoID, String trailJsonStr) {
<<<<<<< HEAD
        if (StringUtils.hasText(trailJsonStr) && !trailJsonStr.equals("[]")) {
=======
        if (StringUtils.hasText(trailJsonStr)) {
>>>>>>> 47194f29866932d95f2512306c16bf2eb1dc2d17
            Map<String, Object> updateMap = new LinkedHashMap<>();
            @SuppressWarnings("unchecked")
            List<ObdInfo> obdInfos = JSONArray.parseObject(trailJsonStr, List.class);
            updateMap.put("trailStr", obdInfos);
            DBObject updateTrivelDBObject = new BasicDBObject(updateMap);
<<<<<<< HEAD
            mongo.updateFirst(Query.query(Criteria.where("_id").is(travelInfoID)), Update.fromDBObject(new BasicDBObject("$pushAll", updateTrivelDBObject), new String[]{}), "travelInfo");
=======
            mongo.updateFirst(Query.query(Criteria.where("_id").is(travelInfoID)), Update.fromDBObject(new BasicDBObject("$pushAll", updateTrivelDBObject), new String[] {}), "travelInfo");
>>>>>>> 47194f29866932d95f2512306c16bf2eb1dc2d17
        }
    }

    @Override
    public void updateByPrimaryKeySelective(TravelInfo tripInfo) throws Exception {
        DBObject update = BeanUtil.bean2DBObject(tripInfo);
        // 添加GPS记录
        appendTrailStr(tripInfo.getTravelInfoId(), tripInfo.getTrailStr());
        update.removeField("trailStr");
        // 查询现在数据
        TravelInfo oldTravelInfo = mongo.findOne(Query.query(Criteria.where("_id").is(tripInfo.getTravelInfoId())), TravelInfo.class, "travelInfo");
        Integer gplus = oldTravelInfo.getGplus() + tripInfo.getGplus();
        Integer reduce = oldTravelInfo.getReduce() + tripInfo.getReduce();
        float distance = oldTravelInfo.getDistance() + tripInfo.getDistance();
        float driveTime = (DateUtil.getTimeByString(tripInfo.getEndTime()) - DateUtil.getTimeByString(oldTravelInfo.getStartTime())) / 1000;
        update.put("consume", oldTravelInfo.getConsume() + tripInfo.getConsume());
        update.put("cost", oldTravelInfo.getCost() + tripInfo.getCost());
        update.put("grade", 100 - (gplus + 2 * reduce) >= 45 ? 100 - (gplus + 2 * reduce) : 45);
        update.put("topSpeed", oldTravelInfo.getTopSpeed() > tripInfo.getTopSpeed() ? oldTravelInfo.getTopSpeed() : tripInfo.getTopSpeed());
        update.put("cost", oldTravelInfo.getCost() + tripInfo.getCost());
        update.put("updateDate", DateUtil.getCurrentTime());
        update.put("driveTime", driveTime);
        update.put("distance", distance);
        if (driveTime != 0) {
            update.put("avgSpeed", NumberUtil.format3(distance / (driveTime / 3600), 2));
        } else {
            update.put("avgSpeed", 0);
        }
<<<<<<< HEAD
        mongo.updateFirst(Query.query(Criteria.where("_id").is(tripInfo.getTravelInfoId())), Update.fromDBObject(new BasicDBObject("$set", update), new String[]{"avgSpeed"}), "travelInfo");
=======
        mongo.updateFirst(Query.query(Criteria.where("_id").is(tripInfo.getTravelInfoId())), Update.fromDBObject(new BasicDBObject("$set", update), new String[] { "avgSpeed" }), "travelInfo");
>>>>>>> 47194f29866932d95f2512306c16bf2eb1dc2d17
    }

    @Override
    public List<Map<String, Object>> getSyncTravelInfoByParams(String simCode, Integer userId, Integer travelInfoID) {
        List<Map<String, Object>> result = new ArrayList<>();
        BasicDBObject filter_dbobject = new BasicDBObject();
        filter_dbobject.put("simCode", simCode);
        filter_dbobject.put("userId", userId);
        DBCursor limit = mongo.getCollection("travelInfo").find(filter_dbobject).sort(new BasicDBObject("_id", 1)).skip(travelInfoID);
        while (limit.hasNext()) {
            buildTravelInfoMap(result, limit);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private void buildTravelInfoMap(List<Map<String, Object>> result, DBCursor limit) {
        DBObject dbObject = limit.next();
        dbObject.put("TravelInfoID", dbObject.removeField("_id").toString());
        dbObject.removeField("_class");
        dbObject.removeField("createDate");
        dbObject.removeField("updateDate");
        // 转换key值大小写
        dbObject.put("SimCode", dbObject.removeField("simCode"));
        dbObject.put("AvgSpeed", dbObject.removeField("avgSpeed"));
        dbObject.put("DriveTime", dbObject.removeField("driveTime"));
        dbObject.put("Reduce", dbObject.removeField("reduce"));
        dbObject.put("Grade", dbObject.removeField("grade"));
        dbObject.put("Distance", dbObject.removeField("distance"));
        dbObject.put("Cost", dbObject.removeField("cost"));
        dbObject.put("TopSpeed", dbObject.removeField("topSpeed"));
        dbObject.put("StartTime", dbObject.removeField("startTime"));
        dbObject.put("EndTime", dbObject.removeField("endTime"));
        dbObject.put("Gplus", dbObject.removeField("gplus"));
        dbObject.put("CarState", dbObject.removeField("carState"));
        dbObject.put("TrailStr", dbObject.removeField("trailStr").toString());
        dbObject.put("Consume", dbObject.removeField("consume"));
        dbObject.put("ConsumePerMile", dbObject.removeField("consumePerMile"));
        result.add((Map<String, Object>) dbObject);
    }

    public List<Map<String, Object>> getTravelInfoByMap(BasicDBObject filter_dbobject, Integer indexLength, Integer indexStart) {
        List<Map<String, Object>> result = new ArrayList<>();
        DBCursor limit = mongo.getCollection("travelInfo").find(filter_dbobject).sort(new BasicDBObject("_id", -1)).limit(indexLength).skip(indexStart);
        while (limit.hasNext()) {
            buildTravelInfoMap(result, limit);
        }
        return result;
    }

}
