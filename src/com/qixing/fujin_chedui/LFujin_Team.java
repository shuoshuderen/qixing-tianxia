package com.qixing.fujin_chedui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qixing.chedui.BeanTeam;
import com.qixing.user.Luser;
import com.qixing.util.GJcsh;

public class LFujin_Team implements JKFujin_team {

	@Override
	public List<BeanTeam> getTeam(String city) {
		List<BeanTeam> list=new ArrayList<BeanTeam>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT *FROM team WHERE teamaddress like ?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,"%"+city+"%");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int teamid = resultSet.getInt(1);
				String userid = resultSet.getString(2);
				String teamname = resultSet.getString(3);
				String teamimage = resultSet.getString(4);
				String teamtime = resultSet.getString(5);
				String teamaddress = resultSet.getString(6);
				double teamdistance = resultSet.getDouble(7);
				int teamnumber = resultSet.getInt(8);
				String teamslogan = resultSet.getString(9);
				String teamintroduce = resultSet.getString(10);
				String teaminform = resultSet.getString(11);
				Luser luser=new Luser();
                String username= luser.SuserName(userid);
				BeanTeam beamTeam = new BeanTeam(teamid, userid, teamname,
						teamimage, teamtime, teamaddress, teamdistance,
						teamnumber, teamslogan, teamintroduce, teaminform,username);
				list.add(beamTeam);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

}
