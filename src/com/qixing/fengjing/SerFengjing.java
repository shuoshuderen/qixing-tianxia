package com.qixing.fengjing;

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

@WebServlet("/SerFengjing")
public class SerFengjing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SerFengjing() {
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
		Lscenery lscenery=new Lscenery();
		switch (fl) {
		case 1:
			System.out.println("风景1");
		 List<BeanScenery> list=	lscenery.SscenceryByNum(1);
		 
		 Gson gson = new Gson();
			Type typeOfSrc = new TypeToken<List<BeanScenery>>() {
			}.getType();
			java.lang.String result = gson.toJson(list, typeOfSrc);// 集合使用type类型

			printWriter = response.getWriter();
			printWriter.write(result);
			break;

		default:
			break;
		}
	}

}
