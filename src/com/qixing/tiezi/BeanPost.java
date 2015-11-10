package com.qixing.tiezi;

import java.io.Serializable;
import java.util.List;

public class BeanPost implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int postid;
	private String userid;
	private String postname;
	private String postaddress;
	private String posttime;
	private int postpraise;
	private String postclass;
	private int plSum;
	private String userimg;
	private List<BeanPostPhoto>  list;
	
	public BeanPost() {
		super();
	}

	public BeanPost(int postid, String userid, String postname,
			String postaddress, String posttime, int postpraise,
			String postclass, int plSum, String userimg,
			List<BeanPostPhoto> list) {
		super();
		this.postid = postid;
		this.userid = userid;
		this.postname = postname;
		this.postaddress = postaddress;
		this.posttime = posttime;
		this.postpraise = postpraise;
		this.postclass = postclass;
		this.plSum = plSum;
		this.userimg = userimg;
		this.list = list;
	}


	public int getPostid() {
		return postid;
	}

	public void setPostid(int postid) {
		this.postid = postid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPostname() {
		return postname;
	}

	public void setPostname(String postname) {
		this.postname = postname;
	}

	public String getPostaddress() {
		return postaddress;
	}

	public void setPostaddress(String postaddress) {
		this.postaddress = postaddress;
	}

	public String getPosttime() {
		return posttime;
	}

	public void setPosttime(String posttime) {
		this.posttime = posttime;
	}

	public int getPostpraise() {
		return postpraise;
	}

	public void setPostpraise(int postpraise) {
		this.postpraise = postpraise;
	}

	public String getPostclass() {
		return postclass;
	}

	public void setPostclass(String postclass) {
		this.postclass = postclass;
	}

	public int getPlSum() {
		return plSum;
	}

	public void setPlSum(int plSum) {
		this.plSum = plSum;
	}

	public String getUserimg() {
		return userimg;
	}

	public void setUserimg(String userimg) {
		this.userimg = userimg;
	}

	public List<BeanPostPhoto> getList() {
		return list;
	}

	public void setList(List<BeanPostPhoto> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "BeanPost [postid=" + postid + ", userid=" + userid
				+ ", postname=" + postname + ", postaddress=" + postaddress
				+ ", posttime=" + posttime + ", postpraise=" + postpraise
				+ ", postclass=" + postclass + ", plSum=" + plSum + ", list="
				+ list + "]";
	}


	




	



	
}
