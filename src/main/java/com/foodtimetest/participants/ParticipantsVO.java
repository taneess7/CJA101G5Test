package com.foodtimetest.participants;

import java.io.Serializable;
import java.math.BigDecimal;

public class ParticipantsVO implements Serializable {

	private Integer parID;
	private Integer memID;
	private Integer gbID;
	private String parPhone;
	private String paeName;
	private String parAddress;
	private BigDecimal parLongitude;
	private BigDecimal parLatitude;
	private boolean isLeader;
	private Integer parPurchaseQuantity;
	private byte paymentStatus;
	
	
	public Integer getParID() {
		return parID;
	}
	public void setParID(Integer parID) {
		this.parID = parID;
	}
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public Integer getGbID() {
		return gbID;
	}
	public void setGbID(Integer gbID) {
		this.gbID = gbID;
	}
	public String getParPhone() {
		return parPhone;
	}
	public void setParPhone(String parPhone) {
		this.parPhone = parPhone;
	}
	public String getPaeName() {
		return paeName;
	}
	public void setPaeName(String paeName) {
		this.paeName = paeName;
	}
	public String getParAddress() {
		return parAddress;
	}
	public void setParAddress(String parAddress) {
		this.parAddress = parAddress;
	}
	public BigDecimal getParLongitude() {
		return parLongitude;
	}
	public void setParLongitude(BigDecimal parLongitude) {
		this.parLongitude = parLongitude;
	}
	public BigDecimal getParLatitude() {
		return parLatitude;
	}
	public void setParLatitude(BigDecimal parLatitude) {
		this.parLatitude = parLatitude;
	}
	public boolean isLeader() {
		return isLeader;
	}
	public void setLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}
	public Integer getParPurchaseQuantity() {
		return parPurchaseQuantity;
	}
	public void setParPurchaseQuantity(Integer parPurchaseQuantity) {
		this.parPurchaseQuantity = parPurchaseQuantity;
	}
	public byte getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(byte paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	
	
}
