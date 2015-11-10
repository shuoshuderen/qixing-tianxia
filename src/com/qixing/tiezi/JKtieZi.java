package com.qixing.tiezi;

import java.util.List;

public interface JKtieZi {
	// 删除帖子
	public abstract boolean delete(int postid);
	// 插入帖子，帖子id为自增，不用插入,需插入其他几项
	public abstract boolean Inewtz(String userid, String postname,
			String postaddress, String posttime, String postclass);

	// 插入图片，一个帖子中可有多张图片? 帖子id为自增，如何找到帖子id
	public abstract boolean insertphoto(int postid, String photo);

	// 修改帖子,根据帖子id修改帖子名字和内容
	public abstract boolean UtieZi(int postid, String postname,
			String postaddress);

	// 根据页数查询,发帖人id已换为发帖人头像
	public abstract List<BeanPost> SpostByNum(int pageNow);

	// 根据帖子名搜索模糊查询,可以有多条数据
	public abstract List<BeanPost> SpostByName(String postname);

	// 根据id找到帖子，只有一条，用bean对象，查看具体的帖子和修改时用
	public abstract BeanPost SpostById(int postid);

	// 在图片表中获取图片,可以有多张，用集合
	public abstract List<BeanPostPhoto> SpostPhoto(int postid);

	// 赞增加
	public abstract boolean Addzan(int postid);
//修改帖子中评论的个数，在评论改变之后直接调用该方法改变
	public abstract boolean UplNumber(int postid);
	
	public abstract boolean UplNumbersub(int postid);
	//根据用户名查看帖子
	public abstract List<BeanPost> SpostByuserid(String userid,int pageNow);
	//根据发帖时间查询帖子id
	public abstract int SpostidBytime(String time);
	//赞减少
	public abstract boolean Subzan(int postid);
	//查询赞的数量
	public abstract int SzanNum(int postid);
}
