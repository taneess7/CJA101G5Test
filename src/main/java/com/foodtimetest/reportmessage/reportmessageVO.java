package com.foodtimetest.reportmessage;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class reportmessageVO implements Serializable{
	private Integer repMesId;
	private Integer mesId;
	private Integer memId;
	private Timestamp repMesDate;
	private String repMesReason;
	private Boolean repMesStatus;
	
	public Integer getRepMesId() {
		return repMesId;
	}
	public void setRepMesId(Integer repMesId) {
		this.repMesId = repMesId;
	}
	public Integer getMesId() {
		return mesId;
	}
	public void setMesId(Integer mesId) {
		this.mesId = mesId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Timestamp getRepMesDate() {
		return repMesDate;
	}
	public void setRepMesDate(Timestamp repMesDate) {
		this.repMesDate = repMesDate;
	}
	public String getRepMesReason() {
		return repMesReason;
	}
	public void setRepMesReason(String repMesReason) {
		this.repMesReason = repMesReason;
	}
	public Boolean getRepMesStatus() {
		return repMesStatus;
	}
	public void setRepMesStatus(Boolean repMesStatus) {
		this.repMesStatus = repMesStatus;
	}

}
