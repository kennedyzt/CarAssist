package com.ruiyi.carassistant.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ruiyi.carassistant.entity.CarInfo;

public interface CarInfoMapper {
    int deleteByPrimaryKey(String simcode);

    int insert(CarInfo record);

    int insertSelective(CarInfo record);

    CarInfo selectByPrimaryKey(String simcode);

    int updateByPrimaryKeySelective(CarInfo record);

    int updateByPrimaryKey(CarInfo record);

    @Select("select SimCode from carinfo where userID = #{userID}")
    String getSimCodeByUserID(Map<String, Object> params);

    @Select("select * from carinfo where userID = #{userID} ORDER BY in_use DESC")
    List<Map<String, Object>> getCarInfoListByUserID(Map<String, Object> userInfo);

    @Select("select count(SimCode) from carinfo where SimCode = #{simCode}")
    Long countBySimCode(String simCode);

    @Update("update carinfo set autoBrand = #{autoBrand} , autoSubBrand = #{autoSubBrand} where SimCode = #{SimCode}")
    Long uploadCarInfo(@Param("autoBrand") String autoBrand, @Param("autoSubBrand") String autoSubBrand, @Param("SimCode") String simCode);

    @Delete("delete from carinfo where userID = #{userID} and SimCode = #{simCode}")
    int deleteByUserIDAndSimCode(@Param("userID") String userID, @Param("simCode") String simCode);

    @Select("select first_letter,brand_name,brand_id,brand_logo from car_brand_info")
    List<Map<String, Object>> getAllCarBrand();

    @Select("select * from car_series_info where brand_id = #{brandId};")
    List<Map<String, Object>> getCarSeriesByBrandId(@Param("brandId") String brandId);

    @Select("select firstletter,brandname,brand_id,brand_logo,series_name,series_id,series_picture,cartype,cartype_id,emission_ml,emission_L from car_detail_info where series_id = #{seriesId}")
    List<Map<String, Object>> getCarModelsBySeriesId(@Param("seriesId") String seriesId);

    /**
     * @Title: getConsumeInfo @Description: TODO(这里用一句话描述这个方法的作用) @param @param
     *         paramMap @param @return 设定文件 @return Map<String,Object>
     *         返回类型 @throws
     */
    Map<String, Object> getConsumeInfo(Map<String, Object> paramMap);

    /**
     * @Title: getConsumeByMonth @Description:
     *         TODO(这里用一句话描述这个方法的作用) @param @param paramMap @param @return
     *         设定文件 @return List<Map<String,Object>> 返回类型 @throws
     */
    List<Map<String, Object>> getConsumeByMonth(Map<String, Object> paramMap);

    @Update("update carinfo set in_use = true , update_date = now() where SimCode = #{simCode} and userID=#{userId}")
    void changeBoxInUse(@Param("simCode") String simCode, @Param("userId") String userId);

    @Update("update carinfo set in_use = false , update_date = now() where SimCode != #{simCode} and userID=#{userId}")
    void changeBoxNotInUse(@Param("simCode") String simCode, @Param("userId") String userId);

    @Select("select SimCode simCode,userID userId,obd_app_version obdAppVersion,obd_Version obdVersion from carinfo where SimCode = #{simCode}")
    CarInfo getCarInfoBySimCode(@Param("simCode") String simCode);

}
