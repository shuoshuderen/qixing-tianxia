package com.qixing.jilu;

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
import com.qixing.user.Luser;


@WebServlet("/SerJiLu")
public class SerJiLu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SerJiLu() {
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
	 Ljilu ljilu=new Ljilu();
		int fl = Integer.parseInt(flg);
		switch (fl) {
		
		case 1://查看自己记录
			System.out.println("记录1");
			String userid = request.getParameter("userid");
		List<BeanJiLu> list=ljilu.Sjilu(userid);
			System.out.println();
			 Gson gson = new Gson();
				Type typeOfSrc = new TypeToken<List<BeanJiLu>>() {
				}.getType();
				java.lang.String result = gson.toJson(list, typeOfSrc);// 集合使用type类型
				printWriter = response.getWriter();
				printWriter.write(result);

			break;
		case 2://发表记录
			System.out.println("记录2");
		
			String userid1 = request.getParameter("userid");
		String mileag=request.getParameter("mileage");
		double mileage=Double.parseDouble(mileag);
		String alltime=request.getParameter("alltime");
		System.out.println(mileage+"-->"+alltime);
		    boolean b=  ljilu.Ijilu(userid1, mileage, alltime);
			if (b) {
				printWriter.write("上传成功");
			Luser luser=new Luser();
			luser.Utime(userid1, mileage, alltime);
			}else {
				printWriter.write("距离过短或时间过少，上传失败");
			}

			break;
		default:
			break;
		}
	}
	

}
