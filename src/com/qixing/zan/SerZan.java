package com.qixing.zan;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SerZan")
public class SerZan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SerZan() {
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
		Lzan lzan=new Lzan();
		switch (fl) {
		case 1://帖子赞
			System.out.println("赞1");
			String userid = request.getParameter("userid");
			String postidd = request.getParameter("postid");
			int postid=  Integer.parseInt(postidd);
			boolean boo=lzan.IzanPost(postid, userid);
			if (boo) {
				printWriter.write("成功");
			}else {
				printWriter.write("失败");
			}
			
			break;
		case 2://判断
			System.out.println("赞2");
			String userid2 = request.getParameter("userid");
			String postidd2 = request.getParameter("postid");
			int postid2=  Integer.parseInt(postidd2);
			boolean boo2=lzan.SzanPost(postid2, userid2);
			if (boo2) {
				printWriter.write("是");
			}else {
				printWriter.write("没有赞");
			}
			break;
			
		case 3://取消
			System.out.println("赞3");
			String userid3 = request.getParameter("userid");
			String postidd3 = request.getParameter("postid");
			int postid3=  Integer.parseInt(postidd3);
			boolean boo3= lzan.DzanPost(postid3, userid3);
			if (boo3) {
				printWriter.write("已取消");
			}else {
				printWriter.write("未能取消");
			}
			break;
		case 4://路线赞
			System.out.println("赞4");
			String userid1 = request.getParameter("userid");
			String wayid = request.getParameter("wayid");
			int wayid1=  Integer.parseInt(wayid);
			boolean boo1= lzan.IzanWay(wayid1, userid1);
			if (boo1) {
				printWriter.write("已赞");
			}else {
				printWriter.write("失败");
			}
			
			break;
		case 5://判断
			System.out.println("赞5");
			String userid21 = request.getParameter("userid");
			String wayid11 = request.getParameter("wayid");
			int wayid111=  Integer.parseInt(wayid11);
			boolean boo21=lzan.SzanWay(wayid111, userid21); 
			if (boo21) {
				printWriter.write("是");
			}else {
				printWriter.write("没有赞");
			}
			break;
			
		case 6://取消
			System.out.println("赞6");
			String userid31 = request.getParameter("userid");
			String wayid1111 = request.getParameter("wayid");
			int wayid4=  Integer.parseInt(wayid1111);
			boolean boo31=lzan.Dzanway(wayid4, userid31);
			if (boo31) {
				printWriter.write("已取消");
			}else {
				printWriter.write("未能取消");
			}
			break;
		default:
			break;
		}
	}

}
