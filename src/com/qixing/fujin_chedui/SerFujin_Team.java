package com.qixing.fujin_chedui;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qixing.chedui.BeanTeam;

/**
 * Servlet implementation class SerFujin_Team
 */
@WebServlet("/SerFujin_Team")
public class SerFujin_Team extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SerFujin_Team() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		LFujin_Team lFujin_Team=new LFujin_Team();
		String teamaddress= request.getParameter("teamaddress");
		System.out.println(teamaddress+"地址");
		List<BeanTeam> list=lFujin_Team.getTeam(teamaddress);
		System.out.println("车队"+list.toString());
		//向客户端返回数据
		Gson gson = new Gson();
		Type typeOfSrc = new TypeToken<List<BeanTeam>>(){}.getType();
		String result = gson.toJson(list, typeOfSrc);
		PrintWriter printWriter = response.getWriter();
		printWriter.write(result);
	}

}
