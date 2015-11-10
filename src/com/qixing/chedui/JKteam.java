package com.qixing.chedui;

import java.util.List;

public interface JKteam {
	// 创建车队
	public abstract boolean Iteam(String userid, String teamname,
			String teamimage, String teamaddress,
			  String teamslogan,
			String teamintroduce, String teaminform,String teamtime);

	// 删除车队
	public abstract boolean Dteam(int teamid);

	// 根据页数查询车队，一次显示n个
	public abstract List<BeanTeam> SteamByNum(int pageNow);

	// 根据名字搜索车队
	public abstract List<BeanTeam> SteamByName(String teamname);

	// 查询某一条具体车队
	public abstract BeanTeam SteamById(int teamid);

	// 修改车队信息
	public abstract boolean Uteam(int teamid, String teamname,
			 String teamslogan, String teamintroduce,
			String teaminform);
	//修改车队头像
	public abstract boolean Uimage(int teamid,String teamimage);
	// 车队总人数，车队表中队员增加加，减少减；//修改车队人数
	//在加减人数之后自动调用
	public abstract boolean UpeoNumber(int teamid);
	// 骑行时间，
	//查询前十名的车队，根据里程
	public abstract List<BeanTeam> SteamByten();
	
	// 根据时间搜索车队
	public abstract int SteamByTime(String teamtime);
	//车队人数加
	public abstract boolean Addteambumber(int teamid);
	//车队人数减
	public abstract boolean Subteambumber(int teamid);
}
