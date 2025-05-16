package com.foodtimetest.gbcases.model;

import java.util.Date;

public class GbcasesVO {
	private Integer gbId;               
	private Integer storId;                
	private Integer gbProdId;
	private Integer memId;
	private Date gbStartTime;                 
	private Date gbEndTime;                   
	private String gbTitle;               
	private String gbDescription;        
	private Byte gbStatus;               
	private Date gbCreateAt;                   
	private Integer gbMinProdQuantity;          
	private String cancelReason;      
	private Integer cumulativePQ;
	
	
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
	public Byte getGbStatus() {
		return gbStatus;
	}
	public void setGbStatus(Byte gbStatus) {
		this.gbStatus = gbStatus;
	}
	public Date getGbCreateAt() {
		return gbCreateAt;
	}
	public void setGbCreateAt(Date gbCreateAt) {
		this.gbCreateAt = gbCreateAt;
	}
	public Integer getGbMinProdQuantity() {
		return gbMinProdQuantity;
	}
	public void setGbMinProdQuantity(Integer gbMinProdQuantity) {
		this.gbMinProdQuantity = gbMinProdQuantity;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public Integer getCumulativePQ() {
		return cumulativePQ;
	}
	public void setCumulativePQ(Integer cumulativePQ) {
		this.cumulativePQ = cumulativePQ;
	}       

}
