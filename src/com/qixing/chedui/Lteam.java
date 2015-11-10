package com.qixing.chedui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qixing.teamPeople.LteamPeople;
import com.qixing.user.Luser;
import com.qixing.util.GJNum;
import com.qixing.util.GJcsh;

public class Lteam implements JKteam {
	// 插入车队
	@Override
	public boolean Iteam(String userid, String teamname, String teamimage,
			 String teamaddress, 
			 String teamslogan, String teamintroduce,
			String teaminform,String teamtime) {
		boolean boo = false;// 默认插入失败
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO team ( userid, teamname, teamimage, teamtime, teamaddress, teamdistance, "
				+ "teamnumber, teamslogan, teamintroduce, teaminform) VALUES(?,?,?,?,?,?,?,?,?,?);";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			preparedStatement.setString(2, teamname);
			preparedStatement.setString(3, teamimage);
			
			preparedStatement.setString(4, teamtime);
			preparedStatement.setString(5, teamaddress);
			double teamdistance=0;
			preparedStatement.setDouble(6, teamdistance);
			int teamnumber=1;
			preparedStatement.setInt(7, teamnumber);
			preparedStatement.setString(8, teamslogan);
			preparedStatement.setString(9, teamintroduce);
			preparedStatement.setString(10, teaminform);
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

	// 删除车队
	@Override
	public boolean Dteam(int teamid) {
		boolean boo = false;// 默认删除失败
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "delete from team where teamid=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teamid);
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

	// 根据页数查询车队，一次n条
	@Override
	public List<BeanTeam> SteamByNum(int pageNow) {
		List<BeanTeam> list = new ArrayList<BeanTeam>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT * FROM team  LIMIT ?,? ;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			int m = (pageNow - 1) * GJNum.pageNum;
			int n = GJNum.pageNum;
			preparedStatement.setInt(1, m);
			preparedStatement.setInt(2, n);
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
                String name=luser.SuserName(userid);
				BeanTeam beamTeam = new BeanTeam(teamid, userid, teamname,
						teamimage, teamtime, teamaddress, teamdistance,
						teamnumber, teamslogan, teamintroduce, teaminform,name);
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

	// 根据名字搜车队
	@Override
	public List<BeanTeam> SteamByName(String teamname) {
		List<BeanTeam> list = new ArrayList<BeanTeam>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT *FROM team WHERE teamname like ?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + teamname + "%");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int teamid = resultSet.getInt(1);
				String userid = resultSet.getString(2);
				String teamname2 = resultSet.getString(3);
				String teamimage = resultSet.getString(4);
				String teamtime = resultSet.getString(5);
				String teamaddress = resultSet.getString(6);
				double teamdistance = resultSet.getDouble(7);
				int teamnumber = resultSet.getInt(8);
				String teamslogan = resultSet.getString(9);
				String teamintroduce = resultSet.getString(10);
				String teaminform = resultSet.getString(11);
				Luser luser=new Luser();
                String name=luser.SuserName(userid);
                
				BeanTeam beamTeam = new BeanTeam(teamid, userid, teamname2,
						teamimage, teamtime, teamaddress, teamdistance,
						teamnumber, teamslogan, teamintroduce, teaminform,name);
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

	// 查询某一具体车队
	@Override
	public BeanTeam SteamById(int teamid) {
		BeanTeam beanTeam = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT *FROM team WHERE teamid = ?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teamid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int teamid2 = resultSet.getInt(1);
				String userid = resultSet.getString(2);
				String teamname = resultSet.getString(3);
				String teamimage = resultSet.getString(4);
				String teamtime = resultSet.getString(5);
				String teamaddress = resultSet.getString(6);
				double teamdistance = resultSet.getDouble(7);
				@SuppressWarnings("unused")
				int teamnumber = resultSet.getInt(8);
				LteamPeople lteamPeople=new LteamPeople();
				int teamnumber2=lteamPeople.Snumber(teamid2);
				String teamslogan = resultSet.getString(9);
				String teamintroduce = resultSet.getString(10);
				String teaminform = resultSet.getString(11);
				Luser luser=new Luser();
                String name=luser.SuserName(userid);
                
				beanTeam = new BeanTeam(teamid2, userid, teamname, teamimage,
						teamtime, teamaddress, teamdistance, teamnumber2,
						teamslogan, teamintroduce, teaminform,name);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return beanTeam;
	}

	// 修改车队信息
	@Override
	public boolean Uteam(int teamid, String teamname, 
			String teamslogan, String teamintroduce, String teaminform) {
		boolean boo = false;// 默认插入失败
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "UPDATE team SET teamname=?," +
				"teamslogan=?,teamintroduce=?,teaminform=? WHERE teamid=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, teamname);
			preparedStatement.setString(2, teamslogan);
			preparedStatement.setString(3, teamintroduce);
			preparedStatement.setString(4, teaminform);
			preparedStatement.setInt(5, teamid);
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
	//修改车队头像
	@Override
	public boolean Uimage(int teamid, String teamimage) {
		boolean boo = false;// 默认修改失败
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE team SET teamimage =? WHERE teamid=?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, teamimage);
			preparedStatement.setInt(2, teamid);
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
//修改车队人数
	@Override
	public boolean UpeoNumber(int teamid) {
		LteamPeople lteamPeople=new LteamPeople();
		boolean boo = false;// 默认修改失败
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE team SET teamnumber =? WHERE teamid=?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			int teamnumber =lteamPeople.Snumber(teamid);
			preparedStatement.setInt(1, teamnumber);
			preparedStatement.setInt(2, teamid);
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
//SELECT * FROM way ORDER BY zambia DESC  LIMIT ?,?
	//查询前五的车队
	@Override
	public List<BeanTeam> SteamByten() {
		List<BeanTeam> list = new ArrayList<BeanTeam>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT * FROM team  ORDER BY teamdistance DESC  LIMIT 0,10;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int teamid = resultSet.getInt(1);
				String userid = resultSet.getString(2);
				String teamname = resultSet.getString(3);
				String teamimage = resultSet.getString(4);
				String teamtime = resultSet.getString(5);
				String teamaddress = resultSet.getString(6);
				double teamdistance = resultSet.getDouble(7);
				//int teamnumber = resultSet.getInt(8);
				LteamPeople lteamPeople=new LteamPeople();
				int teamnumber2=lteamPeople.Snumber(teamid);
				String teamslogan = resultSet.getString(9);
				String teamintroduce = resultSet.getString(10);
				String teaminform = resultSet.getString(11);
				Luser luser=new Luser();
                String name=luser.SuserName(userid);
				BeanTeam beamTeam = new BeanTeam(teamid, userid, teamname,
						teamimage, teamtime, teamaddress, teamdistance,
						teamnumber2, teamslogan, teamintroduce, teaminform,name);
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
	// 根据时间搜索车队
	@Override
	public int SteamByTime(String teamtime) {
			int teamid = 0;
			Connection connection = GJcsh.getConnection();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			connection = GJcsh.getConnection();
			String sql = "SELECT teamid FROM team WHERE teamtime =?;";
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, teamtime);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					 teamid = resultSet.getInt(1);
					
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				GJcsh.close(connection, preparedStatement, resultSet);
			}
			return teamid;
	}
//车队人数增加
	@Override
	public boolean Addteambumber(int teamid) {
		boolean boo = false;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "UPDATE team SET teamnumber=teamnumber+1 WHERE teamid=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teamid);
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
//车队人数减少
	@Override
	public boolean Subteambumber(int teamid) {
		boolean boo = false;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "UPDATE team SET teamnumber=teamnumber-1 WHERE teamid=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teamid);
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
