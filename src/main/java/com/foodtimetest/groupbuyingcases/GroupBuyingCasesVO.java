package com.foodtimetest.groupbuyingcases;

import java.io.Serializable;
import java.sql.Date;


public class GroupBuyingCasesVO implements Serializable{

	private Integer gbID;
	private Integer storID;
	private Integer gbProdID;
	private Integer memID;
	private Date gbStartTime;
	private Date gbEndTime;
	private String gbTitle;
	private String gbDescription;
	private byte gbStatus;
	private Date gbCreateAt;
	private Integer gbMinProductQuantity;
	private String cancelReason;
	private Integer cumulativePurchaseQuantity;
	
	public Integer getGbID() {
		return gbID;
	}
	public void setGbID(Integer gbID) {
		this.gbID = gbID;
	}
	public Integer getStorID() {
		return storID;
	}
	public void setStorID(Integer storID) {
		this.storID = storID;
	}
	public Integer getGbProdID() {
		return gbProdID;
	}
	public void setGbProdID(Integer gbProdID) {
		this.gbProdID = gbProdID;
	}
	public Integer getMenID() {
		return memID;
	}
	public void setMenID(Integer menID) {
		this.memID = menID;
	}
	public Date getGbStartTime() {
		return gbStartTime;
	}
	public void setGbStartTime(Date gbStartTime) {
		this.gbStartTime = gbStartTime;
	}
	public Date getGbEndTime() {
		return gbEndTime;
	}
	public void setGbEndTime(Date gbEndTime) {
		this.gbEndTime = gbEndTime;
	}
	public String getGbTitle() {
		return gbTitle;
	}
	public void setGbTitle(String gbTitle) {
		this.gbTitle = gbTitle;
	}
	public String getGbDescription() {
		return gbDescription;
	}
	public void setGbDescription(String gbDescription) {
		this.gbDescription = gbDescription;
	}
	public byte getGbStatus() {
		return gbStatus;
	}
	public void setGbStatus(byte gbStatus) {
		this.gbStatus = gbStatus;
	}
	public Date getGbCreateAt() {
		return gbCreateAt;
	}
	public void setGbCreateAt(Date gbCreateAt) {
		this.gbCreateAt = gbCreateAt;
	}
	public Integer getGbMinProductQuantity() {
		return gbMinProductQuantity;
	}
	public void setGbMinProductQuantity(Integer gbMinProductQuantity) {
		this.gbMinProductQuantity = gbMinProductQuantity;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public Integer getCumulativePurchaseQuantity() {
		return cumulativePurchaseQuantity;
	}
	public void setCumulativePurchaseQuantity(Integer cumulativePurchaseQuantity) {
		this.cumulativePurchaseQuantity = cumulativePurchaseQuantity;
	}
	
	
	
}
