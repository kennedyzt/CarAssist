package com.ruiyi.carassistant.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface NewsCategoryMapper {

    @Select("SELECT * FROM news_category")
    List<Map<String, Object>> getCategoryInfo();

}
