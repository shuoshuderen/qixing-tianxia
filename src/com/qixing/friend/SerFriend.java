package com.qixing.friend;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qixing.user.BeanUser;
import com.qixing.user.Luser;

@WebServlet("/SerFriend")
public class SerFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public SerFriend() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter printWriter = response.getWriter();
		String flg = request.getParameter("flg");
		int fl = Integer.parseInt(flg);
		Lfriend lfriend=new Lfriend();
		Luser luser=new Luser();
		switch (fl) {
		case 1://查询好友
			System.out.println("好友1");
			String userId = request.getParameter("userid");
			List<BeanFriend> list=lfriend.Scollect(userId);
			List<BeanUser> lUsers=new ArrayList<BeanUser>();
			for (int i = 0; i <list.size(); i++) {
			List<BeanUser> list2	=luser.Suser(list.get(i).getFriendid());
			BeanUser beanUser  =list2.get(0);
			lUsers.add(beanUser);
			}
			
			Gson gson = new Gson();
			Type typeOfSrc = new TypeToken<List<BeanUser>>() {
			}.getType();
			java.lang.String result = gson.toJson(lUsers, typeOfSrc);// 集合使用type类型

			printWriter = response.getWriter();
			printWriter.write(result);
			break;
		case 2://判断是否是好友
			System.out.println("好友2");
			String userid = request.getParameter("userid");
			String friendid = request.getParameter("friendid");
			System.out.println(userid+"-->"+friendid);
		boolean boo=lfriend.check(userid, friendid);
			if (boo) {
				printWriter.write("true");
			}else {
				printWriter.write("false");
			}
			break;
		case 3://加好友
			System.out.println("好友3");
			String userid1 = request.getParameter("userid");
			String friendid1 = request.getParameter("friendid");
		boolean boo1=lfriend.Icollect(userid1, friendid1);
			if (boo1) {
				printWriter.write("关注成功");
			}else {
				printWriter.write("关注失败");
			}
			break;
		case 4://删好友
			System.out.println("好友4");
			String userid11 = request.getParameter("userid");
			String friendid11 = request.getParameter("friendid");
		boolean boo11=lfriend.Dcollect(userid11, friendid11);
			if (boo11) {
				printWriter.write("取消关注");
			}else {
				printWriter.write("关注失败");
			}
			break;
		default:
		
			break;
		}
	}
	

}
