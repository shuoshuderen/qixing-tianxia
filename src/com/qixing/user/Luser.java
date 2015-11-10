package com.qixing.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qixing.util.GJDM5;
import com.qixing.util.GJcsh;

public class Luser implements Jkuser {
	// 判断登录是否成功
	@Override
	public String CheckDL(String userid, String password) {
		String flag="0";
		//password = GJDM5.MD5(password);
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		
		String sql = "SELECT PASSWORD FROM USER WHERE userid=? LIMIT 1;";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String pass = resultSet.getString(1);
				if (pass.equals(password)) {
					flag=userid;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return flag;
	}

	// 查询用户名是否存在
	@Override
	public boolean CheckZC(String userid) {
		boolean boo = false;// 默认不重复可以注册
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		String sql = "SELECT userid FROM USER WHERE userid=? LIMIT 1;";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
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

	// 判断用户插入数据库是否成功
	@Override
	public boolean CheckZcInsert(String username, String password) {
		boolean boo = false;// 默认失败
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		//
		String sql = "INSERT  INTO USER (userid,PASSWORD) VALUES (?,?);";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			password = GJDM5.MD5(password);
			preparedStatement.setString(2, password);
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

	// 修改用户信息 用户名，性别，年龄，地址，
	@Override
	public boolean Uxinxi(String userid, String username, String gender,
			int age, String address) {
		boolean fla=false;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "UPDATE USER SET username=?,age=?,gender=?,address=? WHERE userid=?;";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, age);
			preparedStatement.setString(3, gender);
			preparedStatement.setString(4, address);
			preparedStatement.setString(5, userid);
			preparedStatement.execute();
			fla=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, null);
		}
		return fla;
	}

	// 修改密码
	@Override
	public boolean Umima(String userid, String password) {
		boolean boo = false;// 默认修改失败
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE USER SET PASSWORD =？WHERE userid=？;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			password = GJDM5.MD5(password);
			preparedStatement.setString(1, password);
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

	// 修改骑行时间和路程
	@Override
	public boolean Utime(String userid, double totaldistance, String totaltime) {
		boolean boo = false;// 默认插入失败
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;

		String sql =" UPDATE USER SET totaldistance =totaldistance+?,totaltime=totaltime+? WHERE userid=?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, totaldistance);
			preparedStatement.setString(2, totaltime);
			preparedStatement.setString(3, userid);
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

	// 修改经纬度
	@Override
	public boolean Uitude(String userid, double latiudt, double longitude) {
		boolean boo = false;// 默认插入失败
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE USER SET latitude =?,longitude=? WHERE userid=?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, latiudt);
			preparedStatement.setDouble(2, longitude);
			preparedStatement.setString(3, userid);
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

	// 头像初始值为空，所以都是修改头像
	@Override
	public boolean Uimage(String userid, String userimg) {
		boolean boo = false;// 默认修改失败
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE USER SET userimage =? WHERE userid=?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userimg);
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
//根据用户id查询用户信息
	@Override
	public List<BeanUser> Suser(String userid) {
		List<BeanUser> list = new ArrayList<BeanUser>();
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT username,gender,age,userimage,address,totaldistance,totaltime FROM USER WHERE userid=?;";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String username = resultSet.getString(1);
				String gender = resultSet.getString(2);
				int age = resultSet.getInt(3);
				String userimg = resultSet.getString(4);
				String address = resultSet.getString(5);
				double totaldistance = resultSet.getDouble(6);
				String totaltime = resultSet.getString(7);

				BeanUser beanUser = new BeanUser(userid, username, gender, age,
						userimg, address, totaldistance, totaltime);
				list.add(beanUser);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return list;
	}
	//查看用户的具体信息,根据用户名
	@Override
	public List<BeanUser> SuserByName(String username) {
		List<BeanUser> list = new ArrayList<BeanUser>();
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT userid,gender,age,userimage,address,totaldistance,totaltime,username FROM USER WHERE username like ?;";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + username + "%");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String userid = resultSet.getString(1);
				String gender = resultSet.getString(2);
				int age = resultSet.getInt(3);
				String userimg = resultSet.getString(4);
				String address = resultSet.getString(5);
				double totaldistance = resultSet.getDouble(6);
				String totaltime = resultSet.getString(7);
				String username2 = resultSet.getString(8);
				BeanUser beanUser = new BeanUser(userid, username2, gender, age,
						userimg, address, totaldistance, totaltime);
				list.add(beanUser);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return list;
	}
	//根据用户id，获取用户头像
	@Override
	public String Simage(String userid) {
		String touxiang=null;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT userimage FROM USER WHERE userid=?;";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				touxiang = resultSet.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return touxiang;
	}
	//根据用户id，获取用户名
	@Override
	public String SuserName(String userid) {
		String username=null;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT username FROM USER WHERE userid=?;";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				username = resultSet.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return username;
	}

	

}
