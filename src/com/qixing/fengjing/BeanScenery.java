package com.qixing.fengjing;

import java.io.Serializable;
import java.util.List;

public class BeanScenery implements Serializable {

	private static final long serialVersionUID = 1L;
	private int sceneryid;
	private String sceneryname;
	List<BeanSceneryPhoto> list;
	
	public BeanScenery() {
		super();
	}


	public BeanScenery(int sceneryid, String sceneryname) {
		super();
		this.sceneryid = sceneryid;
		this.sceneryname = sceneryname;
	}


	public BeanScenery(int sceneryid, String sceneryname,
			List<BeanSceneryPhoto> list) {
		super();
		this.sceneryid = sceneryid;
		this.sceneryname = sceneryname;
		this.list = list;
	}


	public int getSceneryid() {
		return sceneryid;
	}


	public void setSceneryid(int sceneryid) {
		this.sceneryid = sceneryid;
	}


	public String getSceneryname() {
		return sceneryname;
	}


	public void setSceneryname(String sceneryname) {
		this.sceneryname = sceneryname;
	}


	@Override
	public String toString() {
		return "BeanScenery [sceneryid=" + sceneryid + ", sceneryname="
				+ sceneryname + "]";
	}
	
}
