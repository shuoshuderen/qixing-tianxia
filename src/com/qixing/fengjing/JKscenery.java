package com.qixing.fengjing;

import java.util.List;

public interface JKscenery {
	// 查询前n条,包括图片
	public abstract List<BeanScenery> SscenceryByNum(int pageNow);
	//根据风景id查询具体图片
	public abstract List<BeanSceneryPhoto> SscenceryImage(int sceneryid);
}
