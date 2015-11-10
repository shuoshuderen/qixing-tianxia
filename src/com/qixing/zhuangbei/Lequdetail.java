package com.qixing.zhuangbei;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qixing.util.GJNum;
import com.qixing.util.GJcsh;

public class Lequdetail implements JKequdetail {
	// 装备分为四类，点击进入后查看具体某一类，然后分页显示，一页n条
	@Override
	public List<BeanEqudetail> SequdetailByNum(String equdetailClass,
			int pageNow) {
		List<BeanEqudetail> list = new ArrayList<BeanEqudetail>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT * FROM equdetail WHERE equdetailClass=? ORDER BY equdetailid DESC LIMIT ?,? ;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			int m = (pageNow - 1) * GJNum.pageNum;
			int n = GJNum.pageNum;
			preparedStatement.setString(1, equdetailClass);
			preparedStatement.setInt(2, m);
			preparedStatement.setInt(3, n);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int equdetailId = resultSet.getInt(1);
				String equdetailName = resultSet.getString(2);
				double equdetailPrice = resultSet.getInt(3);
				String equdetailInform = resultSet.getString(4);
				String equdetailClass2 = resultSet.getString(5);
				
				List<Beanequdetailphoto> list2=new ArrayList<Beanequdetailphoto>();
				list2=Sequdetailphoto(equdetailId);
				BeanEqudetail BeanEqudetail = new BeanEqudetail(equdetailId,
						equdetailName, equdetailPrice, equdetailInform,
						equdetailClass2,list2);
				list.add(BeanEqudetail);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	// 根据名字搜索装备
	@Override
	public List<BeanEqudetail> SequdetailByName(String equdetailName) {
		List<BeanEqudetail> list = new ArrayList<BeanEqudetail>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT *FROM equdetail WHERE equdetailName like ?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + equdetailName + "%");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int equdetailId = resultSet.getInt(1);
				String equdetailName2 = resultSet.getString(2);
				double equdetailPrice = resultSet.getInt(3);
				String equdetailInform = resultSet.getString(4);
				String equdetailClass = resultSet.getString(5);
				List<Beanequdetailphoto> list2=new ArrayList<Beanequdetailphoto>();
				list2=Sequdetailphoto(equdetailId);
				BeanEqudetail BeanEqudetail = new BeanEqudetail(equdetailId,
						equdetailName2, equdetailPrice, equdetailInform,
						equdetailClass,list2);
				list.add(BeanEqudetail);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	// 查询某一条具体装备
	@Override
	public BeanEqudetail SequdetailById(int equdetailId) {
		BeanEqudetail beanEqudetail = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT *FROM equdetail WHERE equdetailid = ?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, equdetailId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int equdetailId2 = resultSet.getInt(1);
				String equdetailName = resultSet.getString(2);
				double equdetailPrice = resultSet.getInt(3);
				String equdetailInform = resultSet.getString(4);
				String equdetailClass = resultSet.getString(5);
				List<Beanequdetailphoto> list2=new ArrayList<Beanequdetailphoto>();
				list2=Sequdetailphoto(equdetailId);
				beanEqudetail = new BeanEqudetail(equdetailId2,
						equdetailName, equdetailPrice, equdetailInform,
						equdetailClass,list2);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return beanEqudetail;
	}

	// 查询装备中的图片
	@Override
	public List<Beanequdetailphoto> Sequdetailphoto(int equdetailId) {
		List<Beanequdetailphoto> list=new ArrayList<Beanequdetailphoto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT photo FROM equdetailphoto WHERE equdetailId = ?;";
		
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, equdetailId);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
			String photo=resultSet.getString(1);
			Beanequdetailphoto beanequdetailphoto=new Beanequdetailphoto(photo);
				list.add(beanequdetailphoto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	

}
