package com.qixing.newmen;

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

@WebServlet("/SerFreshonroot")
public class SerFreshonroot extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public SerFreshonroot() {
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
		Lfreshonroot lfreshonroot=new Lfreshonroot();
		switch (fl) {
		case 1:
			System.out.println("新手1");
		   List<BeanFreshonroot> list=lfreshonroot.SfreshByNum();
			
		    Gson gson = new Gson();
			Type typeOfSrc = new TypeToken<List<BeanFreshonroot>>() {
			}.getType();
			java.lang.String result = gson.toJson(list, typeOfSrc);// 集合使用type类型
			printWriter.write(result);
			
			break;

		default:
			break;
		}
	}

}
