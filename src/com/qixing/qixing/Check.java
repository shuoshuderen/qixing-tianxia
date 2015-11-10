package com.qixing.qixing;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qixing.newmen.Lfreshonroot;

/**
 * Servlet implementation class Check
 */
@WebServlet("/Check")
public class Check extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Check() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("进入");
		int userid = 5;
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String username1 = request.getParameter("nnn");
		String username2 = request.getParameter("n");
	
		/*LtieZi ltieZi=new LtieZi();
		ltieZi.UtieZi(userid, username1, username2);*/
		Lfreshonroot lfreshonroot=new Lfreshonroot();
		
		lfreshonroot.Addnrirom(userid, username1, username2);
	}

}
