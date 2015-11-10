package com.qixing.teamPeople;

import java.util.List;

public interface JKteamPeople {
	// 添加队员
	public abstract boolean IteamPeople(int teamid, String userid);

	// 删除队员
	public abstract boolean DteamPeople(int teamid, String userid);

	// 查询某车队员个数
	public abstract int Snumber(int teamid);

	// 查询车队队员id
	public abstract List<BeanTeamPeople> SteamPeople(int teamid);

	// 我的车队，一个是创建的，另一个是我参加的。
	// 我创建的车队，查询车队表中创建人id。
	
	// 参加的车队，查询userid对应的teamid
	public abstract List<BeanTeamPeople> Saddteam(String userid); 
	
	// 判断队员是否加入车队
	public abstract boolean check(int teamid, String userid);
	
	
	
}
