package com.qixing.shoucang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qixing.util.GJcsh;

public class Lcollect implements JkMyCollect {
	//查看一个帖子是否被该用户收藏
	@Override
	public boolean Scollect(int postId, String userId) {
		boolean boo=false;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		String sql = "SELECT mycollectid FROM mycollect WHERE postid=? AND userid= ?;";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, postId);
			preparedStatement.setString(2, userId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				boo = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return boo;
	}
	// 收藏帖子
	@Override
	public boolean Icollect(int postId, String userId) {
		boolean boo=false;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		//
		String sql = "INSERT  INTO mycollect (postId,userId) VALUES (?,?);";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, postId);
			preparedStatement.setString(2, userId);
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
	// 取消收藏
	@Override
	public boolean Dcollect(int postId,String userId) {
		boolean boo = false;// 默认删除失败
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "DELETE FROM mycollect WHERE postid=? AND userid= ?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, postId);
			preparedStatement.setString(2, userId);
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
	// 查看收藏的帖子
	@Override
	public List<BeanMyCollect> Scollect(String userId) {
        List<BeanMyCollect> list=new ArrayList<BeanMyCollect>();
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		String sql="SELECT postid FROM mycollect WHERE userId=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int postId=resultSet.getInt(1);
				BeanMyCollect beanMyCollect=new BeanMyCollect(postId);
				list.add(beanMyCollect);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			GJcsh.close(connection, preparedStatement, resultSet);
		}	
		return list;
	}
	
}
