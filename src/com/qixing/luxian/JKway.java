package com.qixing.luxian;

import java.util.List;

public interface JKway {
	// 根据页数查询路线，一次n条
	public abstract List<BeanWay> SwayByNum(int pageNow);

	// 查看前十条
	public abstract List<BeanWay> StopWays();
	// 根据名字搜索路线
	public abstract List<BeanWay> SwayByName(String wayName);

	// 查询某一条具体路线
	public abstract BeanWay SwayById(int wayid);

	// 查询路线中的图片
	public abstract List<BeanWayImage> SwayImage(int wayid);
//根据id，查看赞的数量
	public abstract int SzanNum(int wayid);
	// 赞增加
	public abstract boolean Addzan(int wayid);
	//赞减少
	public abstract boolean Subzan(int wayid);
}
