package com.qixing.pinglun;

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
import com.qixing.tiezi.LtieZi;
import com.qixing.util.GJnowtime;

@WebServlet("/SerComments")
public class SerComments extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SerComments() {
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
		Lcomments lcomments = new Lcomments();
		int fl = Integer.parseInt(flg);
		switch (fl) {

		case 1:// 根据帖子id查看该帖子的所有评论
			System.out.println("评论1");
			String postids = request.getParameter("postid");
			int postid = Integer.parseInt(postids);
			List<BeanComments> list = lcomments.Scomments(postid);

			Gson gson = new Gson();
			Type typeOfSrc = new TypeToken<List<BeanComments>>() {
			}.getType();
			java.lang.String result = gson.toJson(list, typeOfSrc);// 集合使用type类型
			printWriter = response.getWriter();
			printWriter.write(result);

			break;
		case 2:// 发表评论
			System.out.println("评论2");
			String postids1 = request.getParameter("postid");
			String userid = request.getParameter("userid");
			String content = request.getParameter("comtent");
			String time = GJnowtime.gettime();
			int postid1 = Integer.parseInt(postids1);
			boolean b = lcomments.Icomments(postid1, userid, time, content);
			if (b) {
				printWriter.write("评论成功");
				LtieZi ltieZi = new LtieZi();
				ltieZi.UplNumber(postid1);
			} else {
				printWriter.write("评论失败");
			}

			break;
		case 3:// 删除评论
			String comm = request.getParameter("commentsid");
			int commentsid = Integer.parseInt(comm);
			String post = request.getParameter("postid");
			int postid11=Integer.parseInt(post);
			boolean bool= lcomments.Dcomments(commentsid);
			if (bool) {
				printWriter.write("删除成功");
				LtieZi ltieZi = new LtieZi();
				ltieZi.UplNumbersub(postid11);
			}else {
				printWriter.write("删除失败");
			}
			break;

		default:
			break;
		}
	}

}
