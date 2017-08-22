package com.ruiyi.carassistant.service;

import java.util.List;
import java.util.Map;

public interface NewsCategoryService {

    /**
     * 获取所有新闻种类信息
     * @return
     * @author Yipd
     * @date 2016-11-1 上午9:25:03
     */
    List<Map<String, Object>> getCategoryInfo();

}
