package com.qixing.zhuangbei;

import java.util.List;

public interface JKequdetail {
	//装备分为四类，点击进入后查看具体某一类，然后分页显示，一页n条
	
	public abstract List<BeanEqudetail> SequdetailByNum(String equdetailClass,int pageNow);

	// 根据名字搜索装备
	public abstract List<BeanEqudetail> SequdetailByName(String equdetailName);

	// 查询某一条具体装备
	public abstract BeanEqudetail SequdetailById(int equdetailId);

	// 查询装备中的图片
	public abstract List<Beanequdetailphoto> Sequdetailphoto(int equdetailId);
}
