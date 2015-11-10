package com.qixing.qiuzhu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.lang.reflect.Type;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@WebServlet("/SerHelp")
public class SerHelp extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SerHelp() {
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
		Lhelp help=new Lhelp();
		switch (fl) {
		case 1://查看附近求助
			System.out.println("求助1");
			String address= request.getParameter("helpaddress");
			List<BeanHelp> list=help.getHelps(address);
			System.out.println(list.toString());
			//向客户端返回数据
			Gson gson = new Gson();
			Type typeOfSrc = new TypeToken<List<BeanHelp>>(){}.getType();
			String result = gson.toJson(list, typeOfSrc);
			printWriter.write(result);
			break;
		case 2://插入求助
			System.out.println("求助2");
			String userId= request.getParameter("userid");
			String name= request.getParameter("name");
			String helpContent= request.getParameter("neirong");
			String helpAddress= request.getParameter("address");
			String helpLongitude= request.getParameter("long");
			double lon=Double.parseDouble(helpLongitude);
			String helpLatitude= request.getParameter("la");
			double la=Double.parseDouble(helpLatitude);
		    boolean a=	help.Iseekhep(userId, name, helpContent, helpAddress, lon, la);
			if (a) {
				printWriter.write("发表成功");
			}else {
				printWriter.write("发表失败");
			}
			
			break;
		
		default:
			break;
		}
	}

}
