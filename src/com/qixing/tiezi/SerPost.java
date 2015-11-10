package com.qixing.tiezi;

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

@WebServlet("/SerPost")
public class SerPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SerPost() {
        super();
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
		LtieZi ltieZi=new LtieZi();
		switch (fl) {
		case 1://查看前n条帖子
			System.out.println("帖子1");
			String yema=request.getParameter("yema");
			int yeshu=Integer.parseInt(yema);
			
		   List<BeanPost> list=	ltieZi.SpostByNum(yeshu);
		   
		   Gson gson = new Gson();
			Type typeOfSrc = new TypeToken<List<BeanPost>>() {
			}.getType();
			java.lang.String result = gson.toJson(list, typeOfSrc);// 集合使用type类型
			printWriter = response.getWriter();
			printWriter.write(result);
			
			break;
		case 2://查看我的帖子,一次显示n条
			System.out.println("帖子2");
			String id = request.getParameter("userid");
			String yema2=request.getParameter("yema");
			int yeshu2=Integer.parseInt(yema2);
			List<BeanPost> list2 = null;
			list2=	ltieZi.SpostByuserid(id,yeshu2);
	
		   Gson gson2 = new Gson();
			Type typeOfSrc2 = new TypeToken<List<BeanPost>>() {
			}.getType();
			java.lang.String result2 = gson2.toJson(list2, typeOfSrc2);// 集合使用type类型

			printWriter = response.getWriter();
			printWriter.write(result2);
			
			break;
		case 3://查看帖子中赞的数量
			System.out.println("帖子3");
			String postid = request.getParameter("postid");
			int postiddd=Integer.parseInt(postid);
		      int zannum=	ltieZi.SzanNum(postiddd);
		      printWriter.write(zannum+"");
		      System.out.println(zannum);
			break;
		case 4://赞增加
			System.out.println("帖子4");
			String postid1 = request.getParameter("postid");
			int postid12=Integer.parseInt(postid1);
			ltieZi.Addzan(postid12);
		break;
		case 5://赞减少
			System.out.println("帖子5");
			String postid2 = request.getParameter("postid");
			int postid22=Integer.parseInt(postid2);
			ltieZi.Subzan(postid22);
			break;
		default:
			break;
		}
	}

}
