package com.qixing.user;

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

@WebServlet("/SerDengLu")
public class SerDengLu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SerDengLu() {
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
		Luser luser = new Luser();
		switch (fl) {

		case 1:// 判断账号密码是否正确，需先判断账号存在
			System.out.println(1);
			String id = request.getParameter("userid");
			String mima = request.getParameter("password");
			if (luser.CheckZC(id)) {// 判断用户名是否存在
				String denglu=luser.CheckDL(id, mima);
				System.out.println(denglu);
				if (!denglu.equals("0")){// 判断账号和密码师傅正确
					printWriter.write(denglu);
				} else {
					printWriter.write("0");
				}
			} else {
				printWriter.write("0");
			}

			break;
		case 2:// 注册账号，需先验证账号是否存在
			System.out.println(2);
			String id2 = request.getParameter("userid");
			String mima2 = request.getParameter("password");
			
			System.out.println(id2 + "-->" + mima2);
			if (luser.CheckZC(id2)) {// 判断用户名是否存在
				printWriter.write("用户名已存在，不能注册");
			} else {
				if (luser.CheckZcInsert(id2, mima2)) {// 插入语句，判断是否插入成功
					printWriter.write("success");
				} else {
					printWriter.write("一般不会失败，所以我不会出现");
				}
			}

			break;
		case 3:// 查询某个用户的个人信息
			System.out.println(3);
			String useridString = request.getParameter("userid");
			System.out.println(useridString);
			List<BeanUser> list = luser.Suser(useridString);

			Gson gson = new Gson();
			Type typeOfSrc = new TypeToken<List<BeanUser>>() {
			}.getType();
			java.lang.String result = gson.toJson(list, typeOfSrc);// 集合使用type类型

			printWriter = response.getWriter();
			printWriter.write(result);
			break;
		case 4:// 编辑个人信息页面
			System.out.println(4);
			String id4 = request.getParameter("userid");
			String username = request.getParameter("username");
			String gender = request.getParameter("gen");
			String age1 = request.getParameter("age");
			int age=Integer.parseInt(age1);
			String address = request.getParameter("address");
			System.out.println(id4+username+ gender+age+address);
			boolean fff = luser.Uxinxi(id4, username, gender, age, address);
			if (fff) {
				printWriter.write("修改成功");
			} else {
				printWriter.write("修改失败");
			}
			break;
		case 5://根据名字查询用户信息
			System.out.println("用户5");
			String usernam = request.getParameter("username");
			System.out.println(usernam);
			List<BeanUser> list1 = luser.SuserByName(usernam);

			Gson gson1 = new Gson();
			Type typeOfSrc1 = new TypeToken<List<BeanUser>>() {
			}.getType();
			java.lang.String result1 = gson1.toJson(list1, typeOfSrc1);// 集合使用type类型

			printWriter = response.getWriter();
			printWriter.write(result1);

			break;
		case 6://修改经纬度
			System.out.println("用户6");
			String id41 = request.getParameter("userid");
			String jingdu = request.getParameter("jingdu");
			String weidu = request.getParameter("weidu");
			double lon= Double.parseDouble(jingdu);
			double lan= Double.parseDouble(weidu);
			System.out.println(id41+"__>"+lon+"__>"+lan);
		    boolean sss=	luser.Uitude(id41, lan, lon);
		    System.out.println(sss);
			break;
		default:
			break;
		}

	}

}
