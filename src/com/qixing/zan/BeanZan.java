package com.qixing.zan;

import java.io.Serializable;

public class BeanZan implements Serializable {

	private static final long serialVersionUID = 1L;
	private int wayid;
	private int postId;
	private String userId;
	
	public BeanZan(int postId) {
		super();
		this.postId = postId;
	}
	
	public BeanZan(int wayid, int postId, String userId) {
		super();
		this.wayid = wayid;
		this.postId = postId;
		this.userId = userId;
	}

	public int getWayid() {
		return wayid;
	}

	public void setWayid(int wayid) {
		this.wayid = wayid;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "BeanZan [wayid=" + wayid + ", postId=" + postId + ", userId="
				+ userId + "]";
	}
	
	
	
}
