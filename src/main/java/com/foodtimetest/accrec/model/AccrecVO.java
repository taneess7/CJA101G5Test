package com.foodtimetest.accrec.model;

import java.math.BigDecimal;
import java.util.Date;

public class AccrecVO {
	private Integer commPayoutID;              
    private Byte orderType;                 
    private Integer orderRefId;              
    private Integer storId;             
    private Integer memId;              
    private Byte payoutRole;                 
    private BigDecimal payoutAmount;          
    private BigDecimal commissionAmount;           
    private Byte payoutStatus;                 
    private Byte commissionStatus;              
    private Date createAt;                
    private Date updateAt;                
    private Date payoutTime;                
    private String payoutMonth;
	public Integer getCommPayoutID() {
		return commPayoutID;
	}
	public void setCommPayoutID(Integer commPayoutID) {
		this.commPayoutID = commPayoutID;
	}
	public Byte getOrderType() {
		return orderType;
	}
	public void setOrderType(Byte orderType) {
		this.orderType = orderType;
	}
	public Integer getOrderRefId() {
		return orderRefId;
	}
	public void setOrderRefId(Integer orderRefId) {
		this.orderRefId = orderRefId;
	}
	public Integer getStorId() {
		return storId;
	}
	public void setStorId(Integer storId) {
		this.storId = storId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Byte getPayoutRole() {
		return payoutRole;
	}
	public void setPayoutRole(Byte payoutRole) {
		this.payoutRole = payoutRole;
	}
	public BigDecimal getPayoutAmount() {
		return payoutAmount;
	}
	public void setPayoutAmount(BigDecimal payoutAmount) {
		this.payoutAmount = payoutAmount;
	}
	public BigDecimal getCommissionAmount() {
		return commissionAmount;
	}
	public void setCommissionAmount(BigDecimal commissionAmount) {
		this.commissionAmount = commissionAmount;
	}
	public Byte getPayoutStatus() {
		return payoutStatus;
	}
	public void setPayoutStatus(Byte payoutStatus) {
		this.payoutStatus = payoutStatus;
	}
	public Byte getCommissionStatus() {
		return commissionStatus;
	}
	public void setCommissionStatus(Byte commissionStatus) {
		this.commissionStatus = commissionStatus;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Date getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	public Date getPayoutTime() {
		return payoutTime;
	}
	public void setPayoutTime(Date payoutTime) {
		this.payoutTime = payoutTime;
	}
	public String getPayoutMonth() {
		return payoutMonth;
	}
	public void setPayoutMonth(String payoutMonth) {
		this.payoutMonth = payoutMonth;
	}
    
    
    
    
}
