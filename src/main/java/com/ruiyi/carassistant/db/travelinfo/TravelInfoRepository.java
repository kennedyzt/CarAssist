package com.ruiyi.carassistant.db.travelinfo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ruiyi.carassistant.entity.TravelInfo;

public interface TravelInfoRepository extends MongoRepository<TravelInfo, String>, TravelInfoOperations {

    List<TravelInfo> getByUserId(Integer c);

    List<TravelInfo> getByUserIdLike(Integer customer);

    List<TravelInfo> getBySimCodeAndUserId(String simCode, Integer userID);

}
