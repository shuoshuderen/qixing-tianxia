package com.qixing.jilu;

import java.util.List;

public interface JKjilu {
	//查询所有记录
	public abstract List<BeanJiLu> Sjilu(String userid);
	//插入新的记录
	public abstract boolean Ijilu(String userid,double mileage,String alltime);
}
