package com.qixing.user;

import java.util.List;

public interface Jkuser {
	//判断登录是否成功
	public abstract String CheckDL(String userid,String password);
	// 查询用户名是否存在
	public abstract boolean CheckZC(String username);
	//判断用户插入数据库是否成功
	public abstract boolean CheckZcInsert(String username,String password);
	//修改用户信息 用户名，性别，年龄，地址，
	public abstract boolean Uxinxi(String userid, String username, String gender, int age,
			String address);
	//修改密码
	public abstract boolean Umima(String userid,String password);
	//修改骑行时间和路程
	public abstract boolean Utime(String userid,double totaldistance,String totaltime);
	//修改经纬度
	public abstract boolean Uitude(String userid,double latiudt,double longitude);
	//头像初始值为空，所以都是修改头像
	public abstract boolean Uimage(String userid,String userimg);
	//查看用户的具体信息
	public abstract List<BeanUser> Suser(String userid);
	//查看用户的具体信息,根据用户名
	public abstract List<BeanUser> SuserByName(String username);
	//根据用户id，获取用户头像
	public abstract String Simage(String userid);
	//根据用户id，获取用户名字
	public abstract String SuserName(String userid);
}
