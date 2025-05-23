package com.foodtimetest.grouppurchasereport.model;

import java.io.Serializable;
import java.sql.Date;

public class GroupPurchaseReportVO implements Serializable{
	
	private Integer reportId;
	private Integer memId;
	private Integer gbId;
	private String reportReason;
	private String reportDetail;
	private byte reportStatus;
	private Date createAt;
	private Date updateAt;
	public Integer getReportId() {
		return reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getGbId() {
		return gbId;
	}
	public void setGbId(Integer gbId) {
		this.gbId = gbId;
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
