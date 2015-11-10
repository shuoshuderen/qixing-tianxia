package com.qixing.zan;

public interface JkZan {
	// 判断是否赞过这个帖子
	public abstract boolean SzanPost(int postId,String userId);
	// 在帖子中增加赞
	public abstract boolean IzanPost(int postId,String userId);
	// 在帖子中减少赞
	public abstract boolean DzanPost(int postId,String userId);
	// 判断是否赞过这个路线
	public abstract boolean SzanWay(int wayid,String userId);
	// 在路线中增加赞
	public abstract boolean IzanWay(int wayid,String userId);
	// 在路线中减少赞
	public abstract boolean Dzanway(int wayid,String userId);
}
