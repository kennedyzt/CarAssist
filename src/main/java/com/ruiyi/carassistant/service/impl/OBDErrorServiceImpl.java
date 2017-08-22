package com.ruiyi.carassistant.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruiyi.carassistant.dao.ObdErrorMapper;
import com.ruiyi.carassistant.service.OBDErrorService;

@Service
public class OBDErrorServiceImpl implements OBDErrorService {
    @Autowired
    private ObdErrorMapper obdErrorMapper;

    @Override
    public List<Map<String, Object>> getErrorByCode(String code) {
        return obdErrorMapper.getErrorByCode(code);
    }
    
}
