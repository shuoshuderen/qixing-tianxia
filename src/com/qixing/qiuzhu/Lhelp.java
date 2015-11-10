package com.qixing.qiuzhu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qixing.util.GJcsh;
import com.qixing.util.GJnowtime;

public class Lhelp  implements JKhelp{
	//发布求助
	@Override
	public boolean Iseekhep(String userId, String name, String helpContent,
			String helpAddress, double helpLongitude, double helpLatitude) {
		boolean boo = false;// 默认删除失败
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO seekhelp (userid,name,helpContent,helpAddress,helpLongitude,helpLatitude,time) VALUES(?,?,?,?,?,?,?);";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, helpContent);
			preparedStatement.setString(4, helpAddress);
			preparedStatement.setDouble(5, helpLongitude);
			preparedStatement.setDouble(6, helpLatitude);
			String time=GJnowtime.gettime();
			preparedStatement.setString(7, time);
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
	//查看求助
	@Override
	public BeanHelp sHelp(int seekhelpid) {
		BeanHelp beanHelp=null;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		String sql="select * from seekhelp where seekhelpid=?";
		
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, seekhelpid);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				String userId=resultSet.getString(2);
				String name=resultSet.getString(3);
				String helpContent=resultSet.getString(4);
				String helpAddress=resultSet.getString(5);
				double helpLongitude =resultSet.getDouble(6);
				double helpLatitude =resultSet.getDouble(7);
				String time=resultSet.getString(8);
				beanHelp=new BeanHelp(seekhelpid, userId, name,
						helpContent, helpAddress, helpLongitude, helpLatitude,time);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return beanHelp;
	}
	//根据城市模糊查询
	@Override
	public List<BeanHelp> getHelps(String helpaddress) {
		// TODO Auto-generated method stub
		BeanHelp beanHelp=null;
		List<BeanHelp> list=new ArrayList<BeanHelp>();
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		String sql="select * from seekhelp where helpaddress like ?";
		//String sql="select * from seekhelp where helpaddress like '%"+helpaddress+"%'";
		try {
			String address = helpaddress == "" ? "%" : "%" +helpaddress+ "%";
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, address);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int seekhelpid=resultSet.getInt(1) ;
				String userId=resultSet.getString(2);
				String name=resultSet.getString(3);
				String helpContent=resultSet.getString(4);
				String helpAddress=resultSet.getString(5);
				double helpLongitude =resultSet.getDouble(6);
				double helpLatitude =resultSet.getDouble(7);
				String time=resultSet.getString(8);
				beanHelp=new BeanHelp(seekhelpid, userId, name, helpContent, helpAddress, helpLongitude, helpLatitude,time);
				list.add(beanHelp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return list;
	}

}
