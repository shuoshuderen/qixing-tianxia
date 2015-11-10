package com.qixing.qiuzhu;

import java.util.List;


public interface JKhelp {
	   //发布求助
	public abstract boolean Iseekhep(String userId, String name,String helpContent,
			String helpAddress,double helpLongitude, double helpLatitude);

	// 查看求助
	public abstract BeanHelp sHelp(int seekhelpid);
	//根据城市搜索求助
	public abstract List<BeanHelp> getHelps(String helpaddress);
}
