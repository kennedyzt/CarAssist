package com.ruiyi.carassistant.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruiyi.carassistant.dao.NewsInfoMapper;
import com.ruiyi.carassistant.service.NewsListService;

@Service
public class NewsListServiceImpl implements NewsListService {
    @Autowired
    private NewsInfoMapper newsInfoMapper;

    @Override
    public List<Map<String, Object>> getNewsList(Map<String, Object> params) {
        return newsInfoMapper.getNewsList(params);
    }

}
