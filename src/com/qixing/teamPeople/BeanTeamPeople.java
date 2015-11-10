package com.qixing.teamPeople;

import java.io.Serializable;

public class BeanTeamPeople implements Serializable{

	private static final long serialVersionUID = 1L;
	private int teampeopleid;
	private int teamid;//车队id
	private String userid;
	
	
	public BeanTeamPeople() {
		super();
	}


	public BeanTeamPeople(String userid) {
		super();
		this.userid = userid;
	}


	public BeanTeamPeople(int teamid) {
		super();
		this.teamid = teamid;
	}


	public BeanTeamPeople(int teampeopleid, int teamid, String userid) {
		super();
		this.teampeopleid = teampeopleid;
		this.teamid = teamid;
		this.userid = userid;
	}


	public int getteampeopleid() {
		return teampeopleid;
	}


	public void setteampeopleid(int teampeopleid) {
		this.teampeopleid = teampeopleid;
	}


	public int getTeamid() {
		return teamid;
	}


	public void setTeamid(int teamid) {
		this.teamid = teamid;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	@Override
	public String toString() {
		return "BeanTeamPeople [teampeopleid=" + teampeopleid + ", teamid=" + teamid
				+ ", userid=" + userid + "]";
	}
	
	
}
