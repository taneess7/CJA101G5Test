package com.foodtimetest.postcategory;

import java.io.Serializable;

public class postcategoryVO implements Serializable{
	private Integer postCateId;
	private String postCate;
	
	public Integer getPostCateId() {
		return postCateId;
	}
	public void setPostCateId(Integer postCateId) {
		this.postCateId = postCateId;
	}
	public String getPostCate() {
		return postCate;
	}
	public void setPostCate(String postCate) {
		this.postCate = postCate;
	}
	

}
