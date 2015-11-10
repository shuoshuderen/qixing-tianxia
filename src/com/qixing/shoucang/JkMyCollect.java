 package com.qixing.shoucang;

import java.util.List;

public interface JkMyCollect {
	//查看一个帖子是否被该用户收藏
	public abstract boolean Scollect(int postId,String userId);
	// 收藏帖子,相当于在表中新建了一个数据
	public abstract boolean Icollect(int postId,String userId);
	// 取消收藏，在表中吧数据删除
	public abstract boolean Dcollect(int postId,String userId);
	// 查看收藏的帖子，所有收藏的帖子都显示
	public abstract List<BeanMyCollect> Scollect(String userId);
	// 点击具体的帖子进行查看，是帖子表中的内容，使用其方法
}
