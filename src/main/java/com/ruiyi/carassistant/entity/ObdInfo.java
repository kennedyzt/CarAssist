/**   
* @Title: ObdInfo.java 
* @Package com.ruiyi.carassistant.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com   
* @date 2017年4月12日 下午12:55:43 
* @version V1.0   
*/
package com.ruiyi.carassistant.entity;

import java.io.Serializable;

/**
 * @ClassName: ObdInfo
 * @Description:
 * @author tao.zeng
 * @date 2017年4月12日 下午12:55:43
 * 
 */
public class ObdInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String longtitude; // 经度
	private String latitude; // 纬度
	private String bearing; // 转速
	private String speed; // 平均速度
	private String loctime; // 时间戳

	/**
	 * @return longtitude
	 */
	public String getLongtitude() {
		return longtitude;
	}

	/**
	 * @param longtitude
	 *            要设置的 longtitude
	 */
	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}

	/**
	 * @return latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            要设置的 latitude
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return bearing
	 */
	public String getBearing() {
		return bearing;
	}

	/**
	 * @param bearing
	 *            要设置的 bearing
	 */
	public void setBearing(String bearing) {
		this.bearing = bearing;
	}

	/**
	 * @return speed
	 */
	public String getSpeed() {
		return speed;
	}

	/**
	 * @param speed
	 *            要设置的 speed
	 */
	public void setSpeed(String speed) {
		this.speed = speed;
	}

	/**
	 * @return loctime
	 */
	public String getLoctime() {
		return loctime;
	}

	/**
	 * @param loctime
	 *            要设置的 loctime
	 */
	public void setLoctime(String loctime) {
		this.loctime = loctime;
	}

}