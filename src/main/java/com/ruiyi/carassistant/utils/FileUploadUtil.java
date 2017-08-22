/**   
* @Title: FileUpload.java 
* @Package com.ruiyi.carassistant.utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com   
* @date 2017年3月10日 下午6:54:53 
* @version V1.0   
*/
package com.ruiyi.carassistant.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: FileUpload
 * @Description:
 * @author tao.zeng
 * @date 2017年3月10日 下午6:54:53
 * 
 */
public class FileUploadUtil {
	public static Map<String, Object> upload(MultipartFile multipartFile, String path) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String fileName = multipartFile.getOriginalFilename().substring(0,
				multipartFile.getOriginalFilename().lastIndexOf("."));
		resultMap.put("fileName", fileName);
		String aliasFileName = fileName + "_" + System.currentTimeMillis()
				+ multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
		resultMap.put("aliasFileName", aliasFileName);
		File file = new File(path + "/" + aliasFileName);
		try {
			if (!file.exists()) {
				file.mkdirs();
				LoggerUtil.debug("创建文件保存目录:" + file.getPath());
			}
			LoggerUtil.debug("文件保存目录:" + file.getPath());
			multipartFile.transferTo(file);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return resultMap;
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		Boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}
}
