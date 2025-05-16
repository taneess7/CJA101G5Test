package com.foodtimetest.groupbuyingcollectionlist;

import java.io.Serializable;
import java.sql.Date;

public class GroupBuyingCollectionListVO implements Serializable{
	
	private Integer gbID;
	private Integer memID;
	private Date createAt;
	public Integer getGbID() {
		return gbID;
	}
	public void setGbID(Integer gbID) {
		this.gbID = gbID;
	}
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	
}
