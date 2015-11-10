package com.qixing.friend;

import java.io.Serializable;

public class BeanFriend implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userfriendid;//好友表的id
	private String userid;//用户id
	private String friendid;// 好友的id

	public BeanFriend() {
		super();
	}


	public BeanFriend(String friendid) {
		super();
		this.friendid = friendid;
	}


	public BeanFriend(int userfriendid, String userid, String friendid) {
		super();
		this.userfriendid = userfriendid;
		this.userid = userid;
		this.friendid = friendid;
	}

	public int getUserfriendid() {
		return userfriendid;
	}

	public void setUserfriendid(int userfriendid) {
		this.userfriendid = userfriendid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFriendid() {
		return friendid;
	}

	public void setFriendid(String friendid) {
		this.friendid = friendid;
	}

	@Override
	public String toString() {
		return "BeanFriend [userfriendid=" + userfriendid + ", userid="
				+ userid + ", friendid=" + friendid + "]";
	}

}
