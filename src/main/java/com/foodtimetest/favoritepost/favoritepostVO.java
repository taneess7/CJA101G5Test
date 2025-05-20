package com.foodtimetest.favoritepost;

import java.io.Serializable;

public class favoritepostVO implements Serializable{
	private Integer postId;
	private Integer memId;
	
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}

}
