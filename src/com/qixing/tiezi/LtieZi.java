
package com.qixing.tiezi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qixing.pinglun.Lcomments;
import com.qixing.user.Luser;
import com.qixing.util.GJNum;
import com.qixing.util.GJcsh;

public class LtieZi implements JKtieZi {
	// 删除帖子
	@Override
	public boolean delete(int postid) {
		boolean boo = false;// 默认删除失败
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "delete from post where postid=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, postid);
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

	// 插入帖子，帖子id为自增，不用插入,需插入其他几项

	@Override
	public boolean Inewtz(String userid, String postname, String postaddress,
			String posttime,  String postclass) {
		boolean boo = false;// 默认插入失败
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO post (userid,postname,postaddress,posttime,postclass) VALUES(?,?,?,?,?);";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			preparedStatement.setString(2, postname);
			preparedStatement.setString(3, postaddress);
			preparedStatement.setString(4, posttime);
			preparedStatement.setString(5, postclass);
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

	// 插入图片，一个帖子中可有多张图片
	@Override
	public boolean insertphoto(int postid, String photo) {
		boolean boo = false;// 默认删除失败
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO postphoto (postid,photo) VALUES(?,?);";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, postid);
			preparedStatement.setString(2, photo);
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

	// 修改帖子,根据帖子id修改帖子名字和内容
	@Override
	public boolean UtieZi(int postid, String postname, String postaddress) {
		boolean boo = false;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "UPDATE post SET postname=?,postaddress=?WHERE postid=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, postname);
			preparedStatement.setString(2, postaddress);
			preparedStatement.setInt(3, postid);
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

	// 根据页数查询
	@Override
	public List<BeanPost> SpostByNum(int pageNow) {
		Luser luser=new Luser();
		LtieZi ltieZi=new LtieZi();
		List<BeanPostPhoto> list2;
		List<BeanPost> list = new ArrayList<BeanPost>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT * FROM post ORDER BY posttime DESC LIMIT ?,? ;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			int m = (pageNow - 1) * GJNum.pageNum;
			int n = GJNum.pageNum;
			preparedStatement.setInt(1, m);
			preparedStatement.setInt(2, n);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int postid = resultSet.getInt(1);
				//找到id，吧id替换为头像
				String userid = resultSet.getString(2);
				String touxiang= luser.Simage(userid);
				String postname = resultSet.getString(3);
				String postaddress = resultSet.getString(4);
				String posttime = resultSet.getString(5);
				int postpraise = resultSet.getInt(6);
				String postclass = resultSet.getString(7);
				int plSum = resultSet.getInt(8);
				
		        list2= ltieZi.SpostPhoto(postid);
				BeanPost beanTieZi = new BeanPost(postid, userid, postname,
						postaddress, posttime, postpraise, postclass,plSum,touxiang,list2);
				list.add(beanTieZi);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	// 根据帖子名搜索模糊查询,可以有多条数据
	@Override
	public List<BeanPost> SpostByName(String postname) {
		Luser luser=new Luser();
		LtieZi ltieZi=new LtieZi();
		List<BeanPostPhoto> list2;
		List<BeanPost> list = new ArrayList<BeanPost>();
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT *FROM post WHERE postname like ?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + postname + "%");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int postid = resultSet.getInt(1);
				String userid = resultSet.getString(2);
				String touxiang= luser.Simage(userid);
				String postname2 = resultSet.getString(3);
				String postaddress = resultSet.getString(4);
				String posttime = resultSet.getString(5);
				int postpraise = resultSet.getInt(6);
				String postclass = resultSet.getString(7);
				int plSum = resultSet.getInt(8);

				 list2= ltieZi.SpostPhoto(postid);
				BeanPost BeanTieZi = new BeanPost(postid, userid, postname2,
						postaddress, posttime, postpraise, postclass,plSum,touxiang,list2);
				list.add(BeanTieZi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}

		return list;
	}

	// 根据id找到帖子，只有一条，用bean对象，查看今天的帖子和修改时用
	@Override
	public BeanPost SpostById(int postid) {
		Luser luser=new Luser();
		LtieZi ltieZi=new LtieZi();
		List<BeanPostPhoto> list2;
		BeanPost BeanTieZi = null;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT *FROM post WHERE postid = ?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, postid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int postid2 = resultSet.getInt(1);
				String userid = resultSet.getString(2);
				String touxiang= luser.Simage(userid);
				String postname = resultSet.getString(3);
				String postaddress = resultSet.getString(4);
				String posttime = resultSet.getString(5);
				int postpraise = resultSet.getInt(6);
				String postclass = resultSet.getString(7);
				int plSum = resultSet.getInt(8);
				 list2= ltieZi.SpostPhoto(postid);
				BeanTieZi = new BeanPost(postid2, userid, postname,
						postaddress, posttime, postpraise, postclass,plSum,touxiang,list2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return BeanTieZi;

	}

	// 在图片表中获取图片,可以有多张，用集合
	@Override
	public List<BeanPostPhoto> SpostPhoto(int postid) {
		List<BeanPostPhoto> list = new ArrayList<BeanPostPhoto>();
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		// 只查询address这一类的信息
		String sql = "SELECT photo FROM postphoto WHERE postid=?;";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, postid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				/*
				 * String address = resultSet.getString(1); if (address == null)
				 * { address = null; } else { address =
				 * address.substring(address.indexOf("\\photo")); }
				 */
				String photo = resultSet.getString(1);
				BeanPostPhoto benTieZiPhoto = new BeanPostPhoto(photo);
				list.add(benTieZiPhoto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	// 增加赞
	@Override
	public boolean Addzan(int postid) {
		boolean boo = false;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "UPDATE post SET postpraise=postpraise+1 WHERE postid=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, postid);
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
//减赞
	@Override
	public boolean Subzan(int postid) {
		boolean boo = false;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "UPDATE post SET postpraise=postpraise-1 WHERE postid=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, postid);
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
//赞的个数
	@Override
	public int SzanNum(int postid) {
		int number = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = GJcsh.getConnection();
		String sql = "SELECT postpraise FROM post WHERE postid = ?;";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, postid);
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
//修改数加一
	@Override
	public boolean UplNumber(int postid) {
		boolean boo = false;// 默认修改失败
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE post SET plSum =plSum+1 WHERE postid=?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, postid);
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
	
//评论减一
	@Override
	public boolean UplNumbersub(int postid) {
		boolean boo = false;// 默认修改失败
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE post SET plSum =plSum-1 WHERE postid=?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, postid);
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
	
//根据用户名查看帖子
	@Override
	public List<BeanPost> SpostByuserid(String userid,int pageNow) {
		Luser luser=new Luser();
		LtieZi ltieZi=new LtieZi();
		List<BeanPostPhoto> list2;
		List<BeanPost> list = new ArrayList<BeanPost>();
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT *FROM post  WHERE userid=? ORDER BY posttime DESC LIMIT ?,?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			int m = (pageNow - 1) * GJNum.pageNum;
			int n = GJNum.pageNum;
			preparedStatement.setInt(2, m);
			preparedStatement.setInt(3, n);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int postid = resultSet.getInt(1);
				String userid2 = resultSet.getString(2);
				String touxiang= luser.Simage(userid2);
				String postname2 = resultSet.getString(3);
				String postaddress = resultSet.getString(4);
				String posttime = resultSet.getString(5);
				int postpraise = resultSet.getInt(6);
				String postclass = resultSet.getString(7);
				//int plSum = resultSet.getInt(8);\//根据帖子id，找到评论个数
				Lcomments lcomments=new Lcomments();
				int plSm2=lcomments.Splnumber(postid);
				 list2= ltieZi.SpostPhoto(postid);
				BeanPost BeanTieZi = new BeanPost(postid, userid, postname2,
						postaddress, posttime, postpraise, postclass,plSm2,touxiang,list2);
				list.add(BeanTieZi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}

		return list;
	}
	//根据发帖时间查询帖子id
	@Override
	public int SpostidBytime(String time) {
		int id = 0;
		Connection connection = GJcsh.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT postid FROM post  WHERE posttime=? ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, time);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			GJcsh.close(connection, preparedStatement, resultSet);
		}

		return id;
	}



}
