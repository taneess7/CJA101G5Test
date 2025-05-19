package com.foodtime.favoritepost;

import java.io.Serializable;

public class favoritepostVO implements Serializable{
	private int PostId;
	private int MemId;
	public int getPostId() {
		return PostId;
	}
	public void setPostId(int postId) {
		PostId = postId;
	}
	public int getMemId() {
		return MemId;
	}
	public void setMemId(int memId) {
		MemId = memId;
	}

}
