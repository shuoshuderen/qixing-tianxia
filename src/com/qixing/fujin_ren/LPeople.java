package com.qixing.fujin_ren;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.qixing.util.GJcsh;

public class LPeople implements JKPeople {

	@Override
	public List<People> getPeople(String userid) {
		// TODO Auto-generated method stub
		List<People> list = new ArrayList<People>();
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT userid,username,gender,age,userimage,totaldistance,totaltime,latitude,longitude "
				+ "FROM user WHERE latitude!= 0 AND longitude != 0 AND userid != ? ;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				//从数据库读取每个用户的名称和经纬度
				String userid1=resultSet.getString(1);
				String username = resultSet.getString(2);
				String gender = resultSet.getString(3);
				int age = resultSet.getInt(4);
				String userimg = resultSet.getString(5);
				double totaldistance = resultSet.getDouble(6);
				String totaltime = resultSet.getString(7);
				double lan = resultSet.getDouble(8);
				double lon = resultSet.getDouble(9);
				People people=new People(userid1, username, gender, age, userimg, totaldistance, totaltime, lan, lon);
				System.out.println(people);
				list.add(people);
		}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		
		return list;
	}

}
