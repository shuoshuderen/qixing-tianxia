package com.qixing.newmen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qixing.util.GJcsh;

public class Lfreshonroot implements JKfreshonroot {
	// 根据页数查询新手推荐，一次n条
	@Override
	public List<BeanFreshonroot> SfreshByNum() {
		List<BeanFreshonroot> list = new ArrayList<BeanFreshonroot>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT * FROM freshonroot  LIMIT 0,10;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int freshonrootid = resultSet.getInt(1);
				String freshtitle = resultSet.getString(2);
				String freshmaincontent = resultSet.getString(3);
				int freshzan = resultSet.getInt(4);
				String maininform = resultSet.getString(5);
				  List<String> list1=	SfreshPhoto(freshonrootid);
					 
					BeanFreshonroot BeanFreshonroot=new BeanFreshonroot(
							freshonrootid, freshtitle, freshmaincontent, freshzan, maininform,list1);
					list.add(BeanFreshonroot);
				list.add(BeanFreshonroot);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return list;
	}
	// // 根据名字搜索新手推荐
	@Override
	public List<BeanFreshonroot> SfreshByName(String freshtitle) {
		List<BeanFreshonroot> list = new ArrayList<BeanFreshonroot>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT *FROM freshonroot WHERE freshtitle like ?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + freshtitle + "%");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int freshonrootid = resultSet.getInt(1);
				String freshtitle2 = resultSet.getString(2);
				String freshmaincontent = resultSet.getString(3);
				int freshzan = resultSet.getInt(4);
				String maininform = resultSet.getString(5);
				  List<String> list1=	SfreshPhoto(freshonrootid);
					 
				BeanFreshonroot BeanFreshonroot=new BeanFreshonroot(
						freshonrootid, freshtitle2, freshmaincontent, freshzan, maininform,list1);
				list.add(BeanFreshonroot);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return list;
	}
	//查询某一条具体新手推荐
	@Override
	public BeanFreshonroot SfreshById(int freshonrootid) {
		BeanFreshonroot BeanFreshonroot=null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT *FROM freshonroot WHERE freshonrootid = ?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, freshonrootid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int freshonrootid1 = resultSet.getInt(1);
				String freshtitle = resultSet.getString(2);
				String freshmaincontent = resultSet.getString(3);
				int freshzan = resultSet.getInt(4);
				String maininform = resultSet.getString(5);
			    List<String> list=	SfreshPhoto(freshonrootid1);
				 BeanFreshonroot=new BeanFreshonroot(
						freshonrootid1, freshtitle, freshmaincontent, freshzan, maininform,list);
		
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return BeanFreshonroot;
	}
	//查询新手推荐中的图片
	@Override
	public List<String> SfreshPhoto(int freshonrootid) {
		List<String> list=new ArrayList<String>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT photo FROM freshonrootphoto WHERE freshonrootid = ?;";
		
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, freshonrootid);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
			String photo=resultSet.getString(1);
			
				list.add(photo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	@Override
	public boolean Addzan(int freshonrootid) {
		boolean boo = false;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "UPDATE freshonroot SET freshzan=freshzan+1 WHERE freshonrootid=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, freshonrootid);
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

	public boolean Addnrirom(int freshonrootid,String neirong,String nr) {
		boolean boo = false;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "UPDATE freshonroot SET freshtitle=?,maininform=? WHERE freshonrootid=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, neirong);
			preparedStatement.setString(2, nr);
			preparedStatement.setInt(3, freshonrootid);
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
