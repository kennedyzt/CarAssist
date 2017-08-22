/**   
* @Title: SuggestionServiceImpl.java 
* @Package com.ruiyi.carassistant.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com   
* @date 2017年3月16日 上午9:30:37 
* @version V1.0   
*/
package com.ruiyi.carassistant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruiyi.carassistant.dao.SuggestionMapper;
import com.ruiyi.carassistant.entity.Suggestion;
import com.ruiyi.carassistant.service.SuggestionService;

/**
 * @ClassName: SuggestionServiceImpl
 * @Description:
 * @author tao.zeng
 * @date 2017年3月16日 上午9:30:37
 * 
 */
@Service
public class SuggestionServiceImpl implements SuggestionService {
	@Autowired
	private SuggestionMapper suggestionMapper;

	/*
	 * (非 Javadoc) <p>Title: add</p> <p>Description: </p>
	 * 
	 * @param suggestion
	 * 
	 * @see com.ruiyi.carassistant.service.SuggestionService#add(com.ruiyi.
	 * carassistant.entity.Suggestion)
	 */
	@Override
	public void add(Suggestion suggestion) throws Exception {
		suggestionMapper.add(suggestion);
	}

}
