/**   
* @Title: SuggestionController.java 
* @Package com.ruiyi.carassistant.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com   
* @date 2017年3月15日 下午8:27:51 
* @version V1.0   
*/
package com.ruiyi.carassistant.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiyi.carassistant.entity.Suggestion;
import com.ruiyi.carassistant.service.SuggestionService;

/**
 * @ClassName: 意见反馈
 * @Description:
 * @author tao.zeng
 * @date 2017年3月15日 下午8:27:51
 * 
 */
@Controller
@RequestMapping("/suggestion")
public class SuggestionController {
	@Autowired
	private SuggestionService suggestionService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addSuggestion(Suggestion Suggestion) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			suggestionService.add(Suggestion);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("error", "服务器异常");
			result.put("success", false);
			return result;
		}
		result.put("error", "");
		result.put("success", true);
		return result;
	}
}
