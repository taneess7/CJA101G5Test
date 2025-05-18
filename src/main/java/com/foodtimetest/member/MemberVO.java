package com.foodtimetest.member;

import java.io.Serializable;
import java.sql.Date;


public class MemberVO implements Serializable {
	
	private Integer memId;
	private String memEmail;
	private String memAccount;
	private String memPassword;
	private String memNickname;
	private String memName;
	private String memPhone;
	private Byte memGender;
	private String memCity;
	private String memCityarea;
	private String memAddress;
	private String memCode;
	private byte[] memAvatar;
	private Date memTime;
	private Byte memStatus;
	private Byte memNoSpeak;
	private Byte memNoPost;
	private Byte memNoGroup;
	private Byte memNOJoingroup;
	private Integer totalStarNum;
	private Integer totalReviews;
	
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemAccount() {
		return memAccount;
	}
	public void setMemAccount(String memAccount) {
		this.memAccount = memAccount;
	}
	public String getMemPassword() {
		return memPassword;
	}
	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}
	public String getMemNickname() {
		return memNickname;
	}
	public void setMemNickname(String memNickname) {
		this.memNickname = memNickname;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public short getMemGender() {
		return memGender;
	}
	public void setMemGender(Byte memGender) {
		this.memGender = memGender;
	}
	public String getMemCity() {
		return memCity;
	}
	public void setMemCity(String memCity) {
		this.memCity = memCity;
	}
	public String getMemCityarea() {
		return memCityarea;
	}
	public void setMemCityarea(String memCityarea) {
		this.memCityarea = memCityarea;
	}
	public String getMemAddress() {
		return memAddress;
	}
	public void setMemAddress(String memAddress) {
		this.memAddress = memAddress;
	}
	public String getMemCode() {
		return memCode;
	}
	public void setMemCode(String memCode) {
		this.memCode = memCode;
	}
	public byte[] getMemAvatar() {
		return memAvatar;
	}
	public void setMemAvatar(byte[] memAvatar) {
		this.memAvatar = memAvatar;
	}
	public Date getMemTime() {
		return memTime;
	}
	public void setMemTime(Date memTime) {
		this.memTime = memTime;
	}
	public short getMemStatus() {
		return memStatus;
	}
	public void setMemStatus(Byte memStatus) {
		this.memStatus = memStatus;
	}
	public short getMemNoSpeak() {
		return memNoSpeak;
	}
	public void setMemNoSpeak(Byte memNoSpeak) {
		this.memNoSpeak = memNoSpeak;
	}
	public short getMemNoPost() {
		return memNoPost;
	}
	public void setMemNoPost(Byte memNoPost) {
		this.memNoPost = memNoPost;
	}
	public short getMemNoGroup() {
		return memNoGroup;
	}
	public void setMemNoGroup(Byte memNoGroup) {
		this.memNoGroup = memNoGroup;
	}
	public short getMemNOJoingroup() {
		return memNOJoingroup;
	}
	public void setMemNOJoingroup(Byte memNOJoingroup) {
		this.memNOJoingroup = memNOJoingroup;
	}
	public Integer getTotalStarNum() {
		return totalStarNum;
	}
	public void setTotalStarNum(Integer totalStarNum) {
		this.totalStarNum = totalStarNum;
	}
	public Integer getTotalReviews() {
		return totalReviews;
	}
	public void setTotalReviews(Integer totalReviews) {
		this.totalReviews = totalReviews;
	}
	

}
