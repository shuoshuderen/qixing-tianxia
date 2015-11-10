package com.qixing.fengjing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qixing.util.GJNum;
import com.qixing.util.GJcsh;

public class Lscenery implements JKscenery {
	// 查询前n条
	@Override
	public List<BeanScenery> SscenceryByNum(int pageNow) {
		List<BeanScenery> list = new ArrayList<BeanScenery>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT * FROM scenery LIMIT ?,? ;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			int m = (pageNow - 1) * GJNum.pageNum;
			int n = GJNum.pageNum;
			preparedStatement.setInt(1, m);
			preparedStatement.setInt(2, n);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int sceneryid = resultSet.getInt(1);
				String sceneryname = resultSet.getString(2);
				//调用方法，查询每个id中的图片，作为list集合
				Lscenery lscenery=new Lscenery();
			      List<BeanSceneryPhoto> list2=  lscenery.SscenceryImage(sceneryid);
				BeanScenery beanScenery=new BeanScenery(sceneryid, sceneryname,list2);
				list.add(beanScenery);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return list;
	}
	//根据风景id查询具体图片
	@Override
	public List<BeanSceneryPhoto> SscenceryImage(int sceneryid) {
		List<BeanSceneryPhoto> list = new ArrayList<BeanSceneryPhoto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT photo FROM sceneryphoto WHERE sceneryid = ?;";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, sceneryid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String photo = resultSet.getString(1);
				BeanSceneryPhoto beanSceneryPhoto=new BeanSceneryPhoto(photo);
				list.add(beanSceneryPhoto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}	
	

}
