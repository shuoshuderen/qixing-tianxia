package com.qixing.newmen;

import java.util.List;


public interface JKfreshonroot {
	// 根据页数查询新手推荐，一次n条
	public abstract List<BeanFreshonroot> SfreshByNum();
	// 根据名字搜索新手推荐
	public abstract List<BeanFreshonroot> SfreshByName(String freshtitle);
	// 查询某一条具体新手推荐
	public abstract BeanFreshonroot SfreshById(int freshonrootid);
	// 查询新手推荐中的图片
	public abstract List<String> SfreshPhoto(int freshonrootid);
	//赞增加
	public abstract boolean Addzan(int freshonrootid);
	
}
