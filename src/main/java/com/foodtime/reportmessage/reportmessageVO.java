package com.foodtime.reportmessage;

import java.io.Serializable;
import java.sql.Date;

public class reportmessageVO implements Serializable{
	private int RepMesId;
	private int MesId;
	private int MemId;
	private Date RepMesDate;
	private char RepMesReason;
	private byte RepMesStatus;
	
	public int getRepMesId() {
		return RepMesId;
	}
	public void setRepMesId(int repMesId) {
		RepMesId = repMesId;
	}
	public int getMesId() {
		return MesId;
	}
	public void setMesId(int mesId) {
		MesId = mesId;
	}
	public int getMemId() {
		return MemId;
	}
	public void setMemId(int memId) {
		MemId = memId;
	}
	public Date getRepMesDate() {
		return RepMesDate;
	}
	public void setRepMesDate(Date repMesDate) {
		RepMesDate = repMesDate;
	}
	public char getRepMesReason() {
		return RepMesReason;
	}
	public void setRepMesReason(char repMesReason) {
		RepMesReason = repMesReason;
	}
	public byte getRepMesStatus() {
		return RepMesStatus;
	}
	public void setRepMesStatus(byte repMesStatus) {
		RepMesStatus = repMesStatus;
	}

}
