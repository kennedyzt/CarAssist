/**   
* @Title: SuggestionMapper.java 
* @Package com.ruiyi.carassistant.dao 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com   
* @date 2017年3月16日 上午9:47:11 
* @version V1.0   
*/
package com.ruiyi.carassistant.dao;

import com.ruiyi.carassistant.entity.Suggestion;

/**
 * @ClassName: SuggestionMapper
 * @Description:
 * @author tao.zeng
 * @date 2017年3月16日 上午9:47:11
 * 
 */
public interface SuggestionMapper {

	/** 
	* @Title: add 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param suggestion    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void add(Suggestion suggestion);

}
