package com.foodtimetest.grouporders;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class GroupOrderVO implements Serializable{
	
	private Integer gbOrID;
	private Integer gbID;
	private Integer storID;
	private Integer gbProdID;
	private Date joinTime;
	private Integer amount;
	private Integer quantity;
	private byte payMethod;
	private byte orderStatus;
	private byte paymentStatus;
	private byte shippingStatus;
	private String parName;
	private String parAddress;
	private BigDecimal parLongitude;
	private BigDecimal parLatitude;
	private String parPhone;
	private byte deliveryMethod;
	private String comment;
	private Integer rating;
	
	public Integer getGbOrID() {
		return gbOrID;
	}
	public void setGbOrID(Integer gbOrID) {
		this.gbOrID = gbOrID;
	}
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
	public Date getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public byte getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(byte payMethod) {
		this.payMethod = payMethod;
	}
	public byte getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(byte orderStatus) {
		this.orderStatus = orderStatus;
	}
	public byte getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(byte paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public byte getShippingStatus() {
		return shippingStatus;
	}
	public void setShippingStatus(byte shippingStatus) {
		this.shippingStatus = shippingStatus;
	}
	public String getParName() {
		return parName;
	}
	public void setParName(String parName) {
		this.parName = parName;
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
	public String getParPhone() {
		return parPhone;
	}
	public void setParPhone(String parPhone) {
		this.parPhone = parPhone;
	}
	public byte getDeliveryMethod() {
		return deliveryMethod;
	}
	public void setDeliveryMethod(byte deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	

}
