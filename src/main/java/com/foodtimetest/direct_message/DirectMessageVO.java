package com.foodtimetest.direct_message;

import java.io.Serializable;
import java.sql.Date;

public class DirectMessageVO implements Serializable{
	private Integer dmId;
	private Integer memId;
	private Integer smgrId;
	private String messContent;
	private Date messTime;
	private Byte messDirection;
	public Integer getDmId() {
		return dmId;
	}
	public void setDmId(Integer dmId) {
		this.dmId = dmId;
	}
	public Integer getMemID() {
		return memId;
	}
	public void setMemID(Integer memId) {
		this.memId = memId;
	}
	public Integer getSmgrID() {
		return smgrId;
	}
	public void setSmgrID(Integer smgrId) {
		this.smgrId = smgrId;
	}
	public String getMessContent() {
		return messContent;
	}
	public void setMessContent(String messContent) {
		this.messContent = messContent;
	}
	public Date getMessTime() {
		return messTime;
	}
	public void setMessTime(Date messTime) {
		this.messTime = messTime;
	}
	public short getMessDirection() {
		return messDirection;
	}
	public void setMessDirection(Byte messDirection) {
		this.messDirection = messDirection;
	}
	
}
