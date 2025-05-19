package com.foodtime.postcategory;

import java.io.Serializable;

public class postcategoryVO implements Serializable{
	private int PostCateId;
	public int getPostCateId() {
		return PostCateId;
	}
	public void setPostCateId(int postCateId) {
		PostCateId = postCateId;
	}
	public int getPostCate() {
		return PostCate;
	}
	public void setPostCate(int postCate) {
		PostCate = postCate;
	}
	private int PostCate;

}
