package com.qixing.shoucang;

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
import com.qixing.tiezi.BeanPost;
import com.qixing.tiezi.LtieZi;

@WebServlet("/SerCollect")
public class SerCollect extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public SerCollect() {
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
		Lcollect lcollect=new Lcollect();
		switch (fl) {
		case 1://收藏
			System.out.println("收藏1");
			String userid = request.getParameter("userid");
			String postidd = request.getParameter("postid");
			int postid=  Integer.parseInt(postidd);
			boolean boo= lcollect.Icollect(postid, userid);
			if (boo) {
				printWriter.write("收藏成功");
			}else {
				printWriter.write("收藏失败");
			}
			
			break;
		case 2://判断
			System.out.println("收藏2");
			String userid2 = request.getParameter("userid");
			String postidd2 = request.getParameter("postid");
			int postid2=  Integer.parseInt(postidd2);
			boolean boo2= lcollect.Scollect(postid2, userid2);
			if (boo2) {
				printWriter.write("是");
			}else {
				printWriter.write("没有收藏");
			}
			break;
			
		case 3://取消收藏
			System.out.println("收藏3");
			String userid3 = request.getParameter("userid");
			String postidd3 = request.getParameter("postid");
			int postid3=  Integer.parseInt(postidd3);
			boolean boo3= lcollect.Dcollect(postid3, userid3);
			if (boo3) {
				printWriter.write("已取消");
			}else {
				printWriter.write("未能取消");
			}
			break;
		case 4://查看我收藏的帖子
			System.out.println("收藏4");
			String userid4 = request.getParameter("userid");
			System.out.println(userid4);
		    List<BeanMyCollect> list=	lcollect.Scollect(userid4);
		    System.out.println(list.toString());
		    LtieZi ltieZi=new LtieZi();
		    List<BeanPost> lBeanPosts=new ArrayList<BeanPost>();
		    for (int i = 0; i < list.size(); i++) {
			BeanPost beanPost=	ltieZi.SpostById(list.get(i).getPostId());
			lBeanPosts.add(beanPost);
			}
		    System.out.println(lBeanPosts.toString());
		    Gson gson = new Gson();
			Type typeOfSrc = new TypeToken<List<BeanPost>>() {
			}.getType();
			java.lang.String result = gson.toJson(lBeanPosts, typeOfSrc);// 集合使用type类型

			printWriter = response.getWriter();
			printWriter.write(result);
			break;
		default:
			break;
		}
	}

}
