package com.qixing.jilu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qixing.util.GJcsh;
import com.qixing.util.GJnowtime;

public class Ljilu implements JKjilu {

	@Override
	public List<BeanJiLu> Sjilu(String userid) {
		List<BeanJiLu> list = new ArrayList<BeanJiLu>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT * FROM recode  where userid=? ORDER BY time DESC ; ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int recodeid = resultSet.getInt(1);
				String userid1 = resultSet.getString(2);
				double mileage = resultSet.getDouble(3);
				String alltime = resultSet.getString(4);
				String time = resultSet.getString(5);
				BeanJiLu beanJiLu = new BeanJiLu(recodeid, userid1, mileage,
						alltime, time);
				list.add(beanJiLu);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public boolean Ijilu(String userid, double mileage, String alltime) {
		boolean boo = false;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "INSERT  INTO recode (userid,mileage,alltime,time) VALUES (?,?,?,?);";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			preparedStatement.setDouble(2,mileage);
			preparedStatement.setString(3, alltime);
			String time=GJnowtime.gettime();
			preparedStatement.setString(4, time);
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
