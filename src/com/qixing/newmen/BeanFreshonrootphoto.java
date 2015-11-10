package com.qixing.newmen;

import java.io.Serializable;

public class BeanFreshonrootphoto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int freshonrootphotoid;
	private int freshonrootid;
	private String photo;
	
	public BeanFreshonrootphoto() {
		super();
	}

	public BeanFreshonrootphoto(String photo) {
		super();
		this.photo = photo;
	}

	public BeanFreshonrootphoto(int freshonrootphotoid, int freshonrootid,
			String photo) {
		super();
		this.freshonrootphotoid = freshonrootphotoid;
		this.freshonrootid = freshonrootid;
		this.photo = photo;
	}

	public int getFreshonrootphotoid() {
		return freshonrootphotoid;
	}

	public void setFreshonrootphotoid(int freshonrootphotoid) {
		this.freshonrootphotoid = freshonrootphotoid;
	}

	public int getFreshonrootid() {
		return freshonrootid;
	}

	public void setFreshonrootid(int freshonrootid) {
		this.freshonrootid = freshonrootid;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "BeanFreshonrootphoto [freshonrootphotoid=" + freshonrootphotoid
				+ ", freshonrootid=" + freshonrootid + ", photo=" + photo + "]";
	}

}
