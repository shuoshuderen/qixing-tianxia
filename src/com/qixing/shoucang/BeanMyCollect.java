package com.qixing.shoucang;

import java.io.Serializable;

public class BeanMyCollect implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int myCollectId;
	private int postId;
	private String userId;
	
	
	public BeanMyCollect() {
		super();
	}


	public BeanMyCollect(int postId) {
		super();
		this.postId = postId;
	}


	public BeanMyCollect(int myCollectId, int postId, String userId) {
		super();
		this.myCollectId = myCollectId;
		this.postId = postId;
		this.userId = userId;
	}


	public int getMyCollectId() {
		return myCollectId;
	}


	public void setMyCollectId(int myCollectId) {
		this.myCollectId = myCollectId;
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
		return "MyCollect [myCollectId=" + myCollectId + ", postId=" + postId
				+ ", userId=" + userId + "]";
	}

	
}
