package com.foodtimetest.grouppurchasereport;

import java.io.Serializable;
import java.sql.Date;

public class GroupPurchaseReportVO implements Serializable{
	
	private Integer reportID;
	private Integer memID;
	private Integer gbID;
	private String reportReason;
	private String reportDetail;
	private byte reportStatus;
	private Date createAt;
	private Date updateAt;
	public Integer getReportID() {
		return reportID;
	}
	public void setReportID(Integer reportID) {
		this.reportID = reportID;
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
	public String getReportReason() {
		return reportReason;
	}
	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}
	public String getReportDetail() {
		return reportDetail;
	}
	public void setReportDetail(String reportDetail) {
		this.reportDetail = reportDetail;
	}
	public byte getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(byte reportStatus) {
		this.reportStatus = reportStatus;
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
	
	

}
