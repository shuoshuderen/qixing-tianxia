package com.qixing.zhuangbei;

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

@WebServlet("/SerEqudetail")
public class SerEqudetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public SerEqudetail() {
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
		Lequdetail lequdetail=new Lequdetail();
		switch (fl) {
		case 1://获取装备
			System.out.println("装备1");
			String yema = request.getParameter("yema");
			int ye=Integer.parseInt(yema);
			String lei = request.getParameter("lei");
			
		    List<BeanEqudetail> list=	lequdetail.SequdetailByNum(lei, ye);
			
		    Gson gson = new Gson();
			Type typeOfSrc = new TypeToken<List<BeanEqudetail>>() {
			}.getType();
			java.lang.String result = gson.toJson(list, typeOfSrc);// 集合使用type类型
			printWriter.write(result);
			break;
case 2:
			
			break;
		default:
			break;
		}
	}

}
