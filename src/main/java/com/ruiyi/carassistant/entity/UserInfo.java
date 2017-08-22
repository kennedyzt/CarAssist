package com.ruiyi.carassistant.entity;

public class UserInfo {

	private Integer userID;

	private String phone;

	private String userName;

	private String email;

	private String loginName;

	private Integer userType; // 1.QQ2.微信3.微博4.手机号

	private Integer point;

	private String token;

	private String password;

	private String userHeadUrl;

	/**
	 * @return userID
	 */
	public Integer getUserID() {
		return userID;
	}

	/**
	 * @param userID
	 *            要设置的 userID
	 */
	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	/**
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            要设置的 phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            要设置的 userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            要设置的 email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName
	 *            要设置的 loginName
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return userType
	 */
	public Integer getUserType() {
		return userType;
	}

	/**
	 * @param userType
	 *            要设置的 userType
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	/**
	 * @return point
	 */
	public Integer getPoint() {
		return point;
	}

	/**
	 * @param point
	 *            要设置的 point
	 */
	public void setPoint(Integer point) {
		this.point = point;
	}

	/**
	 * @return token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            要设置的 token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            要设置的 password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return userHeadUrl
	 */
	public String getUserHeadUrl() {
		return userHeadUrl;
	}

	/**
	 * @param userHeadUrl
	 *            要设置的 userHeadUrl
	 */
	public void setUserHeadUrl(String userHeadUrl) {
		this.userHeadUrl = userHeadUrl;
	}

}