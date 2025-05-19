package com.foodtime.reportpost;

import java.io.Serializable;
import java.sql.Date;

public class reportpostVO implements Serializable{
	private int RepPostId;
	private int PostId;
	private int MemId;
	private Date RepPostDate;
	private char RepPostReason;
	private byte RepPostStatus;
	
	public int getRepPostId() {
		return RepPostId;
	}
	public void setRepPostId(int repPostId) {
		RepPostId = repPostId;
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
	public Date getRepPostDate() {
		return RepPostDate;
	}
	public void setRepPostDate(Date repPostDate) {
		RepPostDate = repPostDate;
	}
	public char getRepPostReason() {
		return RepPostReason;
	}
	public void setRepPostReason(char repPostReason) {
		RepPostReason = repPostReason;
	}
	public byte getRepPostStatus() {
		return RepPostStatus;
	}
	public void setRepPostStatus(byte repPostStatus) {
		RepPostStatus = repPostStatus;
	}

}
