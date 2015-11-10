package com.qixing.teamPeople;

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
import com.qixing.chedui.Lteam;
import com.qixing.user.BeanUser;
import com.qixing.user.Luser;

@WebServlet("/SerTeamPeople")
public class SerTeamPeople extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public SerTeamPeople() {
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
		LteamPeople lteamPeople=new LteamPeople();
		Luser luser=new Luser();
		switch (fl) {
		case 1://查询车队队员
			System.out.println("队员1");
			String teamid = request.getParameter("teamid");
			int teamid1=Integer.parseInt(teamid);
			System.out.println(teamid+"jjj");
			List<BeanTeamPeople> list=lteamPeople.SteamPeople(teamid1);
			List<BeanUser> lUsers=new ArrayList<BeanUser>();
			for (int i = 0; i <list.size(); i++) {
			List<BeanUser> list2	=luser.Suser(list.get(i).getUserid());
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
		case 2://判断是否是车队队员
			System.out.println("队员2");
			String userid = request.getParameter("userid");
			String teamid2 = request.getParameter("teamid");
			int itteamid=Integer.parseInt(teamid2);
			boolean boo=lteamPeople.check(itteamid, userid);
			if (boo) {
				printWriter.write("true");
			}else {
				printWriter.write("false");
			}
			break;
		case 3://加入车队
			System.out.println("队员3");
			String userid1 = request.getParameter("userid");
			String teamid21 = request.getParameter("teamid");
			int itteamid1=Integer.parseInt(teamid21);
			
			boolean boo1=lteamPeople.IteamPeople(itteamid1, userid1);
			Lteam lteam=new Lteam();
			lteam.Addteambumber(itteamid1);
			if (boo1) {
				printWriter.write("成功加入");
			}else {
				printWriter.write("未能成功");
			}
			break;
		case 4://退出车队
			System.out.println("队员4");
			String userid11 = request.getParameter("userid");
			String teamid211 = request.getParameter("teamid");
			int itteamid11=Integer.parseInt(teamid211);
			
			boolean boo11=lteamPeople.DteamPeople(itteamid11, userid11);
			
			Lteam lteam1=new Lteam();
			lteam1.Subteambumber(itteamid11);
			if (boo11) {
				printWriter.write("退出成功");
			}else {
				printWriter.write("未能成功");
			}
			break;
		default:
			break;
		}
	}

}
