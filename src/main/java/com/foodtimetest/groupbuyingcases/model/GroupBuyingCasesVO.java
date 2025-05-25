package com.foodtimetest.groupbuyingcases.model;

import java.io.Serializable;
import java.util.Date;


public class GroupBuyingCasesVO implements Serializable{

	private Integer gbId;
	private Integer storId;
	private Integer gbProdId;
	private Integer memId;
	private Date gbStartTime;
	private Date gbEndTime;
	private String gbTitle;
	private String gbDescription;
	private byte gbStatus;
	private Date gbCreateAt;
	private Integer gbMinProductQuantity;
	private String cancelReason;
	private Integer cumulativePurchaseQuantity;
	public Integer getGbId() {
		return gbId;
	}
	public void setGbId(Integer gbId) {
		this.gbId = gbId;
	}
	public Integer getStorId() {
		return storId;
	}
	public void setStorId(Integer storId) {
		this.storId = storId;
	}
	public Integer getGbProdId() {
		return gbProdId;
	}
	public void setGbProdId(Integer gbProdId) {
		this.gbProdId = gbProdId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
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
