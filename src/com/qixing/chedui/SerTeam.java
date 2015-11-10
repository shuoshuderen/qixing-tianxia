package com.qixing.chedui;

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
import com.qixing.teamPeople.BeanTeamPeople;
import com.qixing.teamPeople.LteamPeople;

@WebServlet("/SerTeam")
public class SerTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SerTeam() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter printWriter = response.getWriter();
		String flg = request.getParameter("flg");
		int fl = Integer.parseInt(flg);
		Lteam lteam = new Lteam();
		switch (fl) {
		case 1:// 查询我所在的车队
			System.out.println("车队1");
			String userid = request.getParameter("userid");
			LteamPeople lteamPeople = new LteamPeople();
			List<BeanTeamPeople> list = lteamPeople.Saddteam(userid);
			List<BeanTeam> lTeams = new ArrayList<BeanTeam>();
			for (int i = 0; i < list.size(); i++) {
				BeanTeam beanTeam = lteam.SteamById(list.get(i).getTeamid());
				lTeams.add(beanTeam);
			}

			Gson gson = new Gson();
			Type typeOfSrc = new TypeToken<List<BeanTeam>>() {
			}.getType();
			java.lang.String result = gson.toJson(lTeams, typeOfSrc);// 集合使用type类型

			printWriter = response.getWriter();
			printWriter.write(result);
			break;
		case 2:// 查询车队前十
			System.out.println("车队2");
			List<BeanTeam> lTeams1 = lteam.SteamByten();

			Gson gson1 = new Gson();
			Type typeOfSrc1 = new TypeToken<List<BeanTeam>>() {
			}.getType();
			java.lang.String result1 = gson1.toJson(lTeams1, typeOfSrc1);// 集合使用type类型
			System.out.println(lTeams1.toString());
			printWriter = response.getWriter();
			printWriter.write(result1);
			break;
		case 3:// 删除车队
			System.out.println("车队3");
			String team = request.getParameter("teamid");
            int teamid=Integer.parseInt(team);
            boolean b= lteam.Dteam(teamid);
			if (b) {
				printWriter.write("解散成功");
			}else {
				printWriter.write("解散失败");
			}
			break;
		default:
			break;
		}
	}

}
