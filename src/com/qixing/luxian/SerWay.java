package com.qixing.luxian;

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

@WebServlet("/SerWay")
public class SerWay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SerWay() {
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
		Lway lway = new Lway();
		switch (fl) {
		case 1:// 查询前n条路线
			System.out.println("路线1");
			String num = request.getParameter("yema");
			System.out.println(num);
			int n = Integer.parseInt(num);
			List<BeanWay> list = lway.SwayByNum(n);

			Gson gson = new Gson();
			Type typeOfSrc = new TypeToken<List<BeanWay>>() {
			}.getType();
			java.lang.String result = gson.toJson(list, typeOfSrc);// 集合使用type类型
			printWriter = response.getWriter();
			printWriter.write(result);
			break;
		case 2:// 查询前10条路线
			System.out.println("路线2");
			List<BeanWay> list1 = lway.StopWays();

			Gson gson1 = new Gson();
			Type typeOfSrc1 = new TypeToken<List<BeanWay>>() {
			}.getType();
			java.lang.String result1 = gson1.toJson(list1, typeOfSrc1);// 集合使用type类型
			printWriter = response.getWriter();
			printWriter.write(result1);
			break;
		case 3://赞增加
			System.out.println("路线3");
			String wayid = request.getParameter("wayid");
			int way=Integer.parseInt(wayid);
			lway.Addzan(way);
		break;
		case 4://赞减少
			System.out.println("路线4");
			String wayid1 = request.getParameter("wayid");
			int way1=Integer.parseInt(wayid1);
			lway.Subzan(way1);
			break;
		case 5://赞数量
			System.out.println("路线5");
			String wayid11 = request.getParameter("wayid");
			int way11=Integer.parseInt(wayid11);
		      int zannum=	lway.SzanNum(way11);
		      printWriter.write(zannum+"");
		      System.out.println(zannum);
			break;
		case 6://
			System.out.println("路线6");
			String wayname = request.getParameter("wayname");
			List<BeanWay> listway=	lway.SwayByName(wayname);
		     
			
			Gson gson11 = new Gson();
			Type typeOfSrc11 = new TypeToken<List<BeanWay>>() {
			}.getType();
			java.lang.String result11 = gson11.toJson(listway, typeOfSrc11);// 集合使用type类型
			printWriter = response.getWriter();
			printWriter.write(result11);
			break;
		default:
			break;
		}
	}

}
