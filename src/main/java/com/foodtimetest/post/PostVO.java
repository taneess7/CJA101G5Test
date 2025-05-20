package com.foodtimetest.post;

import java.io.Serializable;
import java.sql.Timestamp;

public class PostVO implements Serializable{
	private Integer postId;
	private Integer memId;
	private Timestamp postDate;
	private Boolean postStatus;
	private Timestamp editdate;
	private String postTitle;
	private String postContent;
	private Integer likeCount;
	private Integer views;
	
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
	public Timestamp getPostDate() {
		return postDate;
	}
	public void setPostDate(Timestamp postDate) {
		this.postDate = postDate;
	}
	public Boolean getPostStatus() {
		return postStatus;
	}
	public void setPostStatus(Boolean postStatus) {
		this.postStatus = postStatus;
	}
	public Timestamp getEditdate() {
		return editdate;
	}
	public void setEditdate(Timestamp editdate) {
		this.editdate = editdate;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public Integer getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	public Integer getViews() {
		return views;
	}
	public void setViews(Integer views) {
		this.views = views;
	}
	
	
}
	
	