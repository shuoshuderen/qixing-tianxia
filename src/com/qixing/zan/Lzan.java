package com.qixing.zan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qixing.util.GJcsh;

public class Lzan implements JkZan {
	// 判断是否赞过这个帖子
	@Override
	public boolean SzanPost(int postId, String userId) {
		boolean boo=false;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		String sql = "SELECT zanid FROM zan WHERE postId=? AND userid= ?;";

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
	// 在帖子中增加赞
	@Override
	public boolean IzanPost(int postId, String userId) {
		boolean boo=false;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		//
		String sql = "INSERT  INTO zan (postId,userId) VALUES (?,?);";

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
	// 在帖子中减少赞
	@Override
	public boolean DzanPost(int postId, String userId) {
		boolean boo = false;// 默认删除失败
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "DELETE FROM zan WHERE postid=? AND userid= ?";

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
	// 判断是否赞过这个路线
	@Override
	public boolean SzanWay(int wayid, String userId) {
		boolean boo=false;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		String sql = "SELECT zanid FROM zan WHERE wayid=? AND userid= ?;";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, wayid);
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
	
	// 在路线中增加赞
	@Override
	public boolean IzanWay(int wayid, String userId) {
		boolean boo=false;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		//
		String sql = "INSERT  INTO zan (wayid,userId) VALUES (?,?);";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, wayid);
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
	
	// 在路线中减少赞
	@Override
	public boolean Dzanway(int wayid, String userId) {
		boolean boo = false;// 默认删除失败
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "DELETE FROM zan WHERE wayid=? AND userid= ?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, wayid);
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

}
