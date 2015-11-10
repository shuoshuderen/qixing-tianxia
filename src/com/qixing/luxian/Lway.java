package com.qixing.luxian;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qixing.util.GJNum;
import com.qixing.util.GJcsh;

public class Lway implements JKway {
	// 根据也是查询路线，一次n条
	@Override
	public List<BeanWay> SwayByNum(int pageNow) {
		List<BeanWay> list = new ArrayList<BeanWay>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT * FROM way ORDER BY wayDATETIME DESC LIMIT ?,? ;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			int m = (pageNow - 1) * GJNum.pageNum;
			int n = GJNum.pageNum;
			preparedStatement.setInt(1, m);
			preparedStatement.setInt(2, n);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int wayId = resultSet.getInt(1);
				int start1 = resultSet.getInt(2);
				int start2 = resultSet.getInt(3);
				int start3 = resultSet.getInt(4);
				String wayDatatime = resultSet.getString(5);
				String wayName = resultSet.getString(6);
				String wayDecription = resultSet.getString(7);
				int wayZambia = resultSet.getInt(8);
				//根据路线id，查询图标表中图片
				List<BeanWayImage> listImages=SwayImage(wayId);
				BeanWay beanWay = new BeanWay(wayId, start1, start2, start3,
						wayDatatime, wayName, wayDecription, wayZambia,listImages);
				list.add(beanWay);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return list;
	}
	//查看前十条
	@Override
	public List<BeanWay> StopWays() {
		List<BeanWay> list = new ArrayList<BeanWay>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT * FROM way ORDER BY zambia DESC  LIMIT ?,? ; ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, 0);
			preparedStatement.setInt(2, 10);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int wayId = resultSet.getInt(1);
				int start1 = resultSet.getInt(2);
				int start2 = resultSet.getInt(3);
				int start3 = resultSet.getInt(4);
				String wayDatatime = resultSet.getString(5);
				String wayName = resultSet.getString(6);
				String wayDecription = resultSet.getString(7);
				int wayZambia = resultSet.getInt(8);
				//根据路线id，查询图标表中图片
				List<BeanWayImage> listImages=SwayImage(wayId);
				BeanWay beanWay = new BeanWay(wayId, start1, start2, start3,
						wayDatatime, wayName, wayDecription, wayZambia,listImages);
				list.add(beanWay);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return list;
	}
	// 根据名字搜索路线
	@Override
	public List<BeanWay> SwayByName(String wayName) {
		List<BeanWay> list = new ArrayList<BeanWay>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT *FROM way WHERE wayName like ?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + wayName + "%");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int wayId = resultSet.getInt(1);
				int start1 = resultSet.getInt(2);
				int start2 = resultSet.getInt(3);
				int start3 = resultSet.getInt(4);
				String wayDatatime = resultSet.getString(5);
				String wayName2 = resultSet.getString(6);
				String wayDecription = resultSet.getString(7);
				int wayZambia = resultSet.getInt(8);
				//根据路线id，查询图标表中图片
				List<BeanWayImage> listImages=SwayImage(wayId);
				BeanWay beanWay = new BeanWay(wayId, start1, start2, start3,
						wayDatatime, wayName2, wayDecription, wayZambia,listImages);
				list.add(beanWay);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	// 查询某一条具体路线
	@Override
	public BeanWay SwayById(int wayid) {
		BeanWay beanWay = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT *FROM way WHERE wayid = ?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, wayid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int wayId = resultSet.getInt(1);
				int start1 = resultSet.getInt(2);
				int start2 = resultSet.getInt(3);
				int start3 = resultSet.getInt(4);
				String wayDatatime = resultSet.getString(5);
				String wayName2 = resultSet.getString(6);
				String wayDecription = resultSet.getString(7);
				int wayZambia = resultSet.getInt(8);
				//根据路线id，查询图标表中图片
				List<BeanWayImage> listImages=SwayImage(wayId);
				beanWay = new BeanWay(wayId, start1, start2, start3,
						wayDatatime, wayName2, wayDecription, wayZambia,listImages);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return beanWay;
	}

	// 查询路线中的图片
	@Override
	public List<BeanWayImage> SwayImage(int wayid) {
		List<BeanWayImage> list = new ArrayList<BeanWayImage>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT wayimageroot FROM wayimage WHERE wayid = ?;";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, wayid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String wayimageroot = resultSet.getString(1);
				BeanWayImage beanWayImage = new BeanWayImage(wayimageroot);
				list.add(beanWayImage);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
//修改路线赞的数量
	@Override
	public boolean Addzan(int wayid) {
		boolean boo = false;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "UPDATE way SET Zambia=Zambia+1 WHERE wayid=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, wayid);
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
	//赞减少
	@Override
	public boolean Subzan(int wayid) {
		boolean boo = false;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "UPDATE way SET Zambia=Zambia-1 WHERE wayid=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, wayid);
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
	//查看赞的数量
	@Override
	public int SzanNum(int wayid) {
		int number = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT Zambia FROM way WHERE wayid = ?;";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, wayid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				number= resultSet.getInt(1);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return number;
	}

	

}
