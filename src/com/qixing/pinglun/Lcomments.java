package com.qixing.pinglun;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qixing.user.Luser;
import com.qixing.util.GJcsh;

public class Lcomments implements JKcomments {
	// 发表评论
	@Override
	public boolean Icomments(int postid, String userid,String time,String content) {
		boolean boo = false;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "INSERT  INTO comments (postid,userid,time,content) VALUES (?,?,?,?);";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, postid);
			preparedStatement.setString(2, userid);
			preparedStatement.setString(3, time);
			preparedStatement.setString(4, content);
			preparedStatement.execute();
			boo = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, null);
		}
		return boo;
	}

	// 删除评论
	@Override
	public boolean Dcomments(int commentsid) {
		boolean boo = false;// 默认删除失败
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "delete from comments where commentsid=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, commentsid);
			preparedStatement.execute();
			boo = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, null);
		}
		return boo;
	}

	// 查看某一帖子的所有评论
	@Override
	public List<BeanComments> Scomments(int postid) {
		List<BeanComments> list = new ArrayList<BeanComments>();
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM comments WHERE postid=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, postid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int commentsid = resultSet.getInt(1);
				int postid2 = resultSet.getInt(2);
				String userid = resultSet.getString(3);
				Luser luser=new Luser();
			    String touxiang	=luser.Simage(userid);
			    String username= luser.SuserName(userid);
				String tIME = resultSet.getString(4);
				String content = resultSet.getString(5);
				BeanComments beanComments = new BeanComments(commentsid,
						postid2, userid, tIME, content,touxiang,username);
				list.add(beanComments);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return list;
	}
	//查看某一帖子的所有评论的个数
	@Override
	public int Splnumber(int postid) {
		int num = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = GJcsh.getConnection();
		String sql = "SELECT COUNT(*)FROM comments WHERE postid=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, postid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				num = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return num;
	}

}
