package com.foodtimetest.post;

import java.io.Serializable;
import java.util.Date;

public class PostVO implements Serializable{
	private int PostId;
	private int MemId;
	private Date PostDate;
	
	public Date getPostDate() {
		return PostDate;
	}
	public void setPostDate(Date postDate) {
		PostDate = postDate;
	}
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
	
	