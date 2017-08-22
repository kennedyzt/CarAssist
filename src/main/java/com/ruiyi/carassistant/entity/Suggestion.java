package com.ruiyi.carassistant.entity;

import java.io.Serializable;

public class Suggestion implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer userID;

	private Short msgType;

	private String nickName;

	private String phone;

	private String content;

	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            要设置的 id
	 */
	public void setId(Long id) {
		this.id = id;
	}

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
	 * @return msgType
	 */
	public Short getMsgType() {
		return msgType;
	}

	/**
	 * @param msgType
	 *            要设置的 msgType
	 */
	public void setMsgType(Short msgType) {
		this.msgType = msgType;
	}

	/**
	 * @return nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName
	 *            要设置的 nickName
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            要设置的 content
	 */
	public void setContent(String content) {
		this.content = content;
	}

}