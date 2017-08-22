/**   
* @Title: SuggestionService.java 
* @Package com.ruiyi.carassistant.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com   
* @date 2017年3月16日 上午9:23:45 
* @version V1.0   
*/
package com.ruiyi.carassistant.service;

import com.ruiyi.carassistant.entity.Suggestion;

/**
 * @ClassName: SuggestionService
 * @Description:
 * @author tao.zeng
 * @date 2017年3月16日 上午9:23:45
 * 
 */
public interface SuggestionService {

	/**
	 * @throws Exception
	 * @Title: add @Description: TODO(这里用一句话描述这个方法的作用) @param @param suggestion
	 *         设定文件 @return void 返回类型 @throws
	 */
	void add(Suggestion suggestion) throws Exception;

}
