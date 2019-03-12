package com.gupao.prototype;


import com.google.gson.annotations.SerializedName;

public class PersonalInfoModel {
	/* ***************** 个人用户信息 ***************/

	/**
	 * 用户编号
	 */
	@SerializedName("id")
	private Long userId;


	/**
	 * 个人用户证件号
	 */
	private String credNum;
	
	/**
	 * 是否开通一账通用户
	 */
	@SerializedName("isOpenOnepassport")
	private boolean isOpenOnePassport;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getCredNum() {
		return credNum;
	}

	public void setCredNum(String credNum) {
		this.credNum = credNum;
	}


	public boolean isOpenOnePassport() {
		return isOpenOnePassport;
	}

	public void setOpenOnePassport(boolean isOpenOnePassport) {
		this.isOpenOnePassport = isOpenOnePassport;
	}
	
	
}
