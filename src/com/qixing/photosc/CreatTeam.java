package com.qixing.photosc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import com.qixing.chedui.Lteam;
import com.qixing.teamPeople.LteamPeople;
import com.qixing.tiezi.LtieZi;
import com.qixing.util.GJnowtime;

@WebServlet("/CreatTeam")
public class CreatTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public CreatTeam() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入车队创建");
		List<String> list=new ArrayList<String>();
				Lteam lteam=new Lteam();

				DiskFileUpload fUpload = new DiskFileUpload();
				List fList = null;
				try {
					fList = fUpload.parseRequest(request);
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Iterator iterator = fList.iterator();
				String userid, teamname,teamimage = null, teamaddress,
				 teamslogan, teamintroduce, teaminform;
				
				while (iterator.hasNext()) {
					FileItem item = (FileItem) iterator.next();
					if (item.isFormField()) {
						request.setAttribute(item.getFieldName(), item.getString());

						System.out.println("item.getFieldName()-->"
								+ item.getFieldName() + "=" + item.getString());

					} else {
						try {
							
							Date date = new Date();
							long f1 = date.getTime();
							// 图片名
							teamimage = f1 + ".jpg";
							
							// 存放位置
							String s1 = "F:\\photo\\" + teamimage;
							
							item.write(new File(s1));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				String teamtime=GJnowtime.gettime();
				teamname = (String) request.getAttribute("teamname");
				userid = (String) request.getAttribute("userid");
				teamaddress="苏州";
				teamintroduce = (String) request.getAttribute("teamintroduce");
				teamslogan = (String) request.getAttribute("teamslogan");
				teaminform=(String) request.getAttribute("teaminform");
			System.out.println("用户名-->"+userid+"车队名-->"+teamname+"地点-->"+teamaddress+"口号-->"+teamslogan+
					"介绍————》"+teamintroduce+"公告-->"+teaminform);
				boolean boo=lteam.Iteam(userid, teamname, teamimage, teamaddress, teamslogan, teamintroduce, teaminform,teamtime);
				//根据车队创建时间，寻找车队id
				int teamid=lteam.SteamByTime(teamtime);
				//根据车队id，将创建人插入数据库中
				LteamPeople lteamPeople=new LteamPeople();
				lteamPeople.IteamPeople(teamid, userid);
				 System.out.println(boo);
				System.out.println("完成车队创建");
	}

}
