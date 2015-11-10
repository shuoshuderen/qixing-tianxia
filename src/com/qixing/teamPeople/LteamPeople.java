package com.qixing.teamPeople;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qixing.util.GJcsh;

public class LteamPeople implements JKteamPeople {
	// 添加队员
	@Override
	public boolean IteamPeople(int teamid, String userid) {
		boolean boo = false;// 默认插入失败
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO teampeople (teamid,userid) VALUES(?,?);";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teamid);
			preparedStatement.setString(2, userid);
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

	// 删除队员
	@Override
	public boolean DteamPeople(int teamid, String userid) {
		boolean boo = false;// 默认删除失败
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "delete from teampeople where teamid=? AND userid=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teamid);
			preparedStatement.setString(2, userid);
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

	// 查询某车队员个数
	@Override
	public int Snumber(int teamid) {
		int num = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = GJcsh.getConnection();
		String sql = "SELECT COUNT(*)FROM teampeople WHERE teamid=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teamid);
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

	// 查询车队队员id
	@Override
	public List<BeanTeamPeople> SteamPeople(int teamid) {
		List<BeanTeamPeople> list = new ArrayList<BeanTeamPeople>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT userid FROM teampeople WHERE teamid = ?;";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teamid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String userid = resultSet.getString(1);
				BeanTeamPeople beanTeamPeople = new BeanTeamPeople(userid);
				list.add(beanTeamPeople);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	// 参加的车队，查询userid对应的teamid
	@Override
	public List<BeanTeamPeople> Saddteam(String userid) {
		List<BeanTeamPeople> list = new ArrayList<BeanTeamPeople>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT teamid FROM teampeople WHERE userid = ?;";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int teamid = resultSet.getInt(1);
				BeanTeamPeople beanTeamPeople = new BeanTeamPeople(teamid);
				list.add(beanTeamPeople);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
//判断对队员是否存在
	@Override
	public boolean check(int teamid, String userid) {
		boolean boo=false;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		String sql = "SELECT teampeopleid FROM teampeople WHERE teamid=? AND userid= ?;";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teamid);
			preparedStatement.setString(2, userid);
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

}
