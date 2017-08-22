package com.ruiyi.carassistant.service;

import java.util.List;
import java.util.Map;

public interface NewsListService {

    List<Map<String, Object>> getNewsList(Map<String, Object> params);

}
