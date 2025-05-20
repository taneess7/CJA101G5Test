package com.foodtimetest.reportpost;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class reportpostVO implements Serializable{
	private Integer repPostId;
	private Integer postId;
	private Integer memId;
	private Timestamp repPostDate;
	private String repPostReason;
	private Boolean repPostStatus;
	
	public Integer getRepPostId() {
		return repPostId;
	}
	public void setRepPostId(Integer repPostId) {
		this.repPostId = repPostId;
	}
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
	public Timestamp getRepPostDate() {
		return repPostDate;
	}
	public void setRepPostDate(Timestamp repPostDate) {
		this.repPostDate = repPostDate;
	}
	public String getRepPostReason() {
		return repPostReason;
	}
	public void setRepPostReason(String repPostReason) {
		this.repPostReason = repPostReason;
	}
	public Boolean getRepPostStatus() {
		return repPostStatus;
	}
	public void setRepPostStatus(Boolean repPostStatus) {
		this.repPostStatus = repPostStatus;
	}

}
