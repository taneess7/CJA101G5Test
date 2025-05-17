package com.foodtimetest.message;

import java.io.Serializable;

public class MessageVO implements Serializable{
	private Integer MesId;
	private String MesContent;
	
	public Integer getMesId() {
		return MesId;
	}
	public void setMesId(Integer mesId) {
		MesId = mesId;
	}
	public String getMesContent() {
		return MesContent;
	}
	public void setMesContent(String mesContent) {
		MesContent = mesContent;
	}

}
