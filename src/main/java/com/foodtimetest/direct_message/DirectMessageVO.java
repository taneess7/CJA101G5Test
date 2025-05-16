package com.foodtimetest.direct_message;

import java.sql.Date;

public class DirectMessageVO {
	private Integer dmID;
	private Integer memID;
	private Integer smgrID;
	private String messContent;
	private Date messTime;
	private short messDirection;
	public Integer getDmID() {
		return dmID;
	}
	public void setDmID(Integer dmID) {
		this.dmID = dmID;
	}
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public Integer getSmgrID() {
		return smgrID;
	}
	public void setSmgrID(Integer smgrID) {
		this.smgrID = smgrID;
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
	public void setMessDirection(short messDirection) {
		this.messDirection = messDirection;
	}
	
}
