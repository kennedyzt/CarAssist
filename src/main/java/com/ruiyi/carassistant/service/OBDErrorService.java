package com.ruiyi.carassistant.service;

import java.util.List;
import java.util.Map;

public interface OBDErrorService {

	List<Map<String, Object>> getErrorByCode(String code);

}
