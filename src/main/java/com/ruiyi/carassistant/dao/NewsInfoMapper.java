package com.ruiyi.carassistant.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.ruiyi.carassistant.entity.NewsInfo;

public interface NewsInfoMapper {
    int deleteByPrimaryKey(Integer newsid);

    int insert(NewsInfo record);

    int insertSelective(NewsInfo record);

    NewsInfo selectByPrimaryKey(Integer newsid);

    int updateByPrimaryKeySelective(NewsInfo record);

    int updateByPrimaryKey(NewsInfo record);

    @Select("select * from newsinfo where NewsCategoryID = #{newsCategoryID} order by Datetime limit #{indexStart},#{indexLength}")
    List<Map<String, Object>> getNewsList(Map<String, Object> params);
}