package com.qixing.friend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qixing.util.GJcsh;

public class Lfriend implements JKFriend {
	// 添加好友，新增数据
	@Override
	public boolean Icollect(String userid, String friendid) {
		boolean boo=false;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		
		String sql = "INSERT  INTO userfriend (userid,friendid) VALUES (?,?);";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			preparedStatement.setString(2, friendid);
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
	// 删除好友
	@Override
	public boolean Dcollect(String userid, String friendid) {
		boolean boo = false;// 默认删除失败
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "delete from userfriend where userid=? and friendid =?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			preparedStatement.setString(2, friendid);
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
	//判断
	
	@Override
	public boolean check(String userid, String friendid) {
		boolean boo=false;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		String sql = "SELECT userfriendID FROM userfriend WHERE userid=? AND friendid= ?;";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			preparedStatement.setString(2, friendid);
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
	// 查询所有好友
	@Override
	public List<BeanFriend> Scollect(String userId) {
		 List<BeanFriend> list=new ArrayList<BeanFriend>();
			Connection connection = GJcsh.getConnection();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet=null;
			String sql="SELECT friendid FROM userfriend WHERE userId=?";
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, userId);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					String friendid=resultSet.getString(1);
				BeanFriend beanFriend=new BeanFriend(friendid);
					list.add(beanFriend);
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
