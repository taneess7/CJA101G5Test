package com.foodtimetest.memfavlist;

import java.io.Serializable;

public class FavoriteListVO implements Serializable{

	private Integer memId;
	private Integer prodId;
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

}
