package com.qixing.pinglun;

import java.util.List;

public interface JKcomments {
	// 发表评论
	public abstract boolean Icomments(int postid,String userid,String time,String content);
	// 删除评论
	public abstract boolean Dcomments(int commentsid);
	// 查看某一帖子的所有评论
	public abstract List<BeanComments> Scomments(int postid);
    //查看某一帖子的所有评论的个数
	public abstract int Splnumber(int postid);
	
	
	
}
