package com.ruiyi.carassistant.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruiyi.carassistant.dao.NewsCategoryMapper;
import com.ruiyi.carassistant.service.NewsCategoryService;

@Service
public class NewsCategoryServiceImpl implements NewsCategoryService {

    @Autowired
    private NewsCategoryMapper categoryMapper;
    
    @Override
    public List<Map<String, Object>> getCategoryInfo() {
        return categoryMapper.getCategoryInfo();
    }

}
