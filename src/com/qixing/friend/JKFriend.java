package com.qixing.friend;

import java.util.List;


public interface JKFriend {
	// 添加好友，新增数据
		public abstract boolean Icollect(String userid,String friendid);
		// 删除好友
		public abstract boolean Dcollect(String userid,String friendid);
		// 判断
		public abstract boolean check(String userid,String friendid);
		// 查询所有好友
		public abstract List<BeanFriend> Scollect(String userId);
		//点击具体莫个人，进入个人信息页面，方法在登录中
	}
